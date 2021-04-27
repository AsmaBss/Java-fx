package edu.esprit.gui;

import edu.esprit.entities.Category;
import edu.esprit.services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCategoryController implements Initializable {

    @FXML
    private TextField txtLibCategory;
    @FXML
    private Button btnAddCategory;
    int categoryId;
    @FXML
    private Button btnJob;
    @FXML
    private Button btnIntenship;
    @FXML
    private Button btnJobFront;
    @FXML
    private Button btnIntenshipFront;
    @FXML
    private Button btnCategory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void addCateg(ActionEvent event) {
        try {
            Category c = new Category();
            String libelle = txtLibCategory.getText();
            if (libelle.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill all data !");
                alert.showAndWait();
            } else {
                c.setTitle(libelle);
                CategoryService cs = new CategoryService();
                cs.addCategory(c);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCategory.fxml"));
                Parent root = loader.load();
                btnAddCategory.getScene().setRoot(root);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="BUTTON  templates">
    @FXML
    public void showCategory(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCategory.fxml"));
        Parent root = loader.load();
        btnCategory.getScene().setRoot(root); 
    }
    
    @FXML
    public void showJob(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowJob.fxml"));
        Parent root = loader.load();
        btnJob.getScene().setRoot(root);
    }

    @FXML
    public void showInternship(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternship.fxml"));
        Parent root = loader.load();
        btnIntenship.getScene().setRoot(root);
    }

    @FXML
    public void showJobFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowJobFront.fxml"));
        Parent root = loader.load();
        btnJobFront.getScene().setRoot(root);
    }

    @FXML
    public void showInternFront(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternFront.fxml"));
        Parent root = loader.load();
        btnIntenshipFront.getScene().setRoot(root);
    }
    //</editor-fold>
    
}
