/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.tools.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ela
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pc1;
    ObservableList <PieChart.Data> ol = FXCollections.observableArrayList();
    //ObservableList <PieChart.Data> oll = FXCollections.observableArrayList();
    @FXML
    private Label caption;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
           
        // pc1

        String requete = "SELECT complaint.status, Count(*) AS Nombre_de_Fois FROM complaint GROUP BY complaint.status";
        PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
        ResultSet rs =  pst.executeQuery(requete);
        
     
        while(rs.next()){
            
            ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                    pc1.setData(ol);
                    
                    pc1.setLegendSide(Side.LEFT);
                    
                   FadeTransition f = new FadeTransition(Duration.seconds(2),pc1);
                    f.setFromValue(0);
                    f.setToValue(1);
                    f.play();
        }
    
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        for (final PieChart.Data data : pc1.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + " complaints");
             }
        });
                    }
 
        
               
    }    
    
}
