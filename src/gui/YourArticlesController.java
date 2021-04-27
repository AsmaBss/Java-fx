/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import guiAshref.UpdateArticleController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import models.Article;
import models.User;
import services.ArticleService;
import utils.UserSession;
import models.Article;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class YourArticlesController implements Initializable {
 ArticleService as = new ArticleService();
    @FXML
    private TableView<Article> afficher_article; 
    @FXML
    private TableColumn<?, ?> clm_titre;
    @FXML
    private TableColumn<?, ?> clm_desc;
    @FXML
    private TableColumn<?, ?> clm_date;
    @FXML
    private TableColumn<?, ?> clm_img;
    @FXML
    private Button btn_del;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button eventEdit;
    @FXML
    private TextField search;
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
        afficher_art();
        Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }    
   User u = UserSession.getInstance().getLoggedUser(); 
   
    public void afficher_art(){
        
        ObservableList<Article> arts =  as.afficherArticleUser(u); 
   
        clm_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       
        clm_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        clm_date.setCellValueFactory(new PropertyValueFactory<>("date"));
  
        clm_img.setCellValueFactory(new PropertyValueFactory<>("image1"));
        afficher_article.setItems(arts);
    }
     
    @FXML
    private void deleteArticle(ActionEvent event) {
          Article a = null;
		a = afficher_article.getSelectionModel().getSelectedItem();
		if(a != null) {
			as.supprimerArticle(a);
                       
			Notifications n = Notifications.create() 
                              .title("SUCCESS")
                              .text("  Article suprrimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3)); 
               n.darkStyle();
               n.show();
			   afficher_art();
		}
    
    }

    @FXML
    private void add_article(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AjouterArticle.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void modiferClicked(MouseEvent event) throws IOException {
         Article a = null;
		a = afficher_article.getSelectionModel().getSelectedItem();
		System.out.print(a);
		if(a != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/UpdateArticle.fxml"));
			 
                    
      
  Parent parent1 = loader.load();
  System.out.print("aaa");

       
          eventEdit.getScene().setRoot(parent1);
           UpdateArticleController controller = (UpdateArticleController) loader.getController();
            controller.setArt(a);
            controller.updateField();
    
		} 
        
        
    }

    @FXML
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
    private void searchKeyRelaesed(KeyEvent event) {
            afficher_article.getItems().setAll(as.searchArticle(search.getText()));
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
    }

   

  
    @FXML
    private void back(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
}
