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
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.ControleSaisie;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Ajout_candidatFrontController implements Initializable {

    @FXML
    private Button btn_ajout;
    @FXML
    private TextField nom;
    @FXML
    private TextField fb;
    @FXML
    private TextField town;
    @FXML
    private TextField password;
    @FXML
    private TextField address;
    @FXML
    private TextField prenom;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField linkdin;
    @FXML
    private TextArea description;
    @FXML
    private TextField level;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;


    /**
     * Initializes the controller class.
     */
    CandidatServices cn = new CandidatServices();
    @FXML
    private Button btn_back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Candidat", "Job seeker");
    }    

    @FXML
    private void back(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
  ControleSaisie cs = new ControleSaisie(); 
    TrayNotification tray = null; 

    private Boolean verifchamps() {
        if (nom.getText().isEmpty() || prenom.getText().isEmpty()
                || password.getText().isEmpty() || address.getText().isEmpty()
                || phone.getText().isEmpty() || level.getText().isEmpty()
                || town.getText().isEmpty() || fb.getText().isEmpty() || linkdin.getText().isEmpty() || description.getText().isEmpty()) {
            tray = new TrayNotification("Erreur", "Il faut remplire tous les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;

        }
        if(!cs.isValidEmailAddress(address.getText()))
        {
            
            tray = new TrayNotification("Erreur", "Check your Email", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
         if(phone.getText().length()<8 ||phone.getText().length()>8||!cs.isInte(phone.getText()))
        {
            
            tray = new TrayNotification("Erreur", "Your phone number must be 8 numbers !", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
          if(!cs.isDate(date_naissance.getValue().toString()))
        {
            
            tray = new TrayNotification("Erreur", "Verify the date", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        

       

        return true;
    } 
    @FXML
    private void ajout(ActionEvent event) {
         if (verifchamps() == true) {
           User c= new User() ;  
//            c.setDiscr("candidat");
            
       Image image1 = null;
                
            c.setNom(nom.getText());
            c.setPrenom(prenom.getText());
            String phone_number = phone.getText();
            c.setPhone(Integer.parseInt(phone_number));
            c.setFb(fb.getText());
            c.setLinkdin(linkdin.getText());
            c.setDescription(description.getText());
            c.setNiv_etude(level.getText());
            c.setPassword(password.getText());
           Date datee = Date.valueOf(date_naissance.getValue() );
           c.setDate_naissance(datee); 
            c.setAddress(address.getText());
            c.setTown(town.getText());
            String image = imgUrl;

            c.setImg(image);
            String typee = (String) type.getValue();
            c.setType_candidat((String)type.getValue()) ; 
            String str="[\"ROLE_CANDIDATE\"]";
             c.setRole(str) ; 
             c.setDiscr("candidat");
             
            
             
           // img_user.setImg(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + c.getImg())); 
            cn.ajouter(c);
            Notifications n = Notifications.create() 
                              .title("Sucess")
                              .text("Candidat Added ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
        }else {
			Notifications n = Notifications.create()
                              .title("FAIL")
                              .text("Error, invalid field")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		}
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
}
    

