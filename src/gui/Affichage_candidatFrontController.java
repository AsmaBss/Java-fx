/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import models.Skills;
import models.User;
import models.skills_candidat;
import org.controlsfx.control.Notifications;
import services.CandidatServices;
import services.SkillsCandidatServices;
import utils.DataSource;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Affichage_candidatFrontController implements Initializable {
     ObservableList<User> list = FXCollections.observableArrayList();
   


    private GridPane grid;
     private int size;
    private Button btn_ajout;
    @FXML
    private TableView<User> afficher_candidat;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> phone;
    @FXML
    private TableColumn<?, ?> fb;
    @FXML
    private TableColumn<?, ?> linkdin;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> level;
    @FXML
    private TableColumn<?, ?> birthday;
    @FXML
    private TableColumn<?, ?> img;
CandidatServices cn = new CandidatServices();
SkillsCandidatServices cs = new SkillsCandidatServices();
    private Button update;

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private Label labelll;
    @FXML
    private Button btn_follow;
    @FXML
    private Button btn_unfollow;
    @FXML
    private TableColumn<?, ?> follow;
    @FXML
    private Button btn_back;
    private ImageView imageUser;
    
    @FXML
    private Circle circle3;
    private TextField id_user;
 
   User u = UserSession.getInstance().getLoggedUser();
    @FXML
    private Button btn_event;
	
        
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         
       
        loadData();
        CandidatServices  SP = new CandidatServices();
       
      
//        list =  SP.afficher();
//        size = list.size(); 
//          int column = 1;
//            int row = 0;
//            try {
//                for (int i = 0; i < list.size(); i++) {
//                    FXMLLoader fxmlLoader = new FXMLLoader();
//                    fxmlLoader.setLocation(getClass().getResource("/gui/itemP.fxml"));
//                    AnchorPane anchorPane = fxmlLoader.load(); 
//                    System.out.print(list.get(i));
//                    ItemPController itemController = fxmlLoader.getController();
//                    itemController.setData(list.get(i));
//                               
//                    
//            
//             if(column>3)
//             { column=1;
//             row++;}
//                    
//                    grid.add(anchorPane, column, row); //(child,column,row)
//                    //set grid width
//                     grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE); 
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//                column++;     
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ObservableList data = FXCollections.observableArrayList();
//        ObservableList comboString = FXCollections.observableArrayList();
//
//        

       
//        ObservableList<Produit> list1 = FXCollections.observableArrayList();
//        
//        list1 = SP.afficherCombo((String) selectCat.getValue().toString());
//
//        selectCat.setItems(list1);

        
    }    
 public void loadData() {
        afficher_membre();
       
//         System.out.println(cn.userInfos.getId());
        
      
       
       
    }    

    private void ajout_candidat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Ajout_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    private void dashborad(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
     public void afficher_membre(){
            Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
                
        
        ObservableList<User> list =  cn.afficher();
   
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
          
        
        afficher_candidat.setItems(list); 
    }
     
   

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

    private void modifierClicked(MouseEvent event) throws IOException {
         User u = null;
		u = afficher_candidat.getSelectionModel().getSelectedItem();
	
		if(u != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/update_candidatFront.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          update.getScene().setRoot(parent1);
           Update_candidatFrontController controller = (Update_candidatFrontController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
    }


   
     private void test() {
         User candidat = null;
		candidat = afficher_candidat.getSelectionModel().getSelectedItem();
               
               
                if (cn.searchFollow(u,candidat)==true){
                     btn_follow.setVisible(true);
                    btn_unfollow.setVisible(false);
                     
                } else { 
                    btn_follow.setVisible(false);
                      btn_unfollow.setVisible(true);
                    
                   
                    }
                 loadData();
               
                
    }

    @FXML
    private void follow(ActionEvent event) {
         User candidat = null;
		candidat = afficher_candidat.getSelectionModel().getSelectedItem();
          
                    cn.follow(u, candidat);  
                  
                       loadData();
                    
          
                
    }

    @FXML
    private void unfollow(ActionEvent event) {
         User candidat = null;
		candidat = afficher_candidat.getSelectionModel().getSelectedItem();
          
                    cn.unfollow(u, candidat);  
                       loadData();
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
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    

   
}
