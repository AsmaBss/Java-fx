/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAshref;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Article;
import javax.imageio.ImageIO;
import models.User;
import services.ArticleService;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private TextField titre_txt;
    @FXML
    private TextArea desc_txt;
    @FXML
    private DatePicker date_pic;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_back;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private Label labelll;
    @FXML
    private Circle circle3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image image ;
                image = new Image(u.getImg());
                
                
               
                circle3.setFill(new ImagePattern(image));
                labelll.setText(u.getAddress());
    }    
  User u = UserSession.getInstance().getLoggedUser(); 
    @FXML
    private void addEvent(ActionEvent event) {
    
        String titre=titre_txt.getText();
        String desc=desc_txt.getText();
        Date datee=Date.valueOf(date_pic.getValue());
        String image = imgUrl;

           
   
        Article a= new Article (81,titre,desc,datee,image,u.getId()); 
        ArticleService as=new ArticleService();
        as.ajouterArticle(a);
    
    }

    @FXML
    private void back(ActionEvent event) {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiAshref/AfficherArticle.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
        
    }

    @FXML
    private void addImage(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        uploadPic = new FileChooser();
        uploadPic.setTitle("Select the image you want to add");
        picPath = uploadPic.showOpenDialog(stage);
        System.out.println(picPath.toString());
        try {
            imgUrl = picPath.toURI().toURL().toExternalForm();

            BufferedImage buffImage = ImageIO.read(picPath);
            Image up = SwingFXUtils.toFXImage(buffImage, null); 
            imageToPost.setImage(up);
        } catch(IOException ex){
            System.err.println(ex.getMessage());
        } 
        
        
    }

    @FXML
    private void GoToProfile(MouseEvent event) {
    }
    
}
