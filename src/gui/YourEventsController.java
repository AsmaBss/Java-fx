/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import guiAshref.UpdateEvenementController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import models.Evenements;
import models.User;
import org.controlsfx.control.Notifications;
import services.EvenementsService;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class YourEventsController implements Initializable {

    
     EvenementsService en = new EvenementsService(); 
    @FXML
    private TableView<Evenements> afficher_evenement;
    @FXML
    private TableColumn<?, ?> clm_date;
    @FXML
    private TableColumn<?, ?> clm_adr;
    @FXML
    private TableColumn<?, ?> clm_titre;
    @FXML
    private TableColumn<?, ?> clm_desc;
    @FXML
    private TableColumn<?, ?> clm_part;
    @FXML
    private TableColumn<?, ?> clm_max;
    @FXML
    private TableColumn<?, ?> clm_img;
    @FXML
    private Button btn_del;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button eventEdit;
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
         afficher_event();
          
          Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }  
      User u = UserSession.getInstance().getLoggedUser(); 
 public void afficher_event(){
        
        ObservableList<Evenements> events =  en.afficherEvenementsUser(u);
   
        clm_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clm_adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clm_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        clm_desc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clm_part.setCellValueFactory(new PropertyValueFactory<>("nbr_participants"));
        clm_max.setCellValueFactory(new PropertyValueFactory<>("nbr_max"));
        clm_img.setCellValueFactory(new PropertyValueFactory<>("image1"));
        afficher_evenement.setItems(events);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
          Evenements e = null;
		e = afficher_evenement.getSelectionModel().getSelectedItem();
		if(e != null) {
			en.supprimerEvent(e);
                       
			Notifications n = Notifications.create() 
                              .title("SUCCESS")
                              .text("  Event suprrimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3)); 
               n.darkStyle();
               n.show();
			   afficher_event();
		}
    }

    @FXML
    private void add_event(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AjouterEvenement.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
                          
    }
    }

    @FXML
    private void modiferClicked(MouseEvent event) throws IOException {
          
        Evenements e = null;
		e = afficher_evenement.getSelectionModel().getSelectedItem();
		
		if(e != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/UpdateEvenement.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          eventEdit.getScene().setRoot(parent1);
           UpdateEvenementController controller = (UpdateEvenementController) loader.getController();
            controller.setEvent(e);
			controller.updateField();
    
		}

    }

    @FXML
    private void GoToProfile(MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
                          
    }
    }
    
}
