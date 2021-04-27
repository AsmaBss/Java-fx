/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAshref;


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
 * @author bensa
 */
public class AfficherEvenementController implements Initializable {

    EvenementsService en = new EvenementsService(); 
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
    private TableView<Evenements> afficher_evenement;
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

    /**
     * Initializes the controller class.
     */
       User u = UserSession.getInstance().getLoggedUser(); 
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_participer;
    @FXML
    private Button back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
                
                if (u.getRole().equals("[\"ROLE_ADMIN\"]")){
                    btn_participer.setVisible(true);
                    btn_annuler.setVisible(true);
                    btn_ajout.setVisible(true);
                    eventEdit.setVisible(true);
                    btn_del.setVisible(true);
                }
                else {
                     btn_participer.setVisible(true);
                    btn_annuler.setVisible(true);
                    btn_ajout.setVisible(false);
                    eventEdit.setVisible(false);
                    btn_del.setVisible(false);
                }
     loadData();
    }
    public void loadData() {
        afficher_event();
       
    }    

    
    
      public void afficher_event(){
        
        ObservableList<Evenements> events =  en.afficherEvenements();
   
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }

        
    }

    @FXML
    private void modiferClicked(MouseEvent event) throws IOException {
        
        
        Evenements e = null;
		e = afficher_evenement.getSelectionModel().getSelectedItem();
		
		if(e != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateEvenement.fxml"));
			 
       
       
      
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
    private void Attend(ActionEvent event) {
        
        Evenements e = null;
       e = afficher_evenement.getSelectionModel().getSelectedItem();
      
       en.participation(e,u);  
        afficher_event();
        
        
    }

    @FXML
    private void Unattend(ActionEvent event) {
        
         Evenements e = null;
       e = afficher_evenement.getSelectionModel().getSelectedItem();
      
       en.Annuler_participation(e,u);  
        afficher_event();
        
    }

    @FXML
    private void back(ActionEvent event) {
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menu.fxml"));

        try {
            Parent root = loader.load();
             afficher_evenement.getScene().setRoot(root);
        } catch (IOException ex) {
            
        }
        
        
    }
    
    
    
}
