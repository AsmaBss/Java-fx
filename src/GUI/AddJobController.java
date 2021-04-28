package GUI;

import Entities.Category;
import Entities.Job;
import Services.CategoryService;
import Services.JobService;
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

public class AddJobController implements Initializable {

    @FXML
    private TextField txtLib;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtPost;
    @FXML
    private TextField txtSalary;
    @FXML
    private Button btnAddJob;
    @FXML
    private ComboBox<String> cbContrat;
    private ObservableList<String> listContrat = FXCollections.observableArrayList("CDI - Contract of indefinite duration", "CDD - Fixed-term contract", "CTT - Temporary employment contract", "CUI - Single integration contract", "CAE - Employment support contract", "CIE - Employment initiative contract");
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
        cbContrat.setItems(listContrat);
        cbLevel.setItems(listLevel);
        listCategory = FXCollections.observableArrayList(cs.list());

        for (int i = 0; listCategory.size() > i; i++) {
            String test;
            test = listCategory.get(i).getLibelle();
        }
        cbCategory.setItems(listCategory);
    }

    @FXML
    public void addJob(ActionEvent event) {
        try {
            if (txtLib.getText().equals("")
                    || txtPost.getText().equals("")
                    || txtDescription.getText().equals("")
                    || cbLevel.getValue() == null
                    || txtSalary.getText().equals("")
                    || cbCategory.getValue() == null
                    || cbContrat.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill all data !");
                alert.showAndWait();
            } else {
                Job j = new Job();
                String libelle = txtLib.getText();
                String post = txtPost.getText();
                String description = txtDescription.getText();
                Date d = Date.valueOf(txtDate.getValue());
                String salary = txtSalary.getText();
                String level = cbLevel.getValue();
                String contrat = cbContrat.getValue();
                Category category = cbCategory.getValue();

                j.setLibelle(libelle);
                j.setPost(post);
                j.setDescription(description);
                j.setDate_expiration(d);
                j.setSalary(Double.parseDouble(salary));
                j.setContrat(contrat);
                j.setLevel(level);
                j.setCategory(category.getId());
                JobService js = new JobService();
                js.addJob(j);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowJob.fxml"));
                Parent root = loader.load();
                btnAddJob.getScene().setRoot(root);
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
