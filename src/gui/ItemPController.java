/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.User;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ItemPController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    User u = new User();
    @FXML
    private Button view;
      public void setData(User u) {
        this.u = u;
        
        nom.setText(u.getAddress());
        prix.setText( u.getPassword());
        System.out.println(u.getImg());
        String i=u.getImg().substring(24);
        Image image = new Image(getClass().getResourceAsStream(i));
        img.setImage(image); 
    }
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    
    
}
