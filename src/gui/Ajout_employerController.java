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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.ControleSaisie;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import tray.notification.NotificationType;  
import tray.notification.TrayNotification; 
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Ajout_employerController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField company;
    @FXML
    private TextField categorie;
    @FXML
    private TextField town;
    @FXML
    private TextField linkdin;
    @FXML
    private TextField address;
    @FXML
    private TextField fb;
    @FXML
    private TextField phone;
    @FXML
    private TextArea description;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    @FXML
    private Button btn_ajout;
    
     String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private Button back;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = UserSession.getInstance().getLoggedUser();
	 Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }    

    @FXML
    private void ajout(ActionEvent event) {
        
CandidatServices cn = new CandidatServices();
   
       if (verifchamps() == true) {
           User c= new User() ;  
//            c.setDiscr("candidat");
           
       Image image1 = null;
                
            c.setCompany(company.getText());
            c.setCategorie(categorie.getText());
            String phone_number = phone.getText();
            c.setPhone(Integer.parseInt(phone_number));
            c.setFb(fb.getText());
            c.setLinkdin(linkdin.getText());
            c.setDescription(description.getText());
          
            c.setPassword(password.getText());
          
           
            c.setAddress(address.getText());
            c.setTown(town.getText());
            String image = imgUrl;

            c.setImg(image);
           
            String str="[\"ROLE_EMPLOYER\"]";
             c.setRole(str) ; 
             c.setDiscr("employer");
            
             
           // img_user.setImg(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + c.getImg())); 
            cn.ajouterEmployer(c);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
                          
          
    }
    }
    
    ControleSaisie cs = new ControleSaisie(); 
    TrayNotification tray = null; 

     private Boolean verifchamps() {
        if (company.getText().isEmpty() || categorie.getText().isEmpty()
                || password.getText().isEmpty() || address.getText().isEmpty()
                || phone.getText().isEmpty() 
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

       

        return true;
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

    @FXML
    private void back(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    
}

    @FXML
    private void GoToProfile(MouseEvent event) {
    }
    
}
