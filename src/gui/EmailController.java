/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EmailController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Button btn_submit;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;
    @FXML
    private Button btn_back;

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
    User u = new User();
    
    public void setUser(User u)
    {
        this.u=u;
    }

    @FXML
    private void submit(ActionEvent event) {
        System.out.println(u.getAddress());
          
      
//            String cin = tf_cin_recu_pwd.getText();
//            int cinn = Integer.parseInt(cin);
//        
//            if(ms.RecupPwd(cinn)==1){
//            
//                //pour afficher le code generateur dans notif
//                String str = Integer.toString(x);
//
////        TrayNotification tray = null;
////        tray = new TrayNotification("bien", str, NotificationType.SUCCESS);
////        tray.showAndDismiss(Duration.seconds(5));
//            
//            
//            UserSession.setInstance(cinn);
//            //pour SMS
////            SMS sms =new SMS();
////            String num_tel ="+216"+ UserSession.getInstance().getTelephone();
////            System.out.println(num_tel);
////            sms.SendSMS("mohamedd55", "aRTBEN55991044", str, num_tel, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
////            System.out.println("test sms");
//        
        try{
            String host ="smtp.gmail.com" ;
            String user = "mariem.azouz@esprit.tn";
            String pass = "hesoyam123";
            String to =u.getAddress() ;
            String from =UserSession.getInstance().getAddress();
            String subject = "Reasons for your block ! ";
            String messageText = "Dear " +u.getNom()+", \n"+description.getText();
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null); 
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
            TrayNotification tray = null;
        tray = new TrayNotification("message sent successfully",UserSession.getInstance().getAddress() , NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_submit.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
        
    
        
      
    
    
}

    @FXML
    private void GoToProfile(MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
}
