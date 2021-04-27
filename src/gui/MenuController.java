/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.User;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_candidat;
    @FXML
    private Button btn_direction;
    @FXML
    private Text labelll;

    /**
     * Initializes the controller class.
     */
User u = UserSession.getInstance().getLoggedUser();
    @FXML
    private Button btn_employers;
    @FXML
    private Button btn_logout;
    @FXML
    private Button offersbtn;
    @FXML
    private Button offersbtn1;
    @FXML
    private Circle circle3;
    @FXML
    private Button complaints;
    @FXML
    private Button article;
    @FXML
    private Button event;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelll.setText(u.getAddress());
        if (u.getRole().equals("[\"ROLE_ADMIN\"]"))
        {
        btn_direction.setVisible(true);
         
        }
        else {
             btn_direction.setVisible(false);
          
        
            
        }
   
    }  
    
    
    
	public void setUser(User u) {
		this.u = u;
	}
	
	public void refresh() {
		labelll.setText(u.getNom()+" "+u.getPrenom());
                
                

	}

    @FXML
    private void candidats(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage_candidatFront.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void direction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void employers(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void logout(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));

        try {
            Parent root = loader.load();
            btn_logout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
        
    }

    @FXML
    private void offers(ActionEvent event) {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../edu/esprit/gui/ShowJobFront.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
        
    }
    
}

    @FXML
    private void offers1(ActionEvent event) {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../edu/esprit/gui/ShowJobFront.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
        
    }
    
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void complaints(ActionEvent event) {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../edu/esprit/gui/displayComplaintFront"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
        
    }
    }

    @FXML
    private void article(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AfficherArticle.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void event(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AfficherEvenement.fxml"));

        try {
            Parent root = loader.load();
            btn_direction.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
}