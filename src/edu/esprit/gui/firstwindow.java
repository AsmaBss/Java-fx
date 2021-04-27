/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ela
 */
public class firstwindow extends Application {
    
    //displayAll             --> complaints admin
    //displayComplaintFront  --> complaints client
    //DisplayAllFeedback     --> feedbacks admin
    //ShowJobFront           --> feedbacks client
    //ShowJob                --> feedbacks admin
    
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("displayAll.fxml"));
            
            Scene scene = new Scene(root);
            
            
            primaryStage.setTitle("Opportunis");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(firstwindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
