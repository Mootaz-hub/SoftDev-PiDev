/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.GUI;

import edu.SprintJava.entities.User;
import edu.SprintJava.entities.Admin;
import edu.SprintJava.entities.Attachement;
import edu.SprintJava.entities.Client;
import edu.SprintJava.entities.Livreur;
import edu.SprintJava.entities.Session;
import edu.SprintJava.services.AdminCRUD;
import edu.SprintJava.services.AttachementService;
import edu.SprintJava.services.ClientCRUD;
import edu.SprintJava.services.LivreurCRUD;
import edu.SprintJava.services.User_service;
import edu.SprintJava.utils.ControleSaisie;
import edu.SprintJava.utils.Notification;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author moete
 */
public class HomeAdminPannelController implements Initializable {

    ObservableList<Admin> AdminList = FXCollections.observableArrayList();
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
    private TableView<Client> TVClient;
    @FXML
    private TableColumn<Client, String> TCNomClient;
    @FXML
    private TableColumn<Client, String> TCPrenomClient;
    @FXML
    private TableColumn<Client, Date> TCDateNaissance;
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
    private TextField TFRechercheAd;
    @FXML
    private TextField TFRechercheLiv;
    @FXML
    private TextField TFRechercherCli;
    @FXML
    private Pane pnlWelcome;
    @FXML
    private ComboBox<String> RoleBox;

    ObservableList<String> Roles = FXCollections.observableArrayList("Master", "Evenement", "Produit", "Hebergement", "Restaurant", "Activité", "Patrimoine");
    @FXML
    private Pane pnlModifyLivreur;
    @FXML
    private Button btnLivreurList1;
    @FXML
    private Label name;
    @FXML
    private ImageView ImgAvatar;
    @FXML
    private TableColumn<Livreur, Integer> TCid;
    @FXML
    private TableColumn<?, ?> TCidA;

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlWelcome.toFront();
        User_service us = new User_service();
        User usersession = us.findById(Session.getUser().getId());

        User usr = new User();
        AttachementService as = new AttachementService();
        Attachement a = as.findById(Session.getUser().getId());
        //System.out.println((Session.getUser().getAvatar()));
        
        //File file = new File(usersession.getAvatar());
        //System.out.println(file); 
        //Image image = new Image(file.toURI().toString());

        //System.out.println(usersession);
        name.setText(usersession.getUsername());
        //ImgAvatar.setImage();
        //************ Affecter Role**************/
        RoleBox.setItems(Roles);
        RoleBox.setValue("Master");
        //******** Lister dans table admin *****************/
        RechercherAdmin1();
        AdminCRUD adc = new AdminCRUD();
        ObservableList<Admin> liste = FXCollections.observableArrayList();
        liste = adc.ListerAdmin();
        remplirTableAdmin(liste);
        
        //************ Lister dans table Client ***********/
        ClientCRUD clc = new ClientCRUD();
        ObservableList<Client> listeClient = FXCollections.observableArrayList();
        listeClient = clc.ListerClient();
        remplirTableClient(listeClient);
        //************ Lister dans table Livreur ***********/
        LivreurCRUD lic = new LivreurCRUD();
        ObservableList<Livreur> listeLivreur = FXCollections.observableArrayList();
        listeLivreur = lic.ListerLivreur();
        remplirTableLivreur(listeLivreur);

//        LoadDataLivreur();
//        LoadDataAdmin();
    }

    @FXML
    private void AffecterRole(ActionEvent event) {
        AdminCRUD adc = new AdminCRUD();
        adc.affecterAdminRolle(TVListeAdmin.getSelectionModel().getSelectedItem().getNom(), RoleBox.getValue());
        loadMain();
        closeStage();
    }

    private void AfficherSettingPannel(ActionEvent event) throws NullPointerException {
        AdminCRUD adc = new AdminCRUD();
        pnlWelcome.toFront();

    }

    private void loadWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
            handleWebpageLoadException(url);
        }
    }

    private void closeStage() {
        ((Stage) TFRechercheAd.getScene().getWindow()).close();
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
            Parent root = FXMLLoader.load(getClass().getResource("AjouterAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    private void remplirTableAdmin(ObservableList<Admin> liste) {
        
        TCidA.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TCPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TCCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
        TCEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TCUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        TCPassword.setCellValueFactory(new PropertyValueFactory<>("pass"));
        TCRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        TVListeAdmin.setItems(liste);
    }

    private void remplirTableClient(ObservableList<Client> listeClients) {
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

    private void remplirTableLivreur(ObservableList<Livreur> listeLivreur) {
        TCNomLivreur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TCPrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TCEmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
        TCUsernameLivreur.setCellValueFactory(new PropertyValueFactory<>("username"));
        TCPasswordLivreur.setCellValueFactory(new PropertyValueFactory<>("password"));
        TVLivreur.setItems(listeLivreur);
    }

    @FXML
    private void SupprimerAdmin(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Admin");
        alert.setHeaderText("Supprimer" + TVListeAdmin.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer l'admin " + TVListeAdmin.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AdminCRUD adc = new AdminCRUD();
            Admin ad = new Admin();
            ad = TVListeAdmin.getSelectionModel().getSelectedItem();
            adc.SupprimerAdmin(ad.getNom());
            Notification.main("Admin !", "Admin supprimé avec succé !!");
            ObservableList<Admin> liste = FXCollections.observableArrayList();
            liste = adc.ListerAdmin();
            remplirTableAdmin(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    private void Logout(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            Notification.main("Admin !!", "Session Ended !!");
        } catch (IOException ex) {
            Logger.getLogger(HomeAdminPannelController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AfficherAdminModify(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Admin");
        alert.setHeaderText("Modifier" + TVListeAdmin.getSelectionModel().getSelectedItem().getId());
        Admin.setIdd(TVListeAdmin.getSelectionModel().getSelectedItem().getId());
        Admin.setNomm(TVListeAdmin.getSelectionModel().getSelectedItem().getNom());
        Admin.setPrenomm(TVListeAdmin.getSelectionModel().getSelectedItem().getPrenom());
        Admin.setCinn(TVListeAdmin.getSelectionModel().getSelectedItem().getCin());
        Admin.setEmaill(TVListeAdmin.getSelectionModel().getSelectedItem().getEmail());
        Admin.setUsernamee(TVListeAdmin.getSelectionModel().getSelectedItem().getUsername());
        Admin.setPasss(TVListeAdmin.getSelectionModel().getSelectedItem().getPass());
        alert.setContentText("Vous voulez vraiment modifier l'admin " + TVListeAdmin.getSelectionModel().getSelectedItem().getId() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    private void RechercherAdmin1() {
//        AdminCRUD adc = new AdminCRUD();
//         List<Admin> list = new ArrayList<>();
//         list=adc.rechercherAdmin( TFRechercheAd.getText());
//         TCNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//         TCPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//         TCCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
//         TCEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//         TCUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
//         TCPassword.setCellValueFactory(new PropertyValueFactory<>("pass"));
//         TCRole.setCellValueFactory(new PropertyValueFactory<>("role"));
//         TVListeAdmin.setItems((ObservableList<Admin>) list);
        AdminCRUD sp = new AdminCRUD();
        List<Admin> results = new ArrayList<>();
        results = sp.getAll();
        FilteredList<Admin> filteredData = new FilteredList<>(AdminList, b -> true);
        Admin r = new Admin();
// 2. Set the filter Predicate whenever the filter changes.
        TFRechercheAd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Admin -> {
// If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

// Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Admin.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Admin.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (Admin.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else {
                    return false; // Does not match.
                }
            });
        });

// 3. Wrap the FilteredList in a SortedList.
        SortedList<Admin> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TVListeAdmin.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
        TVListeAdmin.setItems(sortedData);

    }

    @FXML
    private void RechercherLivreur(ActionEvent event) {
//        LivreurCRUD lic = new LivreurCRUD();
//         ObservableList<Livreur> list = FXCollections.observableArrayList();
//         list=lic.rechercherLivreurById("nom", TFNomLivreur.getText());
//         TCNomLivreur.setCellValueFactory(new PropertyValueFactory<>("nom"));
//         TCPrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//         TCEmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
//         TCUsernameLivreur.setCellValueFactory(new PropertyValueFactory<>("username"));
//         TCPasswordLivreur.setCellValueFactory(new PropertyValueFactory<>("password"));
//         TVLivreur.setItems(list);

    }

    @FXML
    private void RechercherClient(ActionEvent event) {
        ClientCRUD clc = new ClientCRUD();
        ObservableList<Client> list = FXCollections.observableArrayList();
        list = clc.rechercherClientById("nom", TFRechercherCli.getText());
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Client");
        alert.setHeaderText("Supprimer" + TVClient.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer le client " + TVClient.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ClientCRUD clc = new ClientCRUD();
            Client cl = new Client();
            cl = TVClient.getSelectionModel().getSelectedItem();
            clc.supprimerClient(cl.getNom());
            Notification.main("Client !", "Client supprimé avec succé !!");
            ObservableList<Client> liste = FXCollections.observableArrayList();
            liste = clc.ListerClient();
            remplirTableClient(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("HomeAdminPannel.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("CULTUN");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void GoToModifierLivreur(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Livreur");
        alert.setHeaderText("Modifier" + TVLivreur.getSelectionModel().getSelectedItem().getId());
        Livreur.setIdd(TVLivreur.getSelectionModel().getSelectedItem().getId());
        Livreur.setNomm(TVLivreur.getSelectionModel().getSelectedItem().getNom());
        Livreur.setPrenomm(TVLivreur.getSelectionModel().getSelectedItem().getPrenom());
        Livreur.setEmaill(TVLivreur.getSelectionModel().getSelectedItem().getEmail());
        Livreur.setUsernamee(TVLivreur.getSelectionModel().getSelectedItem().getUsername());
        Livreur.setPasswordd(TVLivreur.getSelectionModel().getSelectedItem().getPassword());
        alert.setContentText("Vous voulez vraiment modifier le livreur " + TVLivreur.getSelectionModel().getSelectedItem().getId() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierLivreur.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    private void SupprimerLivreur(ActionEvent event) throws AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Livreur");
        alert.setHeaderText("Supprimer" + TVLivreur.getSelectionModel().getSelectedItem().getId());
        alert.setContentText("Vous voulez vraiment supprimer le livreur  " + TVLivreur.getSelectionModel().getSelectedItem().getNom() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            LivreurCRUD clc = new LivreurCRUD();
            Livreur cl = new Livreur();
            cl = TVLivreur.getSelectionModel().getSelectedItem();
            clc.supprimerLivreur(cl.getNom());
            Notification.main("Admin " + name.getText() + "Livreur !", "Livreur supprimé avec succé !!");
            ObservableList<Livreur> liste = FXCollections.observableArrayList();
            liste = clc.ListerLivreur();
            remplirTableLivreur(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    private void AjouterLivreur(ActionEvent event) throws AWTException, IOException {
        LivreurCRUD lic = new LivreurCRUD();
        ControleSaisie cs = new ControleSaisie();
        if (!cs.testNomPrenom(TFNomLivreur.getText())) {
            JOptionPane.showMessageDialog(null, "First name is non Valid please enter only Alphabet !! ");
        } else if (!cs.testNomPrenom(TFPrenomLivreur.getText())) {
            JOptionPane.showMessageDialog(null, "Last name is non Valid please enter only Alphabet !! ");
        } else if (!cs.testNomPrenom(TFEmailLivreur.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Email !!");
        } else if (!cs.testUsername(TFUsernameLivreur.getText())) {
            JOptionPane.showMessageDialog(null, "Username Invalid ");
        } else if (!cs.testPassword(TFPasswordLivreur.getText())) {
            JOptionPane.showMessageDialog(null, "Password is not Strong ");
        } else {
            Livreur li = new Livreur(TFNomLivreur.getText(), TFPrenomLivreur.getText(), TFUsernameLivreur.getText(), TFEmailLivreur.getText(), TFPasswordLivreur.getText());
            lic.ajouterLivreur(li);
            User_service us = new User_service();
            us.ajouterUser(new User(TFUsernameLivreur.getText(), TFPasswordLivreur.getText(), "Livreur"));
            JOptionPane.showMessageDialog(null, "Livreur Added");
            Notification.main("Admin " + name.getText() + "Livreur !!", "New Livreur " + TFUsernameLivreur.getText() + "added Succefuly ");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAdminPannel.fxml"));
            pnlLivreur.toFront();
            Parent root = loader.load();
            TFNomLivreur.getScene().setRoot(root);
        }
    }
    
    @FXML
    private void GoToModifierLivreur(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Livreur");
        alert.setHeaderText("Modifier" + TVLivreur.getSelectionModel().getSelectedItem().getId());
        Livreur.setIdd(TVLivreur.getSelectionModel().getSelectedItem().getId());
        Livreur.setNomm(TVLivreur.getSelectionModel().getSelectedItem().getNom());
        Livreur.setPrenomm(TVLivreur.getSelectionModel().getSelectedItem().getPrenom());
        Livreur.setEmaill(TVLivreur.getSelectionModel().getSelectedItem().getEmail());
        Livreur.setUsernamee(TVLivreur.getSelectionModel().getSelectedItem().getUsername());
        Livreur.setPasswordd(TVLivreur.getSelectionModel().getSelectedItem().getPassword());
        alert.setContentText("Vous voulez vraiment modifier le livreur " + TVLivreur.getSelectionModel().getSelectedItem().getId() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierLivreur.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

}
