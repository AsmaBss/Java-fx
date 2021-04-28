package GUI;

import Entities.Job;
import Services.JobService;
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

public class ShowJobController implements Initializable {

    JobService js = new JobService();
    @FXML
    private TableView<Job> table;
    @FXML
    private TableColumn<Job, String> libelle;
    @FXML
    private TableColumn<Job, String> post;
    @FXML
    private TableColumn<Job, Double> salary;
    @FXML
    private TableColumn<Job, String> level;
    @FXML
    private TableColumn<Job, String> contrat;
    @FXML
    private TableColumn<Job, Date> date;
    @FXML
    private TableColumn<Job, String> category;
    @FXML
    private Button updateJ;
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
        viewJob();
    }
    
    //<editor-fold defaultstate="collapsed" desc="SHOW Job">
    public void viewJob() {
        ObservableList<Job> data = js.showJob();
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        post.setCellValueFactory(new PropertyValueFactory<>("post"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        contrat.setCellValueFactory(new PropertyValueFactory<>("contrat"));
        category.setCellValueFactory(new PropertyValueFactory<>("cat"));
        table.setItems(data);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="DELETE Job">
    @FXML
    public void deleteJob(ActionEvent event) {
        JobService js = new JobService();
        Job j = table.getSelectionModel().getSelectedItem();
        if (j != null) {
            js.deleteJob(j);
            List<Job> lr = js.showJob();
            ObservableList<Job> data = FXCollections.observableArrayList(lr);
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
    public void updateJob(ActionEvent event) throws IOException {
        Job j = table.getSelectionModel().getSelectedItem();
        if (j != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateJob.fxml"));
            Parent parent = loader.load();
            updateJ.getScene().setRoot(parent);
            UpdateJobController controller = (UpdateJobController) loader.getController();
            controller.setJob(j);
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
    public void addJob(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddJob.fxml"));
        Parent root = loader.load();
        btnAdd.getScene().setRoot(root); 
    }

}
