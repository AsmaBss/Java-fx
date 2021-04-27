/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.ControleSaisie;
import models.Skills;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import services.SkillsServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Ajout_skillController implements Initializable {

    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button back;
     String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private TextField libelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

SkillsServices cn = new SkillsServices();
    @FXML
    private void ajout(ActionEvent event) {
        
   
       if (verifchamps() == true) {
           Skills c= new Skills() ;  
//            c.setDiscr("candidat");
           
       Image image1 = null;
                
            c.setLibelle(libelle.getText());
           
           
            String image = imgUrl;

            c.setImg(image);
           
           
            
             
           // img_user.setImg(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + c.getImg())); 
            cn.ajouter(c);
            Notifications n = Notifications.create() 
                              .title("Sucess")
                              .text("Employer Added ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
        }else {
			Notifications n = Notifications.create()
                              .title("FAIL")
                              .text("Error, insert data")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		}
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_skills.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
                          
          
    }
    }

    @FXML
    private void back(ActionEvent event) {
    }
     @FXML
    private void addImage(ActionEvent event) {
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        uploadPic = new FileChooser();
        uploadPic.setTitle("Select the image you want to add");
        picPath = uploadPic.showOpenDialog(stage);
        System.out.println(picPath.toString());
        try {
            imgUrl = picPath.toURI().toURL().toExternalForm();

            BufferedImage buffImage = ImageIO.read(picPath);
            Image up = SwingFXUtils.toFXImage(buffImage, null); 
            imageToPost.setImage(up);
        } catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    ControleSaisie cs = new ControleSaisie(); 
    TrayNotification tray = null; 
 
    private Boolean verifchamps() {
        if (libelle.getText().isEmpty()  
                ) {
            tray = new TrayNotification("Erreur", "Il faut remplire tous les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;

        }

       

        return true;
    }
   
    
}

