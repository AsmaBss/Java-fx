package GUI;

import Entities.Category;
import Services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestController implements Initializable {

    @FXML
    private Pagination pagination;
    @FXML
    private Label libelle;
    @FXML
    private AnchorPane ap;
    CategoryService cs = new CategoryService();
    Category c = new Category();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        test();
        //        Stage primaryStage = new Stage();
//        primaryStage.setTitle("JavaFX App");
//        //Pagination pag = new Pagination();
//
//        pagination.setPageCount(10);
//        pagination.setCurrentPageIndex(0);
//        pagination.setMaxPageIndicatorCount(3);
//
//        pagination.setPageFactory((pageIndex) -> {
//            Label label1 = new Label("Content for page with index: " + pageIndex);
//            label1.setFont(new Font("Arial", 24));
//
//            Label label2 = new Label("Main content of the page ...");
//
//            return new VBox(label1, label2);
//        });
//
//
//        VBox vBox = new VBox(pagination);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    @FXML
    public void test() {
        //Pagination paginationn = new Pagination(3, 0);
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
//        AnchorPane anchor = new AnchorPane();
//        AnchorPane.setTopAnchor(pagination, 10.0);
//        AnchorPane.setRightAnchor(pagination, 10.0);
//        AnchorPane.setBottomAnchor(pagination, 10.0);
//        AnchorPane.setLeftAnchor(pagination, 10.0);
//        anchor.getChildren().addAll(pagination);
//        Scene scene = new Scene(anchor);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setTitle("PaginationSample");
//        stage.show();
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * 1;
        ObservableList<Category> data = cs.showCategory();
        for (int i = page; i < page + 1; i++) {
            libelle.setText(data.get(i).toString());
            VBox element = new VBox();
            element.getChildren().add(libelle);
            System.out.println("libelle : " + libelle);
            box.getChildren().add(element);
        }
        return box;
    }
}
