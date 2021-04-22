/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Complaint;
import edu.esprit.services.ComplaintService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class UpdateComplaintFrontController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private Button updateBtn;
    
    DisplayComplaintFrontController a = new DisplayComplaintFrontController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ComplaintService cs = new ComplaintService();

           Complaint cc = new Complaint();
                cc = cs.getComplaint(a.idd());
        System.out.println(cc);


       title.setText(String.valueOf(cc.getTitle()));
       content.setText(cc.getContent());

    }    

    @FXML
    private void update(ActionEvent event) {
        
         int ID = a.idd();

        String t =  title.getText();
        String c =  content.getText();
        
        
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure?","Update",JOptionPane.YES_NO_OPTION);
      if(opt==0){
            ComplaintService cs = new ComplaintService();
            cs.updateComplaintFront(String.valueOf(ID),t,c);
      }
            try {
            Parent root = FXMLLoader.load(getClass().getResource("DisplayComplaintFront.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Your Complaints");
            primaryStage.setScene(scene);

            primaryStage.show();
            Stage stage1 = (Stage) title.getScene().getWindow();
            stage1.close();

      } catch (IOException ex) {
         
        }
 String titleerr = " Opportunis";
        String messagerr = "Updated";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    
    }
    
}
