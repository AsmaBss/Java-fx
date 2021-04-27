/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.ControleSaisie;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
 
import org.apache.commons.codec.digest.DigestUtils; 
import utils.UserSession;
 
/**
 * FXML Controller class
 *
 * @author hp
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane aaa;

    @FXML
    private TextField address;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button btn_candidat;
    @FXML
    private Button btn_employer;
    @FXML
    private Button btn_forget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void login(MouseEvent event) throws IOException {
      
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
//          CandidatServices sU = new CandidatServices ();
//		User u = sU.login(address.getText(), password.getText());  
//		if (u.getId() > -1 && u.getEtat()==0) {
//                        sU.userInfos = u; 
//                          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));
//
//        try {
//            Parent root = loader.load();
//            login.getScene().setRoot(root);
//        } catch (IOException ex) {
//           
//    }
//    
////			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));               
////			Parent parent = loader.load();  
////			aaa.getChildren().setAll(parent);   
//
//           Affichage_candidatFrontController controller = (Affichage_candidatFrontController) loader.getController();
//            controller.setUser(u); 
//			controller.refresh();
//                        controller.getIdUser(u.getId());
//		} else if ( u.getEtat()==1) {
//                    Notifications n = Notifications.create()
//                              .title("FAIL")
//                              .text("  you are blocked !!!! ")
//                              .position(Pos.TOP_CENTER)
//                              .hideAfter(Duration.seconds(1));
//               n.darkStyle();
//               n.show();
//
//                    
//                }
//		else {
//			Notifications n = Notifications.create()
//                              .title("SUCCESS")
//                              .text("  Incorrect Email or Password! ")
//                              .position(Pos.TOP_CENTER)
//                              .hideAfter(Duration.seconds(1));
//               n.darkStyle();
//               n.show();
//		}


CandidatServices ms = new CandidatServices ();
          if(verifchampsMembre()==true)
        {
        String email = address.getText();
       // String pwd = DigestUtils.shaHex(password.getText());//crypt
        UserSession.setInstance(email); 
        ms.loginMembre(email,password.getText());
        if(ms.loginMembre(email,password.getText())==1)
        {
            TrayNotification tray = null;
             User u = UserSession.getInstance().getLoggedUser();
             if(u.getEtat()==1){
                  tray = new TrayNotification("FAIL", "you are blocked !!!"+ UserSession.getInstance().getNom(), NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
                 
             }else{
            tray = new TrayNotification("welcom back", "Nice to see you  "+ UserSession.getInstance().getNom(), NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));               
			Parent parent = loader.load();  
			aaa.getChildren().setAll(parent);   
             }
        }
        else
        {
            System.out.println(ms.loginMembre(email,password.getText()));
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        }
        }
    }
    
     ControleSaisie css =new ControleSaisie();
    private Boolean verifchampsMembre()
    {
        if(address.getText().isEmpty() || password.getText().isEmpty())
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        
        if(!css.isValidEmailAddress(address.getText()))
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre email  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
            
        }
        
        
        return true;
        
    }

    @FXML
    private void registerAsCandidat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ajout_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void registerAsEmployer(ActionEvent event) {
    }

    @FXML
    private void forget_password(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/forgetPassword.fxml"));

        try {
            Parent root = loader.load();
            btn_forget.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
}
