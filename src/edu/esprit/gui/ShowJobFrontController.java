package edu.esprit.gui;


import edu.esprit.entities.Feedback;
import edu.esprit.entities.Job;
import static edu.esprit.gui.DisplayAllFeedbackController.table2;
import edu.esprit.services.FeedbackService;
import edu.esprit.services.JobService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowJobFrontController implements Initializable {
 

    @FXML
    private Pagination pagination;
    @FXML
    private Label libelle;
    @FXML
    private Label post;
    @FXML
    private Label description;
    
    Job j = new Job();
    JobService js = new JobService();
    public ObservableList<Job> offers = js.show();
    public static int currentOfferId;

    @FXML
    private Label salary;
    @FXML
    private Label contract;
    @FXML
    private Label level;
    @FXML
    private Label date;
    @FXML
    private Label category;
    @FXML
    private Button addfeedbackBtn;
    @FXML
    private TableView<Feedback> FeedbackTable;
    @FXML
    private TableColumn<Feedback, String> dateFeedback;
    @FXML
    private TableColumn<Feedback, String> userFeedback;
    @FXML
    private TableColumn<Feedback, String> descriptionFeedback;
    
    public static  TableView<Feedback> table2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //Pages of the offers
      
        pagJob();
        

    }    
    
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * 1;
        ObservableList<Job> data = js.show();
        for (int i = page; i < page + 1; i++) {
            libelle.setText(data.get(i).getLibelle());
            post.setText(data.get(i).getPost());
            description.setText(data.get(i).getDescription());
            description.setWrapText(true);
            salary.setText(String.valueOf(data.get(i).getSalary())+" DT");
            contract.setText(data.get(i).getContrat());
            contract.setWrapText(true);
            level.setText(data.get(i).getLevel());
            level.setWrapText(true);
            category.setText(data.get(i).getCat());
            category.setWrapText(true);
            date.setText(String.valueOf(data.get(i).getDateExpiration()));
            VBox element = new VBox();
            element.getChildren().addAll(libelle,post,description);
            //System.out.println("libelle : " + libelle);
            box.getChildren().add(element);
            
            
        ///////////////////Feedbacks////////////////////
            int p = pagination.getCurrentPageIndex();           
        currentOfferId = offers.get(p).getId();

        //Table of the feedbacks
        table2 = FeedbackTable;
        FeedbackService fs = new FeedbackService();
        
        descriptionFeedback.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateFeedback.setCellValueFactory(new PropertyValueFactory<>("date"));
        userFeedback.setCellValueFactory(new PropertyValueFactory<>("iduser"));        
        
        FeedbackTable.setItems(fs.displayOfferFeedbacks(currentOfferId));   
        
                Feedback f =FeedbackTable.getSelectionModel().getSelectedItem();
                //System.out.println(f);
        }
        return box;
    }

    @FXML
    public void pagJob() {
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
    }

    @FXML
    private void addFeedback(ActionEvent event) {
        try {
            int p = pagination.getCurrentPageIndex();
            currentOfferId = offers.get(p).getId();
            System.out.println(currentOfferId);
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addFeedbackFront.fxml"));
            Parent root =loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("New Feedback");
stage.getIcons().add(new Image("/edu/esprit/gui/icon.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    public int getCurrentOfferId(){
        return currentOfferId;
    }
    
    
}
