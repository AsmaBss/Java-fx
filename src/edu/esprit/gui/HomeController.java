package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    private AnchorPane rootPane;
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

    
    
    
    
    
    public void addCategory(ActionEvent evt) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }
    
    public void addJob(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddJob.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void addInternship(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddInternship.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    public void close(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void test(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("test.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    
}
