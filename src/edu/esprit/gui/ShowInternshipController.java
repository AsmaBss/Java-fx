package edu.esprit.gui;

import edu.esprit.entities.Internship;
import edu.esprit.services.InternshipService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowInternshipController implements Initializable {

    InternshipService is = new InternshipService();
    @FXML
    private TableView<Internship> table;
    @FXML
    private TableColumn<Internship, String> libelle;
    @FXML
    private TableColumn<Internship, String> post;
    @FXML
    private TableColumn<Internship, Integer> duration;
    @FXML
    private TableColumn<Internship, String> level;
    @FXML
    private TableColumn<Internship, Date> dateExpiration;
    @FXML
    private TableColumn<Internship, String> category;
    @FXML
    private Button updateI;
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
    @FXML
    private Button btnAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewInternship();
    }
    
    //<editor-fold defaultstate="collapsed" desc="SHOW Internship">
    public void viewInternship() {
        ObservableList<Internship> data = is.showInternship();
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        post.setCellValueFactory(new PropertyValueFactory<>("post"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        dateExpiration.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        category.setCellValueFactory(new PropertyValueFactory<>("cat"));
        table.setItems(data);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DELETE Internship">
    @FXML
    public void deleteIntenship(ActionEvent event) {
        InternshipService is = new InternshipService();
        Internship i = table.getSelectionModel().getSelectedItem();
        if (i != null) {
            is.deleteInternship(i);
            List<Internship> lr = is.showInternship();
            ObservableList<Internship> data = FXCollections.observableArrayList(lr);
            table.setItems(data);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Row deleted !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select row to delete !");
            alert.showAndWait();
            System.out.println("Select row to delete !");
        }
    }
    //</editor-fold>

    @FXML
    public void updateIntern(ActionEvent event) throws IOException{
        InternshipService cs = new InternshipService();
        Internship i = table.getSelectionModel().getSelectedItem();
        if (i != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateInternship.fxml"));
            Parent parent = loader.load();
            updateI.getScene().setRoot(parent);
            UpdateInternshipController controller = (UpdateInternshipController) loader.getController();
            controller.setInternship(i);
            controller.updateField();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select row to update !");
            alert.showAndWait();
            System.out.println("Select row to update !");
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="BUTTON  templates">
    @FXML
    public void showCategory() throws IOException{
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

    @FXML
    public void addInternship(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddInternship.fxml"));
        Parent root = loader.load();
        btnAdd.getScene().setRoot(root);
    }
}
