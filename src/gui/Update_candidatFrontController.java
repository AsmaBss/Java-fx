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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Update_candidatFrontController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button btn_update;
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
      
    String imgUrl  = "";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private Circle circle;
    @FXML
    private Text labelll;
    @FXML
    private Button btn_candidat;

    /**
     * Initializes the controller class.
     */
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Candidat", "Job seeker");
         User u = UserSession.getInstance().getLoggedUser();
       Image image ;
                image = new Image(u.getImg());
                
                
               
                circle.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }
 public void setUser(User u) {
		this.u = u;
	}
     private AnchorPane ap;    

    @FXML
    private void backToAffichage(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
           back.getScene().setRoot(root);
        } catch (IOException ex) {
                          
          
          
		
    }
    }
User u = new User(); 
    @FXML
    private void update_candidat(MouseEvent event) {
         CandidatServices cs = new CandidatServices(); 
		
		if (!nom.getText().equals("") && !prenom.getText().equals("")
				&& !phone.getText().equals("") && !address.getText().equals("") 
				&& !password.getText().equals("") && !fb.getText().equals("") &&
                        !linkdin.getText().equals("") && !town.getText().equals("") && !description.getText().equals("") && !level.getText().equals("")) {
          
           
          String str="[\"ROLE_CANDIDATE\"]";
          String image =imgUrl;
				cs.modifier(new User(u.getId(), nom.getText(),prenom.getText(),
						 address.getText(), password.getText(), Date.valueOf(date_naissance.getValue()), 
						 town.getText(),fb.getText(),linkdin.getText(),type.getValue(),image,level.getText(),Integer.parseInt(phone.getText()),
                                        description.getText(), "candidat", null , null, str, 0
                                     
				));
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User Modifié")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
          
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
            btn_update.getScene().setRoot(root);
        } catch (IOException ex) {
                          
          
          
		
    }
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
     public void updateField() throws IOException {
		nom.setText(u.getNom());
		prenom.setText(u.getPrenom());
                String phone_number = Integer.toString(u.getPhone());
 
		phone.setText(phone_number);
		address.setText(u.getAddress());
		password.setText(u.getPassword());
                description.setText(u.getDescription());
                fb.setText(u.getFb());
                linkdin.setText(u.getLinkdin());
                level.setText(u.getNiv_etude());
                type.setValue(u.getType_candidat());
                
                town.setText(u.getTown());
                Image image ;
                image = new Image(u.getImg());
        
        this.imageToPost.setImage(image);
                
                Date d = u.getDate_naissance();
              
               date_naissance.setValue(d.toLocalDate());
               AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Affichage_candidatFront.fxml"));
              
               
              
                
		
	}

    @FXML
    private void candidat(ActionEvent event) {
        
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_update.getScene().setRoot(root);
        } catch (IOException ex) {
                          
          
          
		
    }
    }
    }
    

