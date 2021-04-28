package GUI;

import Entities.Category;
import Services.CategoryService;
import java.io.IOException;
import java.net.URL;
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

public class ShowCategoryController implements Initializable {

    CategoryService cs = new CategoryService();
    @FXML
    private TableView<Category> categories;
    @FXML
    private TableColumn<Category, String> libelle;
    @FXML
    private Button updateC;
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
        viewCategory();
    }

    //<editor-fold defaultstate="collapsed" desc="SHOW Category">
    public void viewCategory() {
        ObservableList<Category> data = cs.showCategory();
        libelle.setCellValueFactory(new PropertyValueFactory<>("title"));
        categories.setItems(data);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DELETE Category">
    @FXML
    public void deleteCategorie(ActionEvent event) {
        CategoryService cs = new CategoryService();
        Category c = categories.getSelectionModel().getSelectedItem();
        if (c != null) {
            cs.deleteCategory(c);
            List<Category> lr = cs.showCategory();
            ObservableList<Category> data = FXCollections.observableArrayList(lr);
            categories.setItems(data);
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
    public void updateCategorie(ActionEvent event) throws IOException {
        Category c = categories.getSelectionModel().getSelectedItem();
        if (c != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCategory.fxml"));
            Parent parent = loader.load();
            updateC.getScene().setRoot(parent);
            UpdateCategoryController controller = (UpdateCategoryController) loader.getController();
            controller.setCateg(c);
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

    @FXML
    public void addCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
        Parent root = loader.load();
        btnAdd.getScene().setRoot(root);
    }

}
