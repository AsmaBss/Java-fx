package GUI;

import Entities.Job;
import Services.JobService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    private Button btnApply;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pagJob();
        
    }    
    
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * 1;
        ObservableList<Job> data = js.show();
        for (int i = page; i < page + 1; i++) {
            Button apply = new Button();
            apply.setText("Apply");
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
            btnApply.setOnAction(actionEvent -> {
                sendMail();
                System.out.println("sent mail !");
                //btnApply.setText("Cancel");
            });
            VBox element = new VBox();
            element.getChildren().addAll(libelle,post,description);
            box.getChildren().add(element);
        }
        return box;
    }

    @FXML
    public void pagJob() {
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
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

    
    //<editor-fold defaultstate="collapsed" desc=" SendMail ">
    public void sendMail() {
        //authentification info
        String username = "asma.besbes@esprit.tn";
        String password = "203JFT1621";
        String fromEmail = "asma.besbes@esprit.tn";
        String toEmail = "asma.besbes@esprit.tn";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //properties.put("mail.smtp.port", "587");
        properties.put("mail.smtps.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("test password");
                return new PasswordAuthentication(username, password);
            }
        });
        
        //start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Posulation");
            
            createPDF();
            
            //msg.setText("Email Body Text");
            Multipart emailContent = new MimeMultipart();
            
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Offre");

            MimeBodyPart attachment = new MimeBodyPart();
            //attachment.attachFile("C:/Users/hp/Desktop/Mes études/Ing/1/Semestre 2/java/pidev/src/Images/dashboard.png");
            attachment.attachFile("C:/Users/hp/Desktop/Mes études/Ing/1/Semestre 2/java/pidev/src/CV/asma-CV.pdf");
            //attachment.attachFile("C:/Users/hp/Desktop/Mes études/Ing/1/Semestre 2/java/pidev/src/CV/" +  + "-CV.pdf");
            
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(attachment);

            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
            //Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CreatePDF">
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public void createPDF(){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/hp/Desktop/Mes études/Ing/1/Semestre 2/java/pidev/src/CV/job-CV.pdf"));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addContent(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Nom Candidat" + "                                                 " + "Image candidat" , catFont));
        //addEmptyLine(preface, 1);
        preface.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        //preface.add(new Paragraph(
        //        "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        preface.add(new Paragraph("CONTACT DETAILS", subFont));
        preface.add(new Paragraph("Email : \n", smallBold));
        preface.add(new Paragraph("N tel : \n", smallBold));
        preface.add(new Paragraph("Adresse : ", smallBold));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph("SKILLS", subFont));
        addEmptyLine(preface, 8);

        document.add(preface);
        // Start a new page
        document.newPage();
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    //</editor-fold>
    

}
