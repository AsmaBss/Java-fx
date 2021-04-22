package edu.esprit.gui;

import edu.esprit.entities.Internship;
import edu.esprit.services.InternshipService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;

public class ShowInternFrontController implements Initializable {

    @FXML
    private Pagination pagination;
    @FXML
    private Label libelle;
    @FXML
    private Label post;
    @FXML
    private Label description;
    Internship i = new Internship();
    InternshipService is = new InternshipService();
    @FXML
    private Label duration;
    @FXML
    private Label level;
    @FXML
    private Label date;
    @FXML
    private Label category;
    @FXML
    private Button btnApply;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            pagIntern();
    }    
    
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * 1;
        ObservableList<Internship> data = is.show();
        for (int i = page; i < page + 1; i++) {
            libelle.setText(data.get(i).getLibelle());
            post.setText(data.get(i).getPost());
            description.setText(data.get(i).getDescription());
            description.setWrapText(true);
            duration.setText(String.valueOf(data.get(i).getDuration())+" months");
            level.setText(data.get(i).getLevel());
            level.setWrapText(true);
            category.setText(data.get(i).getCat());
            category.setWrapText(true);
            date.setText(String.valueOf(data.get(i).getDateExpiration()));
            
            VBox element = new VBox();
            element.getChildren().addAll(libelle,post,description);
            System.out.println("libelle : " + libelle);
            box.getChildren().add(element);
        }
        return box;
    }

    @FXML
    public void pagIntern() {
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
    }
}
