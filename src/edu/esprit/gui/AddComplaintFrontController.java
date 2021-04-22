/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Complaint;
import edu.esprit.services.ComplaintService;
import java.awt.Desktop;
import static java.awt.Desktop.isDesktopSupported;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class AddComplaintFrontController implements Initializable {

    private Desktop desktop = Desktop.getDesktop();
    public File ffile;
    public String pathh;
    
    
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private Button addbtn;
    @FXML
    private ImageView att;
    @FXML
    private Label fname;
    @FXML
    private ImageView ImageField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(isDesktopSupported());
    }    

    @FXML
    private void send(ActionEvent event) {
        
        String t =  title.getText();
        String c =  content.getText();
        
        String[] lista ={"(?i)javafx","(?i)cow","(?i)dog","(?i)cat"};
       for (int i = 0; i < 4; i++) {
        t= t.replaceAll(lista[i], "****");
        c= c.replaceAll(lista[i], "****");

    }
       
        if (t.isEmpty())
            {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty field");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in the fields");

        alert.showAndWait();
        return;
            }
        
        
        
        Complaint cmp = new Complaint(t,c);
        
        ComplaintService cs = new ComplaintService();
        cs.addComplaintFront(cmp);
        String titleerr = " Opportunis";
        String messagerr = "complaint sent successfully";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titleerr);
        tray.setMessage(messagerr);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(4));
        
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
        
    }
    
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();}

    @FXML
    private void FileUpload(MouseEvent event) throws IOException {
        
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        File f = fc.showOpenDialog(stage);
        System.out.println(f);
        System.out.println(f.getAbsolutePath());

        if (f != null) {
                       //desktop.open(file);
                       //stage.display(file);
                       fname.setText(f.getName());
                       
           
           // InputStream is = null;
            //OutputStream os = null;
            try {
                InputStream is = new FileInputStream(new File(f.getAbsolutePath()));
                System.out.println(is);
               OutputStream os = new FileOutputStream(new File("F:/xampp/htdocs/imagePIDEV/" + f.getName()));
                System.out.println(os);
                
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

            } finally {
                //is.close();
                //os.close();

            }

            File file = new File("F:/xampp/htdocs/imagePIDEV/" + f.getName());
            System.out.println(file.toURI().toString());
            ImageField.setImage(new Image(file.toURI().toString()));
            //ImageComp = f.getName();
            //System.out.println(ImageComp);
            //imagePath.setText(ImageComp);
             
            String path = file.getAbsolutePath();
             System.out.println("path of the file=  "+path);
             ffile = file;
             pathh =path;
                    }
         else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Error !");
        }
        
        
        
    }
    
    
}
