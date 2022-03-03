/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.test;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
    try {
            URL fxURL = getClass().getResource("../gui/FrontRestau.fxml");
            Parent root = FXMLLoader.load(fxURL);
            Scene scene = new Scene(root);
            //String css = this.getClass().getResource("../gui/ajouterproduitfxml.css").toExternalForm();
            //scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajout de restau");
            primaryStage.show();
            
        } catch (IOException ex) {
          System.out.println(ex.getMessage());
        }
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
