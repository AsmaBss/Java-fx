package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pidev.Cnx;
import Entities.Job;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JobService {
    
    Connection cnx;
    PreparedStatement pste;
    Statement ste;

    public JobService(){
        cnx = Cnx.getinstance().getCnx();
    }

    public ObservableList<Job> showJob(){
        ObservableList<Job> list = FXCollections.observableArrayList();
        String sql = "select * from offre where discr ='emploi'";
        String sqltest = "select o.*,c.title from offre as o, categorie as c where o.category_id=c.id and discr='emploi'" ;
        try{
            System.out.println("Selection jobs ...");
            pste = cnx.prepareStatement(sqltest);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                //list.add(new Job(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
                list.add(new Job(resultSet.getInt("id"),resultSet.getString("libelle"), resultSet.getString("poste"), 
                        resultSet.getDate("dateExpiration"), resultSet.getString("niveau"), 
                        resultSet.getString("typeContrat"), resultSet.getDouble("salaire"), 
                        resultSet.getString("c.title")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public void addJob(Job j){
        try {
            String sql = "insert into offre (libelle, poste, dateExpiration, description, discr, typeContrat, salaire, niveau, category_id) values (?, ?, ?, ?, 'emploi', ?, ?, ?, ?)";
            pste = cnx.prepareStatement(sql);
            pste.setString(1, j.getLibelle());
            pste.setString(2, j.getPost());
            pste.setDate(3, j.getDateExpiration());
            pste.setString(4, j.getDescription());
            pste.setString(5, j.getContrat());
            pste.setDouble(6, j.getSalary());
            pste.setString(7, j.getLevel());
            pste.setInt(8, j.getCategory());
            pste.execute();
            System.out.println("Job Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteJob(Job j){
        try {
            //String sql = "delete from offre where libelle= ? and discr ='emploi'";
            String sql="delete from offre where id= ? and discr ='emploi'";
            pste = cnx.prepareStatement(sql);
            pste.setInt(1, j.getId());
            pste.executeUpdate();
            System.out.println("Job Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateJob(Job j){
        try {
            String sql = "update offre set id=?, libelle=?, poste=?, dateExpiration=?, description=?, typeContrat=?, salaire=?, niveau=?, category_id=?  where id=? ";
            pste = cnx.prepareStatement(sql);
            pste.setInt(1,j.getId());
            pste.setString(2, j.getLibelle());
            pste.setString(3, j.getPost());
            pste.setDate(4, j.getDateExpiration());
            pste.setString(5, j.getDescription());
            pste.setString(6, j.getContrat());
            pste.setDouble(7, j.getSalary());
            pste.setString(8, j.getLevel());
            pste.setInt(9, j.getCategory());
            pste.setInt(10, j.getId());
            System.err.println(j.getId());
            pste.execute();
            System.out.println("Job Updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ObservableList<Job> show(){
        ObservableList<Job> list = FXCollections.observableArrayList();
        String sqltest = "select o.*,c.title from offre as o, categorie as c where o.category_id=c.id and discr='emploi'" ;
        try{
            System.out.println("Selection jobs ...");
            pste = cnx.prepareStatement(sqltest);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                list.add(new Job(resultSet.getInt("id"),resultSet.getString("libelle"), resultSet.getString("poste"), 
                        resultSet.getDate("dateExpiration"), resultSet.getString("description"), resultSet.getString("niveau"), 
                        resultSet.getString("typeContrat"), resultSet.getDouble("salaire"), 
                        resultSet.getString("c.title")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
//alter table test.offre 
//add FOREIGN KEY (category_id) REFERENCES test.categorie(id) 
//ON DELETE CASCADE ON UPDATE CASCADE;
    
    
}
