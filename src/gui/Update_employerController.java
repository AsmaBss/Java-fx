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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Update_employerController implements Initializable {

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
    private Button btn_update;
    @FXML
    private Button back;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;

    
    String imgUrl  = "";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private Button btn_skills;
    @FXML
    private Button btn_candidat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
User u = new User();
    @FXML
    private void update_employer(MouseEvent event) {
          CandidatServices cs = new CandidatServices(); 
		
		if ( !phone.getText().equals("") && !address.getText().equals("") 
				&& !password.getText().equals("") && !fb.getText().equals("") &&
                        !linkdin.getText().equals("") && !town.getText().equals("") && !description.getText().equals("") && !company.getText().equals("") && !categorie.getText().equals("")) {
          
           
          String str="[\"ROLE_EMPLOYER\"]";
          String image =imgUrl;
				cs.modifierEmployer(new User(u.getId(), null,null,
						 address.getText(), password.getText(), null, 
						 town.getText(),fb.getText(),linkdin.getText(),null,image,null,Integer.parseInt(phone.getText()),
                                        description.getText(), "employer", company.getText() , categorie.getText(), str, 0
                                     
				));
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User Modifié")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
          
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
                          
          
    }
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
     public void setUser(User u) {
		this.u = u;
	}
     private AnchorPane ap;
    
    public void updateField() throws IOException {
		
                String phone_number = Integer.toString(u.getPhone());
 
		phone.setText(phone_number);
		address.setText(u.getAddress());
		password.setText(u.getPassword());
                description.setText(u.getDescription());
                fb.setText(u.getFb());
                linkdin.setText(u.getLinkdin());
               company.setText(u.getCompany());
                categorie.setText(u.getCategorie());
                
                town.setText(u.getTown());
               
                
           
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_update.getScene().setRoot(root);
        } catch (IOException ex) {
                          
          
          
		
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

    @FXML
    private void affichage_skills(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_skills.fxml"));

        try {
            Parent root = loader.load();
            btn_skills.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void candidat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
}
