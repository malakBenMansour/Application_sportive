/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Event;
import entities.Reservation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.SEvent;
import services.SReservation;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtage;
    @FXML
    private ComboBox<Integer> txtevent;
    @FXML
    private Button btnajout;

    SEvent s= new SEvent();
    ObservableList<Integer> listids=FXCollections.observableArrayList(s.afficherid());
    @FXML
    private Button btnajout1;
    @FXML
    private Button btnpdf;
    ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private Button mailing;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtevent.setItems(listids);
         validationSupport.registerValidator(txtnom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtprenom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtage, Validator.createEmptyValidator("text is required"));
          
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (txtnom.getText().length()==0)
        { txtnom.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtnom).play();
    }
        else 
            txtnom.setStyle(null);
        if(txtprenom.getText().length()==0)
        {
            txtprenom.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtprenom).play();
        }
        else 
            txtprenom.setStyle(null);    
   /* if(Date.valueOf(txtdate.getValue())==null)
        {
            txtdate.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtdate).play();
        }
        else 
            txtdate.setStyle(null);  */
    if(txtage.getText().length()==0)
        {
            txtage.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtage).play();
        }
        else 
            txtage.setStyle(null); 
        String nom=txtnom.getText();
        
       ObservableList<Integer> listids=txtevent.getItems();
       // ObservableList<String> list=txttype.getItems();
       String prenom=txtprenom.getText();
       int age=Integer.valueOf(txtage.getText());
       
   Reservation sp=new Reservation(nom,age,prenom,txtevent.getSelectionModel().getSelectedItem(),Userconnected.getId());
    SReservation e=new SReservation();
   e.ajouter(sp);
   SEvent s=new SEvent();
   s.modifierNB();
   /* Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");
Session session = Session.getInstance(props,
 new javax.mail.Authenticator() {
 final String password="191JFT3222";
        protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication("ikbel.benmansour@esprit.tn", password.toCharArray());
}
 });

try {

Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("ikbel.benmansour@esprit.tn"));
                        String mail=((String) txtprenom.getText());
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(mail));
message.setSubject("Avertissement!!");
message.setText("Salut Monsieur \n\n Votre compte est en etat de desactivation veuillez verifier vos parametres!! ");
                       
Transport.send(message);
                         Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Email Envoyé ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
                }catch (MessagingException m){
                    System.out.println(m);
                }*/
 
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilFan.fxml"));
        Parent root = loader.load();
        AcceuilFanController ap= loader.getController();
        btnajout1.getScene().setRoot(root);
    }

    @FXML
    private void PDF(ActionEvent event) {
        String FILE_NAME = "C:\\Users\\malak_6\\Desktop\\reservation.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            //Image imag = Image.getInstance("checkMark.jpg");
            //imag.scaleAbsoluteWidth(100);
            //imag.scaleAbsoluteHeight(92);
           // imag.setAlignment(Image.ALIGN_LEFT);
           // imag.setAlignment(Image.ALIGN_TOP);
           // document.add(imag);
            Paragraph p = new Paragraph();
            p.add("Liste Reservation");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            //Image img=Image.getInstance("C:\\Users\\malak_6\\Desktop\\integration\\src\\GUI\\img\\checkMark.jpg");
           // img.scaleAbsoluteWidth(400);
            //img.scaleAbsoluteHeight(92);
           // imag.setAlignment(Image.ALIGN_LEFT);
           // img.setAlignment(Image.ALIGN_CENTER);
            //document.add(img);
            Connection cnx =MaConnexion.getInstance().getCnx();
            String query = "select nom,idr from reservation where id=(select max(id) from reservation) ";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
                p3 = new Paragraph();
                
                
              
                p3.add(rs.getString("nom"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mail(ActionEvent event) {
        
        
    }
    
}
