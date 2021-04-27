/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.Pair;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AffichageController implements Initializable {

    @FXML
    private Button btn_ajout;

    /**
     * Initializes the controller class.
     */
     CandidatServices cn = new CandidatServices();
    @FXML
    private TableView<User> afficher_candidat;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, Integer> phone;
    @FXML
    private TableColumn<User, String> fb;
    @FXML
    private TableColumn<User, String> linkdin;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> level;
    @FXML
    private TableColumn<User, Date> birthday;
    @FXML
    private TableColumn<User, String> img;
    @FXML
    private Button btn_delete;
    private Label test;
    @FXML
    private Button update;
    @FXML
    private Button btn_employer;
    @FXML
    private Button btn_skills;
    @FXML
    private Button btn_stat;
    @FXML
    private TextField search;
    private Button btn_front;
    @FXML
    private Button btn_block;
    @FXML
    private Button btn_unblock;
    @FXML
    private TableColumn<?, ?> follow;
    @FXML
    private Button btn_back;
    @FXML
    private TableColumn<?, ?> block;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;
    @FXML
    private AnchorPane aaa;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_article;
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
        
        ObservableList<User> list =  cn.afficherBack();
   
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        fb.setCellValueFactory(new PropertyValueFactory<>("fb"));
         linkdin.setCellValueFactory(new PropertyValueFactory<>("linkdin"));
          type.setCellValueFactory(new PropertyValueFactory<>("type_candidat"));
           level.setCellValueFactory(new PropertyValueFactory<>("niv_etude"));
         birthday.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
          img.setCellValueFactory(new PropertyValueFactory<>("image1"));
           follow.setCellValueFactory(new PropertyValueFactory<>("nbr_follow")); 
            block.setCellValueFactory(new PropertyValueFactory<>("block")); 
        afficher_candidat.setItems(list);
    }
    @FXML
    private void ajoutCandidat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ajout_candidat.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    
}

    @FXML
    private void supprimer_candidat(ActionEvent event) {
        User u = null;
		u = afficher_candidat.getSelectionModel().getSelectedItem();
		if(u != null) {
			cn.supprimer(u);
                       
			Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User suprrimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
			   afficher_membre();
		}
    }

    @FXML
    private void modifierClicked(MouseEvent event) throws IOException {
        User u = null;
		u = afficher_candidat.getSelectionModel().getSelectedItem();
		
		if(u != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/update_candidat.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          update.getScene().setRoot(parent1);
           update_candidatController controller = (update_candidatController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
    }

    @FXML
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
            afficher_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void affichage_statistique(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/statUser.fxml"));

        try {
            Parent root = loader.load();
            afficher_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
    private void searchKeyRelaesed(KeyEvent event) {
         afficher_candidat.getItems().setAll(cn.searchCandidat(search.getText()));
    }

     private Stage primaryStage;
     @FXML
    private void pdf_coach(ActionEvent event) {
        //imprim
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.afficher_candidat;
           job.printPage(root);
           job.endJob();
     
    }
    
    
    
}

    private void front(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_front.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void block(ActionEvent event) throws IOException {
          User candidat= null;
          User u = new User();
		candidat = afficher_candidat.getSelectionModel().getSelectedItem();
                cn.block(candidat);
               
                
                
                   loadData();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/email.fxml"));               
			Parent parent = loader.load();   
			aaa.getChildren().setAll(parent);   

           EmailController controller = (  EmailController) loader.getController();
            controller.setUser(candidat); 
             

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
    private void unblock(ActionEvent event) {
         User candidat= null;
		candidat = afficher_candidat.getSelectionModel().getSelectedItem();
                cn.unblock(candidat);
                loadData();
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void events(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AfficherEvenement.fxml"));

        try {
            Parent root = loader.load();
            btn_event.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void articles(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AfficherArticle.fxml"));

        try {
            Parent root = loader.load();
            btn_event.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
}

