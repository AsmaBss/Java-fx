/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Feedback;
import edu.esprit.services.FeedbackService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class DisplayAllFeedbackController implements Initializable {

    @FXML
    private TableView<Feedback> FeedbackTable;
    
     public static  TableView<Feedback> table2;
     
    @FXML
    private TableColumn<Feedback, String> descriptionFeedback;
    @FXML
    private TableColumn<Feedback, String> dateFeedback;
    @FXML
    private TableColumn<Feedback, String> userFeedback;
    @FXML
    private TableColumn<Feedback, String> offerFeedback;
    @FXML
    private Button addFeedbackBtn;
    @FXML
    private Button updateFeedbackBtn;
    @FXML
    private Button statBtn;
    
     public static  ObservableList<Feedback>oblist=FXCollections.observableArrayList();
    public static int id;
    @FXML
    private Button removeComplaintBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       table2 = FeedbackTable;
        FeedbackService fs = new FeedbackService();
        
        descriptionFeedback.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateFeedback.setCellValueFactory(new PropertyValueFactory<>("date"));
        userFeedback.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        offerFeedback.setCellValueFactory(new PropertyValueFactory<>("offer"));
        
        FeedbackTable.setItems(fs.displayFeedbacks());
    }    

    @FXML
    private void addFeedback(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addFeedback.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("New Feedback");
stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void updateFeedback(ActionEvent event) {
        
        try {

        Feedback c =table2.getSelectionModel().getSelectedItem();
        if(c == null)
        {//msg alertt
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Update feedback");
        alert.setHeaderText(null);
        alert.setContentText("Please select a feedback to update.");

        alert.showAndWait();
        return;
        
            }
            
            id =c.getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateFeedback.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Update feedback");
stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));

             System.out.println(c);
             System.out.println(c.getId());

         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public int iddf(){
        return id ;
    }

    @FXML
    private void removeFeedback(ActionEvent event) {
        
          Feedback c =FeedbackTable.getSelectionModel().getSelectedItem();
           if(c== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete Feedback");
        alert.setHeaderText(null);
        alert.setContentText("Please select a Feedback");

        alert.showAndWait();
        return;
            }


    FeedbackService cs = new FeedbackService();
    cs.removeFeedback(c);
     FeedbackTable.setItems(cs.displayFeedbacks());
      String titleerr = " Opportunis";
        String messagerr = "Removed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));

    }

    @FXML
    private void statFeedback(ActionEvent event) {
    }
    
}
