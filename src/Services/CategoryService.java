package Services;

import java.sql.Connection;
import java.sql.SQLException;
import pidev.Cnx;
import Entities.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryService {
    Connection cnx;
    PreparedStatement pste;

    public CategoryService(){
        cnx = Cnx.getinstance().getCnx();
    }
    
    public ObservableList<Category> showCategory(){
        ObservableList<Category> categories = FXCollections.observableArrayList();
        String sql = "select * from categorie";
        try{
            System.out.println("Selection categories ...");
            pste = cnx.prepareStatement(sql);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                categories.add(new Category(resultSet.getInt(1),resultSet.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    public void addCategory(Category c){
        try {
            String sql = "insert into categorie (title) values (?)";
            pste = cnx.prepareStatement(sql);
            pste.setString(1, c.getTitle());
            pste.execute();
            System.out.println("Category Added..");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCategory(Category c){
        try {
            String sql = "update categorie set id = ? , title = ? where id = ?";
            pste= cnx.prepareStatement(sql);
            System.out.println(sql);
	    pste.setInt(1,c.getId());
            pste.setString(2,c.getTitle());
            pste.setInt(3,c.getId());
            pste.executeUpdate();
            System.out.println("Category Updated !");
        } catch (SQLException ex) {
	    System.err.println(ex.getMessage());
        }
    }

    public void deleteCategory(Category c){
        try {
            String sql = "delete from categorie where title= ?";
            pste = cnx.prepareStatement(sql);
            pste.setString(1, c.getTitle());
            pste.executeUpdate();
            System.out.println("Category Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Category> list(){
        ObservableList<Category> categories = FXCollections.observableArrayList();
        String sql = "select * from categorie";
        try{
            System.out.println("Selection categories ...");
            pste = cnx.prepareStatement(sql);
            ResultSet resultSet = pste.executeQuery();
            while(resultSet.next()){
                categories.add(new Category(resultSet.getInt(1),resultSet.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }
}
