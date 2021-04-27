/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.User;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ItemUController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text nom;
    @FXML
    private Text prix;

    /**
     * Initializes the controller class.
     */
     User u = new User();
     
      public void setData(User u) {
        this.u = u;
        
        nom.setText(u.getAddress());
        prix.setText( u.getTown());
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
