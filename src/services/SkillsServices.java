/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Skills;
import models.User;
import edu.esprit.tools.MyConnection;

/**
 *
 * @author hp
 */
public class SkillsServices implements IUser<Skills> {
         Connection cnx = MyConnection.getInstance().getCnx();


    @Override
    public void ajouter(Skills t) {
        String requete = "INSERT INTO Skills (libelle,img)"
                + "VALUES (?,?)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setString(1, t.getLibelle());
            pst.setString(2, t.getImg());
            
          
       
            pst.executeUpdate();
            System.out.println("Skill ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Skills t) {
        try {
            String requete = "DELETE FROM skills WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
          
            pst.executeUpdate();
            System.out.println("Skills Supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
     public void modifier(Skills t) {
        String requete = "UPDATE skills SET id=?,libelle=? ,img=? ";
        try {
             PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.setString(2, t.getLibelle());
            pst.setString(3, t.getImg());
            pst.executeUpdate();
            System.out.println("skill Modifié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public ObservableList<Skills> afficher_skills() {
        ObservableList<Skills> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM Skills";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image(rs.getString(3)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80); 
                System.out.println(rs.getString(2));
                list.add(new Skills(rs.getInt(1),rs.getString(2),imgV));    
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
public ObservableList<Skills> search(String input) {
		ObservableList <Skills> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT *"
					+ "FROM skills "
					+ "WHERE (`libelle` like ?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+input+"%");
			
                      
                       
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Image image = new Image(rs.getString("img")); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                ListUsers.add(new Skills(rs.getInt(1),rs.getString(2),imgV
						
				));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
    @Override
    public List<Skills> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Skills> userListe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Skills> TrieParNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
