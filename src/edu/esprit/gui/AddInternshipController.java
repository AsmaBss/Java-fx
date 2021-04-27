package edu.esprit.gui;

import edu.esprit.entities.Category;
import edu.esprit.entities.Internship;
import edu.esprit.services.CategoryService;
import edu.esprit.services.InternshipService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddInternshipController implements Initializable {

    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtLib;
    @FXML
    private TextField txtPost;
    @FXML
    private TextField txtDuration;
    @FXML
    private DatePicker txtDate;
    @FXML
    private ComboBox<String> cbLevel;
    private ObservableList<String> listLevel = FXCollections.observableArrayList("Bacalaureate", "Bacalaureate+3", "Bacalaureate+5", "Bacalaureate+6", "Other");
    @FXML
    private Button btnAddInternship;
    @FXML
    private ComboBox<Category> cbCategory;
    private ObservableList<Category> listCategory;
    CategoryService cs = new CategoryService();
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
        cbLevel.setItems(listLevel);
        listCategory = FXCollections.observableArrayList(cs.list());
        for (int i = 0; listCategory.size() > i; i++) {
            String test;
            test = listCategory.get(i).getLibelle();
        }
        cbCategory.setItems(listCategory);
    }

    @FXML
    public void addIntern(ActionEvent event) {
        try {
            if (txtLib.getText().equals("")
                    || txtPost.getText().equals("")
                    || txtDescription.getText().equals("")
                    || cbLevel.getValue() == null
                    || txtDuration.getText().equals("")
                    || cbCategory.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill all data !");
                alert.showAndWait();
            } else if(Integer.parseInt(txtDuration.getText()) <= 1 && Integer.parseInt(txtDuration.getText()) >= 12) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Duration must be between 1 and 12 !");
                alert.showAndWait();
            } else {
                String libelle = txtLib.getText();
                String post = txtPost.getText();
                String description = txtDescription.getText();
                String level = cbLevel.getValue();
                String duration = txtDuration.getText();
                //LocalDate date = txtDate.getValue();
                Date d = Date.valueOf(txtDate.getValue());
                Category category = cbCategory.getValue();

                Internship i = new Internship();
                i.setLibelle(libelle);
                i.setPost(post);
                i.setDescription(description);
                i.setDate_expiration(d);
                i.setDuration(Integer.parseInt(duration));
                i.setLevel(level);
                i.setCategory(category.getId());
                InternshipService is = new InternshipService();
                is.addInternship(i);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternship.fxml"));
                Parent root = loader.load();
                btnAddInternship.getScene().setRoot(root);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="BUTTON  templates">
    @FXML
    public void showCategory(ActionEvent event) throws IOException {
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
    public void showInternFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternFront.fxml"));
        Parent root = loader.load();
        btnIntenshipFront.getScene().setRoot(root);
    }
    //</editor-fold>
}