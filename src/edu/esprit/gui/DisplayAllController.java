/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Complaint;
import edu.esprit.services.ComplaintService;
import edu.esprit.services.UserService;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
public class DisplayAllController implements Initializable {

    @FXML
    private TableView<Complaint> ComplaintTable;
    
    public static  TableView<Complaint> table2;
    
    
    private TableColumn<Complaint, String> idComplaint;
    @FXML
    private TableColumn<Complaint, String> titleComplaint;
    @FXML
    private TableColumn<Complaint, String> contentComplaint;
    @FXML
    private TableColumn<Complaint, String> userComplaint;
    @FXML
    private TableColumn<Complaint, String> statusComplaint;
    @FXML
    private Button addComplaintBtn;
    @FXML
    private Button updateComplaintBtn;
    @FXML
    private Button removeComplaintBtn;
    @FXML
    private Button statBtn;

    public static  ObservableList<Complaint>oblist=FXCollections.observableArrayList();
    public static int id;
    public static String x;

    @FXML
    private Label label;
    @FXML
    private Button treatBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table2 = ComplaintTable;
       UserService us = new UserService();
        ComplaintService cs = new ComplaintService();
        
        titleComplaint.setCellValueFactory(new PropertyValueFactory<>("title"));
        contentComplaint.setCellValueFactory(new PropertyValueFactory<>("content"));
        userComplaint.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        statusComplaint.setCellValueFactory(new PropertyValueFactory<>("status"));
        
       // statusComplaint.setStyle("-fx-text-fill: blueviolet");
        
        ComplaintTable.setItems(cs.displayComplaints());
    
    }    

    @FXML
    private void addComplaint(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addComplaint.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("New Complaint");
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
        alert.setTitle("Update complaint");
        alert.setHeaderText(null);
        alert.setContentText("Please select a complaint.");

        alert.showAndWait();
        return;
        
            }
            
            id =c.getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateComplaint.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Update complaint");
            stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));


             System.out.println(c);
             System.out.println(c.getId());

         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public int idd(){
        return id ;
    }

    @FXML
    private void removeComplaint(ActionEvent event) {
        
        Complaint c =ComplaintTable.getSelectionModel().getSelectedItem();
           if(c== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete complaint");
        alert.setHeaderText(null);
        alert.setContentText("Please select a complaint");

        alert.showAndWait();
        return;
            }


    ComplaintService cs = new ComplaintService();
    cs.removeComplaint(c);
     ComplaintTable.setItems(cs.displayComplaints());

    }

    @FXML
    private void statComplaint(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Statistics");
            stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void detail(MouseEvent event) {
        
    Complaint c =table2.getSelectionModel().getSelectedItem();
    label.setText(c.getContent());


    }

    @FXML
    private void treat(ActionEvent event) {
      

        Complaint c =table2.getSelectionModel().getSelectedItem();
        if(c == null)
        {//msg alertt
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Treat complaint");
        alert.setHeaderText(null);
        alert.setContentText("Please select a complaint.");

        alert.showAndWait();
        return;
        }
            
        int idd =c.getId();
        String title =  c.getTitle();
        String content =  c.getContent();
        int iu = c.getIduser();
        String status = "Treated";
        
        
      System.out.println(c);
        
         
            ComplaintService cs = new ComplaintService();
            //cs.updateComplaint2(String.valueOf(idd),title,content,status);
            cs.treat(String.valueOf(idd),title,content,String.valueOf(iu),status);

            ComplaintTable.setItems(cs.displayComplaints());

       String titleerr = " Opportunis";
        String messagerr = "Done";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
            

    }

    }    
        
       

    

