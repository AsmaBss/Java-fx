/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import models.Skills;
import models.User;
import models.skills_candidat;
import services.CandidatServices;
import services.SkillsCandidatServices;
import utils.DataSource;
import edu.esprit.tools.MyConnection;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Profile_userController implements Initializable {

    @FXML
    private Text address;
    @FXML
    private Circle circle;
    @FXML
    private Text labelll;
    @FXML
    private Text description;
    @FXML
    private Text phone;
    @FXML
    private Text town;
    @FXML
    private Text fb;
    @FXML
    private Text linkdin;
    @FXML
    private Circle circle4;
    @FXML
    private TableColumn<?, ?> skills;
    @FXML
    private TableView<Skills> affichageCandidatSkill;
    private Text id_user;

    /**
     * Initializes the controller class.
     */
     User u = UserSession.getInstance().getLoggedUser();
    @FXML
    private ComboBox<?> selectSkill;
    @FXML
    private Button add;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_candidat;
    @FXML
    private Button update;
    @FXML
    private Button update1;
    @FXML
    private Button btn_events;
    @FXML
    private Button btn_articles;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Image image ;
                image = new Image(u.getImg());
               
                circle.setFill(new ImagePattern(image));
               labelll.setText(u.getAddress());
              
                address.setText(u.getAddress());
              
            
                description.setText(u.getDescription());
                
                phone.setText(String.valueOf(u.getPhone())); 
                town.setText(u.getTown());
                fb.setText(u.getFb());
                linkdin.setText(u.getLinkdin());
                 Image image1 ;
                image1 = new Image(u.getImg());
               
                circle4.setFill(new ImagePattern(image1));
                afficher_skill();
                ObservableList data = FXCollections.observableArrayList();
        ObservableList comboString = FXCollections.observableArrayList();
  
                java.sql.Connection cnx = MyConnection.getInstance().getCnx(); 
        String query = "Select libelle from skills";
        Statement st;
        ResultSet rs;
try {
             st=cnx.createStatement();
            rs=st.executeQuery(query);

             
            while (rs.next()) {
                        data.add(new Skills(rs.getString("libelle"))); 
                        comboString.add(rs.getString("libelle")); 
                        
                                 }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        selectSkill.setItems(null);
        selectSkill.setItems(comboString);
    }   
  
     public void setUser(User u) {
		this.u = u;
	}
	
	public void refresh() {
		labelll.setText(u.getAddress());
                address.setText(u.getAddress());
              
            
                description.setText(u.getDescription());
                
                phone.setText(String.valueOf(u.getPhone())); 
                town.setText(u.getTown());
                fb.setText(u.getFb());
                linkdin.setText(u.getLinkdin());
                Image image ;
                image = new Image(u.getImg());
               
                circle.setFill(new ImagePattern(image));
                 Image image1 ;
                image1 = new Image(u.getImg());
               
                circle4.setFill(new ImagePattern(image1));
                id_user.setText(String.valueOf(u.getId())); 
                

	}
        
        SkillsCandidatServices cs = new SkillsCandidatServices();
        CandidatServices cn = new CandidatServices();
         public void afficher_skill(){
      
               
        ObservableList<Skills> list =  cs.afficher_skills(u);
   
        skills.setCellValueFactory(new PropertyValueFactory<>("image1"));
       // candidat.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        
        affichageCandidatSkill.setItems(list);  
    }

    @FXML
    private void handleSearchBySkill(ActionEvent event) {
    }

    @FXML
    private void add_skill(ActionEvent event) {
         String skill= (String) selectSkill.getValue();
         Skills s = new Skills();
         
         s = cs.getSkill(skill); 
      
		
           
                skills_candidat sc = new skills_candidat(s.getId(),u.getId());   
                cs.ajouter(sc);
                afficher_skill();
    }

    @FXML
    private void delete(ActionEvent event) {
        
         Skills s = null;
		s = affichageCandidatSkill.getSelectionModel().getSelectedItem();
                   skills_candidat sc = new skills_candidat(s.getId(),u.getId());   
                cs.supprimer(sc);
                  afficher_skill();
    }

    @FXML
    private void candidat(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
                          
    }
    }

    @FXML
    private void modifierClicked(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
      
	
		if(u != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/update_candidatFront.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          update1.getScene().setRoot(parent1);
           Update_candidatFrontController controller = (Update_candidatFrontController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
    }

    @FXML
    private void events(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/YourEvents.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
                          
    }
    }

    @FXML
    private void articles(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/YourArticles.fxml"));

        try {
            Parent root = loader.load();
            btn_articles.getScene().setRoot(root);
        } catch (IOException ex) {
                          
    }
    }

}
