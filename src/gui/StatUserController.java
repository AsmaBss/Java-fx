/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.User;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class StatUserController implements Initializable {

    @FXML
    private PieChart statMembre;
    private Button btn_employer;
    @FXML
    private Button btn_skills;
    @FXML
    private Button btn_candidat;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
          User u = UserSession.getInstance().getLoggedUser();
	 Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
        statMembre.setData(piechartdata);
    } 
    
     ObservableList<PieChart.Data> piechartdata;
     Connection cnx;
    ResultSet rs;
     public void loadDataPie() throws SQLException{
        piechartdata = FXCollections.observableArrayList();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/opportunis";
        
  
        
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT discr as 'candidats', COUNT(discr) as 'total' FROM user GROUP BY discr  ");
        rs=pst.executeQuery();
        
        while(rs.next()){
            piechartdata.add(new PieChart.Data(rs.getString("candidats"),rs.getInt("total")));

        }  
    }

    private void ajout_employer(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_employer.fxml"));

        try {
            Parent root = loader.load();
            btn_employer.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void affichage_skills(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_skills.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    private void affichage_statistique(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/statUser.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    
        
    }

    @FXML
    private void affichage_candidats(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Affichage.fxml"));

        try {
            Parent root = loader.load();
            btn_candidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
    }
    
}
