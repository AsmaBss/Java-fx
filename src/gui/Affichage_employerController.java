/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import models.User;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Affichage_employerController implements Initializable {

    @FXML
    private TableView<User> afficher_candidat;
    @FXML
    private TableColumn<User, String> company;
    @FXML
    private TableColumn<User, String> categorie;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> fb;
    @FXML
    private TableColumn<User, String> linkdin;
    @FXML
    private TableColumn<User, String> img;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_delete;
    @FXML
    private Button update;
    
    CandidatServices cn = new CandidatServices();
    @FXML
    private Button affichage_candidat;
    @FXML
    private Button btn_skills;
    @FXML
    private TextField search;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_candidat;
    
     User u = UserSession.getInstance().getLoggedUser();
    @FXML
    private ImageView img_delete;
    @FXML
    private ImageView img_edit;
    @FXML
    private ImageView img_ajout;
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
        String e = "[\"ROLE_ADMIN\"]";
        
        if (u.getRole().equals(e))
        {
        img_delete.setVisible(true);
          img_edit.setVisible(true);
          btn_candidat.setVisible(true);
         img_ajout.setVisible(true);
         affichage_candidat.setVisible(true);
        }
        else {
             img_delete.setVisible(false);
          img_edit.setVisible(false);
           btn_candidat.setVisible(false);
            img_ajout.setVisible(false);
             affichage_candidat.setVisible(false);
        
            
        }
       
    }        
      public void afficher_membre(){
        
        ObservableList<User> list =  cn.afficherEmployer();
   
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        email.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        fb.setCellValueFactory(new PropertyValueFactory<>("fb"));
         linkdin.setCellValueFactory(new PropertyValueFactory<>("linkdin"));
          img.setCellValueFactory(new PropertyValueFactory<>("image1"));
        afficher_candidat.setItems(list);
    }

    @FXML
    private void ajoutCandidat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ajout_employer.fxml"));

        try {
            Parent root = loader.load();
            affichage_candidat.getScene().setRoot(root);
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/update_employer.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          update.getScene().setRoot(parent1);
           Update_employerController controller = (Update_employerController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
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
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
    private void searchKeyRelaesed(KeyEvent event) {
        afficher_candidat.getItems().setAll(cn.search(search.getText()));
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

    @FXML
    private void candidat(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
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
