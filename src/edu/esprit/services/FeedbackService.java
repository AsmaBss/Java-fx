/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Complaint;
import edu.esprit.entities.Feedback;
import edu.esprit.interfaces.IFeedback;
import edu.esprit.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ela
 */
public class FeedbackService implements IFeedback <Feedback>
{

    public ObservableList<Feedback> observableListFeedbacks = FXCollections.observableArrayList();
    
    @Override
    public void addFeedback(Feedback t) {
        
        try {
            String requete = "INSERT INTO feedback (description,date,iduser,offer)"
                    + "VALUES ('"+t.getDescription()+"','"+t.getDate()+"','"+t.getIduser()+"','"+t.getOffer()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Feedback added successfully ! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void removeFeedback(Feedback t) {
        
         try {
            String requete = "DELETE FROM feedback where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Feedback removed successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateFeedback(Feedback t) {
        
    }
    public void updateFeedback2(String id, String description, String date ,String iduser ,String offer) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE feedback SET description= '"+description+"', date = '"+date+"', iduser = '"+iduser+"', offer = '"+offer+"' WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Feedback updated successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Feedback> FeedbacksList() {
        return null;
    }
    
    
    
     public ObservableList<Feedback> displayFeedbacks()
{
    try {
        String sqltes = "select o.*,c.title from offre as o, categorie as c where o.category_id=c.id and discr='emploi'" ;

            String sqltest= "select f.*,o.libelle from feedback as f,offre as o where f.offer=o.libelle" ;
            String requete = "SELECT * from feedback";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(sqltest);
            while(rs.next()){

                observableListFeedbacks.add( new Feedback(rs.getInt("id"),rs.getString("description"),rs.getDate("date"),rs.getInt("iduser"),rs.getString("offer")));
            }
    }
             catch (SQLException ex) {
            
        }

          return observableListFeedbacks;     

}
     public ObservableList<Feedback> displayOfferFeedbacks(String oi)
{
    try {

            String requete = "SELECT * from feedback where offer='"+oi+"' ";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){

                observableListFeedbacks.add( new Feedback(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5)));
            }
    }
             catch (SQLException ex) {
            
        }

          return observableListFeedbacks;     

}

     
     
     public Feedback getFeedback(int id){
          Feedback f =new Feedback();
         try {
             String requete="select * from feedback where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

             pst.setInt(1, id );
             ResultSet rs=pst.executeQuery();
              while(rs.next()){

            f =new Feedback(rs.getInt("id"),rs.getString("description"),rs.getDate("date"),rs.getInt("iduser"),rs.getString("offer"));
              }
         } catch (SQLException ex) {
          
         }
         return f;

     }

     
    
}
