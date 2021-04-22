/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import edu.esprit.entities.Complaint;
import edu.esprit.services.ComplaintService;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ela
 */



public class TestController implements Initializable {

    
    
    ObservableList<String> data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
    
    
    @FXML
    private ListView<String> listView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      // ObservableList<String> names = FXCollections.observableArrayList(
          //"Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
            //listView.setItems(names);
            
            ComplaintService cs = new ComplaintService();
            listView.setItems(cs.displayComplaintsFront());

       
                
    }    
    
}
