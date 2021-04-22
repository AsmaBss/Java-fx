/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Complaint;
import static edu.esprit.gui.DisplayAllController.id;
import static edu.esprit.gui.DisplayAllController.table2;
import edu.esprit.services.ComplaintService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class DisplayComplaintFrontController implements Initializable {

    
    AddComplaintFrontController aa = new AddComplaintFrontController();
    
    @FXML
    private TableView<Complaint> ComplaintTable;
    public static  TableView<Complaint> table2;
    
    @FXML
    private TableColumn<Complaint, String> titleComplaint;
    @FXML
    private TableColumn<Complaint, String> contentComplaint;
    @FXML
    private TableColumn<Complaint, String> statusComplaint;
    @FXML
    private Button addComplaintBtn;
    @FXML
    private Button updateComplaintBtn;
    @FXML
    private Button removeComplaintBtn;
    @FXML
    private Label label;
    
    public static  ObservableList<Complaint>oblist=FXCollections.observableArrayList();
    public static int id;
    @FXML
    private ImageView img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         table2 = ComplaintTable;
         
        
        ComplaintService cs = new ComplaintService();
        
        titleComplaint.setCellValueFactory(new PropertyValueFactory<>("title"));
        contentComplaint.setCellValueFactory(new PropertyValueFactory<>("content"));
        statusComplaint.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        ComplaintTable.setItems(cs.displayFront(1));
        //img.setImage(new Image(aa.ffile.toURI().toString()));
        
    }    

    @FXML
    private void detail(MouseEvent event) {
         Complaint c =table2.getSelectionModel().getSelectedItem();
    label.setText(c.getContent());
    }

    @FXML
    private void addComplaint(ActionEvent event) {
        
         try {
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addComplaintFront.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Write a complaint");
stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         
    }

    @FXML
    private void updateComplaint(ActionEvent event) {
        try {

        Complaint c =table2.getSelectionModel().getSelectedItem();
        if(c == null)
        {//msg alertt
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Update your complaint");
        alert.setHeaderText(null);
        alert.setContentText("Please select a complaint.");

        alert.showAndWait();
        return;
        
            }
            
            id =c.getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateComplaintFront.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Update your complaint");
stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));

             System.out.println(c);
             System.out.println(c.getId());

         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void removeComplaint(ActionEvent event) {
         Complaint c =ComplaintTable.getSelectionModel().getSelectedItem();
         System.out.println(c.getStatus());
            String h= "Treated";
           if(c== null)
        {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete complaint");
        alert.setHeaderText(null);
        alert.setContentText("Please select a complaint");

        alert.showAndWait();
        return;
            }
           else if (c.getStatus().equals(h))
        {System.out.println("equals");
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete complaint");
        alert.setHeaderText(null);
        alert.setContentText("You can't unsend a treated complaint.");

        alert.showAndWait();
        return;
            }


    ComplaintService cs = new ComplaintService();
    
        
    cs.removeComplaint(c);
    
     ComplaintTable.setItems(cs.displayFront(1));
      String titleerr = " Opportunis";
        String messagerr = "Removed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
}

    

    
    
    
    public int idd(){
        return id ;
    }
}
