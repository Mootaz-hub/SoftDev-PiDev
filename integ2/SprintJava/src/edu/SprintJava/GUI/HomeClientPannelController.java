/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.GUI;

import edu.SprintJava.entities.Session;
import edu.SprintJava.entities.User;
import edu.SprintJava.services.User_service;
import edu.SprintJava.utils.Notification;
import java.awt.AWTException;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moete
 */
public class HomeClientPannelController implements Initializable {

    private Pane pnlClient;
    private Pane pnlActualite;
    
    @FXML
    private Button shopp;
    @FXML
    private Button restau;
    @FXML
    private Label name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        pnlActualite.toFront();
        User_service us = new User_service();
        User usersession = us.findById(Session.getUser().getId());
        name.setText(usersession.getUsername());
    }    

    private void AfficherSettingsPane(ActionEvent event) {
        pnlClient.toFront();
    }

    @FXML
    private void ModifierClient(ActionEvent event) {
        try {
            
            Parent root=FXMLLoader.load(getClass().getResource("ModifierClient.fxml"));

            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void AfficherPaneActua(ActionEvent event) {
        pnlActualite.toFront();
    }

    @FXML
    private void Logout(ActionEvent event) throws AWTException {
        try {
            Launcher.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("LoginClient.fxml"))));
            Notification.main("Session " + Session.getUser().getUsername() , "Ended !!");
        } catch (IOException ex) {
            Logger.getLogger(PanieerrController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void shopp(ActionEvent event) {
         try {
            Launcher.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("produitfront.fxml"))));
        } catch (IOException ex) {
            Logger.getLogger(PanieerrController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resatu(ActionEvent event) {
         try {
            Launcher.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("FrontRestau.fxml"))));
        } catch (IOException ex) {
            Logger.getLogger(PanieerrController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Activite(ActionEvent event) {
         try {
            Launcher.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("FrontActivite.fxml"))));
        } catch (IOException ex) {
            Logger.getLogger(PanieerrController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToEvent(ActionEvent event) {
        try {
            Launcher.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("FrontEvent.fxml"))));
        } catch (IOException ex) {
            Logger.getLogger(PanieerrController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
