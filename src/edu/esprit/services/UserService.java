package edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.esprit.tools.MyConnection;
//import pidev.Cnx;
import edu.esprit.entities.User;
import java.sql.Date;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class UserService {
    
    Connection cnx;
    PreparedStatement pste;
    Statement ste;

    public UserService(){
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public String getname(int id)
    {
        User u = new User();
        try{
             String requete=" select * from user where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

             pst.setInt(1, id );
             ResultSet rs=pst.executeQuery();
              while(rs.next()){

            u =new User(rs.getInt("id"),rs.getString("nom"));}
             
         } catch (SQLException ex) {
          
         }
        String name= u.getNom();        
        
        return name;
        
    }
}
