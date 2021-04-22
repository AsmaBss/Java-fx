package edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.entities.Job;
//import pidev.Cnx;
import edu.esprit.entities.Internship;
import edu.esprit.tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InternshipService {
    
    Connection cnx;
    PreparedStatement pste;
    Statement ste;

    public InternshipService(){
        cnx = MyConnection.getInstance().getCnx();
    }

    public ObservableList<Internship> showInternship(){
        ObservableList<Internship> list = FXCollections.observableArrayList();
        String sql = "select * from offre where discr = 'stage'";
        String sqltest = "select o.*,c.title from offre as o, categorie as c where o.category_id=c.id and discr='stage'" ;
        try {  
            System.out.println("Selection Internship ...");
            pste = cnx.prepareStatement(sqltest);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                list.add(new Internship(
                        resultSet.getInt("id"),resultSet.getString("libelle"), 
                        resultSet.getString("poste"), 
                        resultSet.getDate("dateExpiration"), resultSet.getString("niveau"), 
                        resultSet.getInt("duree"),resultSet.getString("c.title")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public void addInternship(Internship i){
        try {
            String sql = "insert into offre (libelle, poste, dateExpiration, description, discr, niveau, duree,category_id) values (?, ?, ?, ?,'stage' , ?, ?, ?)";
            pste = cnx.prepareStatement(sql);
            pste.setString(1, i.getLibelle());
            pste.setString(2, i.getPost());
            //pste.setDate(3, (Date) i.getDate_expiration());
            pste.setDate(3, i.getDateExpiration());
            pste.setString(4, i.getDescription());
            pste.setString(5, i.getLevel());
            pste.setInt(6, i.getDuration());
            pste.setInt(7, i.getCategory());
            pste.execute();
            System.out.println("Intership Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateInternship(Internship i){
        try {
            String sql = "update offre set id=? ,libelle= ?, poste=?, dateExpiration=?, description=?, niveau=?, duree=? where id=?";
            PreparedStatement pste = cnx.prepareStatement(sql);
            pste.setInt(1, i.getId());
            pste.setString(2, i.getLibelle());
            pste.setString(3, i.getPost());
            pste.setDate(4, (i.getDateExpiration()));
            pste.setString(5, i.getDescription());
            pste.setString(6, i.getLevel());
            pste.setInt(7, i.getDuration());
            pste.setInt(8, i.getId());
            pste.execute();
            System.out.println("Intership Updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteInternship(Internship i){
        try {
            String sql = "delete from offre where libelle= ? and discr ='stage'";
            pste = cnx.prepareStatement(sql);
            pste.setString(1, i.getLibelle());
            System.out.println(sql);
            pste.executeUpdate();
            System.out.println("Internship Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Internship> show(){
        ObservableList<Internship> list = FXCollections.observableArrayList();
        String sqltest = "select o.*,c.title from offre as o, categorie as c where o.category_id=c.id and discr='stage'" ;
        try {  
            System.out.println("Selection Internship ...");
            pste = cnx.prepareStatement(sqltest);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                list.add(new Internship(
                        resultSet.getInt("id"),resultSet.getString("libelle"), 
                        resultSet.getString("poste"), resultSet.getDate("dateExpiration"), 
                        resultSet.getString("description"), resultSet.getString("niveau"), 
                        resultSet.getInt("duree"),resultSet.getString("c.title")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
