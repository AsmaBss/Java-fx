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
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class AddFeedbackController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private TextField iduser;
    @FXML
    private TextField idoffer;
    @FXML
    private Button addbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
        
        String des =  description.getText();
        
        String[] lista ={"(?i)javafx","(?i)cow","(?i)dog","(?i)cat"};
       for (int i = 0; i < 4; i++) {
        des= des.replaceAll(lista[i], "****");}
        
        
        
        
        
        LocalDate ld =  LocalDate.now();
        java.util.Date utilDate = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        int iu = Integer.parseInt(iduser.getText());
        String io =  idoffer.getText();
        
        Feedback fdb = new Feedback(des,sqlDate,iu,io);
        
        FeedbackService fs = new FeedbackService();
        fs.addFeedback(fdb);
        
       try {
            Parent root = FXMLLoader.load(getClass().getResource("DisplayAllFeedback.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);

            primaryStage.show();
            Stage stage1 = (Stage) description.getScene().getWindow();
            stage1.close();

      } catch (IOException ex) {
          
        }
         String titleerr = " Opportunis";
        String messagerr = "Feedback sent";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }
    
}
