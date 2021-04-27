/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
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
import models.skills_candidat;
import edu.esprit.tools.MyConnection;


/**
 *
 * @author hp
 */
public class SkillsCandidatServices {
        Connection cnx = MyConnection.getInstance().getCnx();
        
    public  ObservableList<Skills> afficher_skills(User u1) {
        ObservableList<Skills> list = FXCollections.observableArrayList();
        
        String requete = "SELECT s.* FROM skills s inner join skills_candidat sc on sc.skills_id = s.id inner join user u on u.id = sc.candidat_id where u.id = "+u1.getId();
     
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
       
          
           
          
            
            
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                
               // System.out.println(rs.getInt(2));
                 Image image = new Image(rs.getString(3)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                System.out.println(rs.getString(3));
                list.add(new Skills(rs.getInt(1),imgV));    
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
      public Skills getSkill(String libelle) {
        Skills s = new Skills();
        
        String requete = "SELECT * FROM skills where libelle = ? ";
       
        try {
             PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, libelle);
               ResultSet rs = pst.executeQuery();
            while(rs.next()){
           s= new Skills(rs.getInt(1),rs.getString(2),rs.getString(3));
            
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return s;
    }
       public int getUser(String address) {
        User s = new User();
        
        String requete = "SELECT * FROM user where address = ? ";
       
        try {
             PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, address);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                s = new User(rs.getInt(1),rs.getString(13), rs.getString(14),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(17),rs.getString(16),rs.getDate(15),rs.getString(5),rs.getString(8),rs.getString(2));
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return s.getId();
    }
      
      
       public void ajouter(skills_candidat t) {
        String requete = "INSERT INTO skills_candidat (skills_id,candidat_id)"
                + "VALUES (?,?)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, t.getSkills_id());
            pst.setInt(2, t.getCandidat_id());
            System.out.println(t.getSkills_id());
            System.out.println( t.getCandidat_id());
            
          
       
            pst.executeUpdate();
            System.out.println("Skill ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
        public void supprimer(skills_candidat t) {
        try {
            String requete = "DELETE FROM skills_candidat WHERE skills_id=? and candidat_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getSkills_id());
            pst.setInt(2,t.getCandidat_id());
           System.out.println(t.getSkills_id());
            System.out.println(t.getCandidat_id());
            pst.executeUpdate();
            System.out.println("skill Supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}

