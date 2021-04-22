/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Complaint;
import edu.esprit.interfaces.IComplaint;
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
public class ComplaintService implements IComplaint <Complaint>
{
public ObservableList<Complaint> observableListComplaints = FXCollections.observableArrayList();
public ObservableList<String> observableListComplaintsfront = FXCollections.observableArrayList();

    @Override
    public void addComplaint(Complaint t) {
      try {
            String requete = "INSERT INTO complaint (title,content,iduser,status)"
                    + "VALUES ('"+t.getTitle()+"','"+t.getContent()+"','"+t.getIduser()+"','"+t.getStatus()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Complaint added successfully ! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
     public void addComplaintFront(Complaint t) {
      try {
            String requete = "INSERT INTO complaint (title,content,iduser,status)"
                    + "VALUES ('"+t.getTitle()+"','"+t.getContent()+"','1','"+t.getStatus()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Complaint added successfully ! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void removeComplaint(Complaint t) {
        
        try {
            String requete = "DELETE FROM complaint where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Complaint removed successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void updateComplaint(Complaint t) {
       
    }
    public void updateComplaint2(String id, String title, String content,String status) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE complaint SET title= '"+title+"', content = '"+content+"', iduser = '0', status = '"+status+"' WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Complaint updated successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void treat(String id, String title, String content,String iduser,String status) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE complaint SET title= '"+title+"', content = '"+content+"', iduser = '"+iduser+"', status = '"+status+"' WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Complaint updated successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void updateComplaintFront(String id, String title, String content) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE complaint SET title= '"+title+"', content = '"+content+"', iduser = '1', status = 'Not treated' WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Complaint updated successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Complaint> complaintsList() {
        return null;
       
    }
    
    public ObservableList<Complaint> displayComplaints()
{
    try {

            String requete = "SELECT * from complaint";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){

                observableListComplaints.add( new Complaint(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
            }
    }
             catch (SQLException ex) {
            
        }

          return observableListComplaints;     

}
    
    
    
     public ObservableList<String> displayComplaintsFront()
{
    try {

            String requete = "SELECT * from complaint";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){

                 Complaint c = new Complaint(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
                 String str = c.getTitle()+"  \n"+c.getContent();
                 observableListComplaintsfront.add(str);
            }
    }
             catch (SQLException ex) {
            
        }

          return observableListComplaintsfront;     

}
     
     
     

  
    public Complaint getComplaint(int id){
          Complaint c =new Complaint();
         try {
             String requete="select * from complaint where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

             pst.setInt(1, id);
             ResultSet rs=pst.executeQuery();
              while(rs.next()){

            c =new Complaint(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getInt("iduser"),rs.getString("status"));
              ;}
         } catch (SQLException ex) {
          
         }
         return c;

     }
    public ObservableList<Complaint> displayFront(int user_id)
{
    try {

            String requete = "SELECT * from complaint where iduser='"+user_id+"' ";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){

                observableListComplaints.add( new Complaint(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
            }
    }
             catch (SQLException ex) {
            
        }

          return observableListComplaints;     

}

    
    
    
    
}
