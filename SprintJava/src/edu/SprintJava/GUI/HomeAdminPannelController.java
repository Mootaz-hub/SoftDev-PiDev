/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.GUI;

import edu.SprintJava.entities.Admin;
import edu.SprintJava.entities.Client;
import edu.SprintJava.entities.Livreur;
import edu.SprintJava.entities.Session;
import edu.SprintJava.services.AdminCRUD;
import edu.SprintJava.services.ClientCRUD;
import edu.SprintJava.services.LivreurCRUD;
import edu.SprintJava.utils.Notification;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author moete
 */
public class HomeAdminPannelController implements Initializable {
    private static final String LINKED_IN = "https://www.linkedin.com/in/muhammedafsalvillan/";
    private static final String FACEBOOK = "http://www.facebook.com/afsalashyana";
    private static final String WEBSITE = "http://www.genuinecoder.com";
    private static final String YOUTUBE = "https://www.youtube.com/c/GenuineCoder";

    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnAdminList;
    @FXML
    private Button btnClientList;
    @FXML
    private Button btnLivreurList;
    @FXML
    private Pane pnlLivreur;
    @FXML
    private Pane pnlClient;
    @FXML
    private Pane pnlAdmin;
    @FXML
    private TableView<Admin> TVListeAdmin;
    @FXML
    private TableColumn<Admin, String> TCNom;
    @FXML
    private TableColumn<Admin, String> TCPrenom;
    @FXML
    private TableColumn<Admin, Integer> TCCIN;
    @FXML
    private TableColumn<Admin, String> TCEmail;
    @FXML
    private TableColumn<Admin, String> TCUsername;
    @FXML
    private TableColumn<Admin, String> TCPassword;
    @FXML
    private TableColumn<Admin, String> TCRole;
    @FXML
    private Label TitreAdmin;
    @FXML
    private TableView<Client> TVClient;
    @FXML
    private TableColumn<Client, String> TCNomClient;
    @FXML
    private TableColumn<Client, String> TCPrenomClient;
    @FXML
    private TableColumn<Client, String> TCDateNaissance;
    @FXML
    private TableColumn<Client, String> TCPaysVille;
    @FXML
    private TableColumn<Client, Integer> TCMobile;
    @FXML
    private TableColumn<Client, String> TCEmailClient;
    @FXML
    private TableColumn<Client, String> TCUsernameClient;
    @FXML
    private TableColumn<Client, String> TCPasswordClient;
    @FXML
    private TableColumn<Client, String> TCGenre;
    @FXML
    private TableView<Livreur> TVLivreur;
    @FXML
    private TableColumn<Livreur, String> TCNomLivreur;
    @FXML
    private TableColumn<Livreur, String> TCPrenomLivreur;
    @FXML
    private TableColumn<Livreur, String> TCEmailLivreur;
    @FXML
    private TableColumn<Livreur, String> TCUsernameLivreur;
    @FXML
    private TableColumn<Livreur, String> TCPasswordLivreur;
    @FXML
    private TextField TFNomLivreur;
    @FXML
    private TextField TFPrenomLivreur;
    @FXML
    private TextField TFEmailLivreur;
    @FXML
    private TextField TFUsernameLivreur;
    @FXML
    private PasswordField TFPasswordLivreur;
    @FXML
    private TextField TFUpdAdNom;
    @FXML
    private TextField TFUpdAdPrenom;
    @FXML
    private TextField TFUpdAdEmail;
    @FXML
    private TextField TFUpdAdPassword;
    @FXML
    private TextField TFUpdAdUsername;
    @FXML
    private TextField TFUpdAdCIN;
    @FXML
    private TextField TFUpdAdRole;
    @FXML
    private Pane pnlModifyAdmin;
    @FXML
    private TextField TFRechercheAd;
    @FXML
    private TextField TFRechercheLiv;
    @FXML
    private TextField TFRechercherCli;
    @FXML
    private Pane pnlClient1;

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //******** Lister dans table admin *****************/
        AdminCRUD adc = new AdminCRUD();
        ObservableList<Admin> liste = FXCollections.observableArrayList();
        liste= adc.ListerAdmin();
        remplirTableAdmin(liste );
        //************ Lister dans table Client ***********/
        ClientCRUD clc = new ClientCRUD();
        ObservableList<Client> listeClient=FXCollections.observableArrayList();
        listeClient=clc.ListerClient();
        remplirTableClient(listeClient);
        //************ Lister dans table Livreur ***********/
        LivreurCRUD  lic = new LivreurCRUD();
        ObservableList<Livreur> listeLivreur=FXCollections.observableArrayList();
        listeLivreur=lic.ListerLivreur();
        remplirTableLivreur(listeLivreur);
//        LoadDataLivreur();
//        LoadDataAdmin();
    }
    private void loadWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
            handleWebpageLoadException(url);
        }
    }

    private void handleWebpageLoadException(String url) {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(url);
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane(browser));
        stage.setScene(scene);
        stage.setTitle("Genuine Coder");
        stage.show();
//        LibraryAssistantUtil.setStageIcon(stage);
    }
    @FXML
    private void loadLinkedIN(ActionEvent event) {
        loadWebpage(LINKED_IN);
    }

    @FXML
    private void loadFacebook(ActionEvent event) {
        loadWebpage(FACEBOOK);
    }

    @FXML
    private void loadBlog(ActionEvent event) {
        
        loadWebpage(WEBSITE);
    }

    @FXML
    private void loadYoutubeChannel(ActionEvent event) {
        loadWebpage(YOUTUBE);
    }
    
    
    @FXML
    private void AjouterUser(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("AjouterAdmin.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void AfficherAdminList(ActionEvent event) {
        
            pnlAdmin.toFront();
        
    }

    @FXML
    private void AfficherClientList(ActionEvent event) {
        pnlClient.toFront();
    }

    @FXML
    private void AfficherLivreurList(ActionEvent event) {
        pnlLivreur.toFront();
    }
    
    
    
    private void remplirTableAdmin(ObservableList<Admin> liste ){
     TCNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    TCPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    TCCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
    TCEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    TCUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    TCPassword.setCellValueFactory(new PropertyValueFactory<>("pass"));
    TCRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    TVListeAdmin.setItems(liste);
    }
    private void remplirTableClient(ObservableList<Client> listeClients ){
     TCNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    TCPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    TCDateNaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
    TCPaysVille.setCellValueFactory(new PropertyValueFactory<>("pays_ville"));
    TCMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    TCEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
    TCUsernameClient.setCellValueFactory(new PropertyValueFactory<>("username"));
    TCPasswordClient.setCellValueFactory(new PropertyValueFactory<>("password"));
    TCGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    TVClient.setItems(listeClients);
    }
    private void remplirTableLivreur(ObservableList<Livreur> listeLivreur ){
     TCNomLivreur.setCellValueFactory(new PropertyValueFactory<>("nom"));
    TCPrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    TCEmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
    TCUsernameLivreur.setCellValueFactory(new PropertyValueFactory<>("username"));
    TCPasswordLivreur.setCellValueFactory(new PropertyValueFactory<>("password"));
    TVLivreur.setItems(listeLivreur);
    }

    @FXML
    private void SupprimerAdmin(ActionEvent event) throws Exception {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Admin");
        alert.setHeaderText("Supprimer"+TVListeAdmin.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer l'admin " +TVListeAdmin.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
            AdminCRUD adc=new AdminCRUD();
            Admin ad=new Admin();
            ad=TVListeAdmin.getSelectionModel().getSelectedItem();
            adc.SupprimerAdmin(ad.getNom());
                Notification.main("Admin !", "Admin supprimé avec succé !!");       
                ObservableList<Admin> liste=FXCollections.observableArrayList();
                liste=adc.ListerAdmin();
                remplirTableAdmin(liste);
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }
    
    @FXML
    private void Logout(ActionEvent event) throws Exception {
        try {
            System.out.println("Se deconnecter");
            Session.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root=loader.load();
            TitreAdmin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeAdminPannelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterLivreur(ActionEvent event) {
        LivreurCRUD lic=new LivreurCRUD();
        Livreur li=new Livreur();
        li.setNom(TFNomLivreur.getText());
        li.setPrenom(TFPrenomLivreur.getText());
        li.setEmail(TFEmailLivreur.getText());
        li.setUsername(TFUsernameLivreur.getText());
        li.setPassword(TFPasswordLivreur.getText());
        lic.ajouterLivreur(li);
    }
    @FXML
    private void AfficherAdminModify(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Admin");
        alert.setHeaderText("Modifier"+TVListeAdmin.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment modifier l'admin " +TVListeAdmin.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
            pnlModifyAdmin.toFront();
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }
        
    

    @FXML
    private void ModifierAdmin(ActionEvent event) throws AWTException {
            AdminCRUD adc=new AdminCRUD();
            adc.modifierAdmin(TFUpdAdNom.getText(), TFUpdAdPrenom.getText(), Integer.parseInt(TFUpdAdCIN.getText()), TFUpdAdUsername.getText()
                , TFUpdAdEmail.getText(), TFUpdAdPassword.getText(), TFUpdAdRole.getText());
            Notification.main("Admin !", "Admin modifié avec succé !!");  
            
    }

    @FXML
    private void RechercherAdmin(ActionEvent event) {
        AdminCRUD adc = new AdminCRUD();
         ObservableList<Admin> list = FXCollections.observableArrayList();
         list=adc.rechercherAdminById("nom", TFRechercheAd.getText());
         TCNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         TCPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         TCCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
         TCEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         TCUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
         TCPassword.setCellValueFactory(new PropertyValueFactory<>("pass"));
         TCRole.setCellValueFactory(new PropertyValueFactory<>("role"));
         TVListeAdmin.setItems(list);
    }

    @FXML
    private void RechercherLivreur(ActionEvent event) {
        LivreurCRUD lic = new LivreurCRUD();
         ObservableList<Livreur> list = FXCollections.observableArrayList();
         list=lic.rechercherLivreurById("nom", TFRechercheLiv.getText());
         TCNomLivreur.setCellValueFactory(new PropertyValueFactory<>("nom"));
         TCPrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         TCEmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
         TCUsernameLivreur.setCellValueFactory(new PropertyValueFactory<>("username"));
         TCPasswordLivreur.setCellValueFactory(new PropertyValueFactory<>("password"));
         TVLivreur.setItems(list);
    }

    @FXML
    private void RechercherClient(ActionEvent event) {
        ClientCRUD clc = new ClientCRUD();
         ObservableList<Client> list = FXCollections.observableArrayList();
         list=clc.rechercherClientById("nom", TFRechercherCli.getText());
         TCNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
         TCPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         TCDateNaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
         TCPaysVille.setCellValueFactory(new PropertyValueFactory<>("pays_ville"));
         TCMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
         TCEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
         TCUsernameClient.setCellValueFactory(new PropertyValueFactory<>("username"));
         TCPasswordClient.setCellValueFactory(new PropertyValueFactory<>("password"));
         TCGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
         TVClient.setItems(list);
    }

    @FXML
    private void SupprimerClient(ActionEvent event) throws AWTException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Admin");
        alert.setHeaderText("Supprimer"+TVClient.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer l'admin " +TVClient.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get()==ButtonType.OK){
            ClientCRUD clc=new ClientCRUD();
            Client cl=new Client();
            cl=TVClient.getSelectionModel().getSelectedItem();
            clc.supprimerClient(cl.getNom());
                Notification.main("Admin !", "Admin supprimé avec succé !!");       
                ObservableList<Client> liste=FXCollections.observableArrayList();
                liste=clc.ListerClient();
                remplirTableClient(liste);
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }

    
   

    
}
