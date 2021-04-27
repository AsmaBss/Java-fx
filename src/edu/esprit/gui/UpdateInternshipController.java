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
import javafx.scene.layout.AnchorPane;

public class UpdateInternshipController implements Initializable {

    Internship i = new Internship();
    @FXML
    private Button btnUpdateIntership;
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
        init();
    }

    public void init() {
        cbLevel.setItems(listLevel);
        listCategory = FXCollections.observableArrayList(cs.list());
        for (int i = 0; listCategory.size() > i; i++) {
            String test;
            test = listCategory.get(i).getLibelle();
        }
        cbCategory.setItems(listCategory);
    }

    public void setCateg(Internship i) {
        this.i = i;
    }
    private AnchorPane ap;

    public void updateField() throws IOException {
        Category c = new Category();
        Date d = i.getDateExpiration();
        txtLib.setText(i.getLibelle());
        txtPost.setText(i.getPost());
        txtDuration.setText(String.valueOf(i.getDuration()));
        txtDescription.setText(i.getDescription());
        txtDate.setValue(d.toLocalDate());
        cbLevel.setValue(i.getLevel());
        //cbCategory.setValue(c);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternship.fxml"));
    }

    @FXML
    public void updateIntern(ActionEvent event) throws IOException {
        InternshipService is = new InternshipService();
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
            } else if (Integer.parseInt(txtDuration.getText()) >= 1 && Integer.parseInt(txtDuration.getText()) <= 12) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Duration must be between 1 and 12 !");
                alert.showAndWait();
            } else {
                is.updateInternship(new Internship(i.getId(), txtLib.getText(), txtPost.getText(),
                        Date.valueOf(txtDate.getValue()), txtDescription.getText(),
                        cbLevel.getValue(), Integer.parseInt(txtDuration.getText()),
                        cbCategory.getValue().getId())
                );

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowInternship.fxml"));
                Parent root = loader.load();
                btnUpdateIntership.getScene().setRoot(root);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void setInternship(Internship i) {
        this.i = i;
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
