package edu.esprit.gui;

import edu.esprit.entities.Internship;
import edu.esprit.services.InternshipService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
    
    @FXML
    private Button btnJobFront;
    @FXML
    private Button back;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pagIntern();
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * 1;
        ObservableList<Internship> data = is.show();
        for (int i = page; i < page + 1; i++) {
            int xx = data.get(i).getId();
            libelle.setText(data.get(i).getLibelle());
            post.setText(data.get(i).getPost());
            description.setText(data.get(i).getDescription());
            description.setWrapText(true);
            duration.setText(String.valueOf(data.get(i).getDuration()) + " months");
            level.setText(data.get(i).getLevel());
            level.setWrapText(true);
            category.setText(data.get(i).getCat());
            category.setWrapText(true);
            date.setText(String.valueOf(data.get(i).getDateExpiration()));
            btnApply.setOnAction(actionEvent -> {
                try {
                    sendMail();
                } catch (IOException ex) {
                    Logger.getLogger(ShowInternFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("sent mail !");
                //btnApply.setText("Cancel");
            });
            VBox element = new VBox();
            element.getChildren().addAll(libelle, post, description);
            box.getChildren().add(element);
        }
        return box;
    }

    @FXML
    public void pagIntern() {
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
    }

    //<editor-fold defaultstate="collapsed" desc="BUTTON  templates">
    

    @FXML
    public void showJobFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowJobFront.fxml"));
        Parent root = loader.load();
        btnJobFront.getScene().setRoot(root);
    }

   
    //</editor-fold>

    @FXML
    private void applyIntern(ActionEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc=" SendMail ">
    public void sendMail() throws IOException {
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
            msg.setSubject("Subject Line");
            //msg.setText("Email Body Text");
            Multipart emailContent = new MimeMultipart();
            
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("My multipart text");
            
            createPDF();

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile("C:/Users/PC-HP/Documents/NetBeansProjects/pidev/src/edu/esprit/gui/asma-CV.pdf");

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(attachment);

            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        
        } 
        catch (MessagingException e) {
            e.printStackTrace();
            //Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (IOException ex) {
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
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/PC-HP/Documents/NetBeansProjects/pidev/src/edu/esprit/gui/asma-CV.pdf"));
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

    @FXML
    private void back(ActionEvent event) {
        
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menu.fxml"));

        try {
            Parent root = loader.load();
            pagination.getScene().setRoot(root);
        } catch (IOException ex) {
            
        }
        
        
        
    }
    


}
