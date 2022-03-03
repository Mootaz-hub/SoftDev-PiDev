/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.GUI;

import edu.SprintJava.entities.Admin;
import edu.SprintJava.services.AdminCRUD;
import edu.SprintJava.utils.ControleSaisie;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * 
 * @author moete
 */
public class AjouterAdminController implements Initializable {
    @FXML
    private javafx.scene.control.TextField TFnom;
    @FXML
    private javafx.scene.control.TextField TFprenom;
    @FXML
    private javafx.scene.control.TextField TFCIN;
    @FXML
    private javafx.scene.control.TextField TFemail;
    @FXML
    private javafx.scene.control.TextField TFlogin;
    @FXML
    private javafx.scene.control.TextField TFpassword;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminCRUD ad =new AdminCRUD();
        
    }    

    @FXML
    private void AjouterAdmin(ActionEvent event) throws IOException {
       AdminCRUD adc=new AdminCRUD();
        ControleSaisie cs = new ControleSaisie();
        if(!cs.testNomPrenom(TFnom.getText())){
            JOptionPane.showMessageDialog(null,"nom incorrect");
        }
        else if(!cs.testNomPrenom(TFprenom.getText())){
            JOptionPane.showMessageDialog(null,"prenom incorrect");
        }
        else if(!cs.testNomPrenom(TFprenom.getText())){
            JOptionPane.showMessageDialog(null,"prenom incorrect");
        }
        else if(!cs.Cin(TFCIN.getText())){
            JOptionPane.showMessageDialog(null,"CIN incorrect");
        }
        else if(!cs.testUsername(TFlogin.getText())){
            JOptionPane.showMessageDialog(null,"Username incorrect");
        }
        else if(!cs.testPassword(TFpassword.getText())){
            JOptionPane.showMessageDialog(null,"Username incorrect");
        }
        
        else{
            Admin ad = new Admin(TFnom.getText(), TFprenom.getText(),  Integer.parseInt(TFCIN.getText()), TFlogin.getText(), TFemail.getText(), TFpassword.getText());
            adc.ajouterAdmin(ad);
            JOptionPane.showMessageDialog(null,"Admin ajouté");
            FXMLLoader loader= new FXMLLoader(getClass().getResource("HomeAdminPannel.fxml"));
            Parent root = loader.load();
            TFnom.getScene().setRoot(root);
        }
        
    }

    @FXML
    private void BackToMainWindow(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RetourAdminPannel(ActionEvent event) {
         try {
            Parent root=FXMLLoader.load(getClass().getResource("HomeAdminPannel.fxml"));
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
