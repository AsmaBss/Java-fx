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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class AddComplaintController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextArea content;
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
    private void send(ActionEvent event) throws IOException {
        
        

        
        String t =  title.getText();
        String c =  content.getText();
        //int iu = Integer.parseInt(iduser.getText());
        
        String[] lista ={"(?i)java","(?i)cow","(?i)dog","(?i)cat"};
       for (int i = 0; i < 4; i++) {
           //t.equalsIgnoreCase(t)
          // if (t.contains(lista[i]))
        t= t.replaceAll(lista[i], "****");
        //t = Regex.Replace(t,lista[i],"***", RegexOptions.IgnoreCase);
        c= c.replaceAll(lista[i], "****");

    }
        
        if (t.isEmpty() || c.isEmpty())
            {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty field");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in the fields");

        alert.showAndWait();
        return;
            }
        
        Complaint cmp = new Complaint(t,c,0);
        
        ComplaintService cs = new ComplaintService();
        cs.addComplaint(cmp);
        
     /*
           
            Parent root = FXMLLoader.load(getClass().getResource("DisplayAll.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);

            primaryStage.show();


*/
            Stage stage1 = (Stage) title.getScene().getWindow();
            stage1.close();
            
             
             

      
      String titleerr = " Opportunis";
        String messagerr = "Added successfully";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));   
        
    }
    
}
