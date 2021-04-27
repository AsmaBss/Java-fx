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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import models.ControleSaisie;
import models.Skills;
import models.User;
import org.controlsfx.control.Notifications;
import services.SkillsServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Affichage_skillsController implements Initializable {

    @FXML
    private TableColumn<Skills, String> libelle;
    @FXML
    private TableColumn<Skills, String> img;
    @FXML
    private TableView<Skills> afficher_skills;
    SkillsServices cn = new SkillsServices();
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_delete;
    @FXML
    private Button update;
    @FXML
    private Button affichage_candidat;
    private Button btn_employer;
    @FXML
    private Button btn_skills;
    @FXML
    private Button btn_stat;
    @FXML
    private TextField search;
    @FXML
    private TextField tf_libelle;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    @FXML
    private Button btn_update;
     String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private Button btn_back;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
         User u = UserSession.getInstance().getLoggedUser();
	 Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }    
    public void loadData() {
        afficher_membre();
       
    }     
     public void afficher_membre(){
        
        ObservableList<Skills> list =  cn.afficher_skills();
   
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       
          img.setCellValueFactory(new PropertyValueFactory<>("image1"));
        afficher_skills.setItems(list);
    }

    @FXML
    private void ajoutCandidat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Ajout_skill.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void supprimer_candidat(ActionEvent event) {
         Skills u = null;
		u = afficher_skills.getSelectionModel().getSelectedItem();
		if(u != null) {
			cn.supprimer(u);
                       
			Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  skill supprimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
			   afficher_membre();
		}
    }

    @FXML
    private void modifierClicked(MouseEvent event) {
    }

    @FXML
    private void affichage_candidat(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            affichage_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    private void ajout_employer(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_employer.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    

    @FXML
    private void affichage_skills(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_skills.fxml"));

        try {
            Parent root = loader.load();
            affichage_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void affichage_statistique(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/statUser.fxml"));

        try {
            Parent root = loader.load();
            btn_stat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
    private void searchKeyRelaesed(KeyEvent event) {
        afficher_skills.getItems().setAll(cn.search(search.getText()));
    }

    
    @FXML
    private void update_employer(MouseEvent event) {
    }
    ControleSaisie cs = new ControleSaisie(); 
    TrayNotification tray = null; 

    private Boolean verifchamps() {
        if (tf_libelle.getText().isEmpty()) {
            tray = new TrayNotification("Erreur", "Il faut remplire tous les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;

        }
       

       

        return true;
    }
   @FXML
    public void SelectRow(){
        int id=afficher_skills.getSelectionModel().getSelectedItem().getId();
        tf_libelle.setText((afficher_skills.getSelectionModel().getSelectedItem().getLibelle()));
        
        
//        Image image;
//        String img = ((Skills)this.afficher_skills.getSelectionModel().getSelectedItem()).getImg();
//        image = new Image(img);
//        imageToPost.setImage(image);


    }

    @FXML
    private void btn_update(ActionEvent event) {
        int id =afficher_skills.getSelectionModel().getSelectedItem().getId();
       String libelle = tf_libelle.getText();
          String image =imgUrl;
        
       cn.modifier(new Skills(id,libelle,image));
       afficher_membre();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menu.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
    }
}
