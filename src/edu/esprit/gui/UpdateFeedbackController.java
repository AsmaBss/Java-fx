/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Feedback;
import edu.esprit.services.FeedbackService;
import edu.esprit.services.FeedbackService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class UpdateFeedbackController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private TextField iduser;
    @FXML
    private TextField idoffer;
    @FXML
    private Button updatebtn;
    
    DisplayAllFeedbackController a = new DisplayAllFeedbackController();

        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FeedbackService fs = new FeedbackService();

           Feedback ff = new Feedback();
                ff = fs.getFeedback(a.iddf());
        System.out.println(ff);


       description.setText(String.valueOf(ff.getDescription()));
       iduser.setText(String.valueOf(ff.getIduser()));
       idoffer.setText(String.valueOf(ff.getIdoffer()));

      
    }    

    @FXML
    private void update(ActionEvent event) {
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate ld =  LocalDate.now();
        java.util.Date utilDate = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        int ID = a.iddf();

        String des =  description.getText();
        int iu = Integer.parseInt(iduser.getText());
        int io =  Integer.parseInt(idoffer.getText());
        
        
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure?","Update",JOptionPane.YES_NO_OPTION);
      if(opt==0){
            FeedbackService cs = new FeedbackService();
            cs.updateFeedback2(String.valueOf(ID),des,String.valueOf(sqlDate),String.valueOf(iu),String.valueOf(io));
      }
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

    }
    
}
