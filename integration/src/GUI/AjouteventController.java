/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import com.itextpdf.text.Image;
import entities.Event;
import entities.Sponsor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
//import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import static javafx.util.Duration.millis;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.SEvent;
import services.SSponsor;
//
//import org.apache.commons.io.FileUtils;
/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AjouteventController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtlieu;
    @FXML
    private TextField txtparticip;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;
    @FXML
    private DatePicker txtdate;
    @FXML
    private ComboBox<String> txtids;
    @FXML
    private Button ajouter;
    SSponsor s= new SSponsor();
    ObservableList<String> listids=FXCollections.observableArrayList(s.affichernom());
    @FXML
    private Button afficher;
    @FXML
    private Button acceuil;
 SSponsor sp=new SSponsor();
 ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private Button image;
    private TextField txtimage;
    private String fcs;
    private String fileName;
    private File selectedFile;
    private String path;
    @FXML
    private Label lblimage;
    @FXML
    private TextField txtfile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtids.setItems(listids);
         validationSupport.registerValidator(txtnom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtlieu, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtparticip, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtdesc, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtprix, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtdate, Validator.createEmptyValidator("Date Selection Required"));
      
    }    
    /*public void Calendar(String Titre , String Datedeb , String Datefin) throws MalformedURLException, IOException{
        // Using Calendar api
      /*    URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/c_u5lhds76rf6q93bg5gcpguq9f8@group.calendar.google.com/events");
HttpURLConnection http = (HttpURLConnection)url.openConnection();
http.setRequestMethod("POST");
http.setDoOutput(true);
http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM_kctYg56sECJR_wbqGfREil1j_pAt1KpXUvaACUkuZmdgsPD8I4lPFKlMEpRwXqm12h3JxkOj55aP2thxP-PGvMYYwnIDBsEWtf4-v4G1w5YisfY7bL0zMXty8cmXe9VkTkeYdDgsCTNRwoKaOHKgG");
http.setRequestProperty("Content-Type", "application/json");

String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"T10:00:00.000-06:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"T10:25:00.000-06:00\"\n    }\n\n}";
//T10:00:00.000-06:00 heya 16h yaani 4pm
//String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"\"\n    }\n\n}";
//String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
byte[] out = data.getBytes(StandardCharsets.UTF_8);

OutputStream stream = http.getOutputStream();
stream.write(out);

System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + "Cours added to Calendar Successfully");
http.disconnect();
        
        // end Calendar 
      
} */
    public void Calendar(String Titre , String Datedeb , String Datefin) throws MalformedURLException, IOException{
        // Using Calendar api
          URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/c_u5lhds76rf6q93bg5gcpguq9f8@group.calendar.google.com/events");
HttpURLConnection http = (HttpURLConnection)url.openConnection();
http.setRequestMethod("POST");
http.setDoOutput(true);
http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM-T_VM8jgMQwq1AqpGaOUn1d6EO2TFD3n60OUU_2FFFdgV_9qbpNikPt0MyT6Unb60xa079CbwOZ0pMoLb_GJzZKq5nEWZvinAwkwuWBAMspXWdoJ-0xzi2O1TDut9igeb2Se9uTl_p99CMmVIKlW9w");
http.setRequestProperty("Content-Type", "application/json");

String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"T10:00:00.000-06:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"T10:25:00.000-06:00\"\n    }\n\n}";
//T10:00:00.000-06:00 heya 16h yaani 4pm
//String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"feel the burn Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"\"\n    }\n\n}";
//String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
byte[] out = data.getBytes(StandardCharsets.UTF_8);

OutputStream stream = http.getOutputStream();
stream.write(out);

System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + "Cours added to Calendar Successfully");
http.disconnect();
        
        // end Calendar 
}

    @FXML
    private void ahoutevent(ActionEvent event) throws SQLException, IOException {
        
      long millis=System.currentTimeMillis();  
      
    
    java.sql.Date date = new java.sql.Date(millis); 
    System.out.println(Date.valueOf(txtdate.getValue()));
    if(Date.valueOf(txtdate.getValue()).before(date) || Date.valueOf(txtdate.getValue()).equals(date) )
    {
        Notifications notificationBuilder = Notifications.create().title("date").text("date 9diiim rahou").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
    }
   
    else{
//        System.out.println(Date.valueOf(txtdate.getValue()));
        if (txtnom.getText().length()==0)
        { txtnom.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtnom).play();
    }
        else 
            txtnom.setStyle(null);
        if(txtlieu.getText().length()==0)
        {
            txtlieu.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtlieu).play();
        }
        else 
            txtlieu.setStyle(null);    
    if(txtparticip.getText().length()==0)
        {
            txtparticip.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtparticip).play();
        }
        else 
            txtparticip.setStyle(null);  
    if(txtdesc.getText().length()==0)
        {
            txtdesc.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtdesc).play();
        }
        else 
            txtdesc.setStyle(null); 
    if(txtprix.getText().length()==0)
        {
            txtprix.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtprix).play();
        }
        else 
            txtprix.setStyle(null); 
    
        String nom=txtnom.getText();
        Date d = Date.valueOf(txtdate.getValue());
       ObservableList<String> listids=txtids.getItems();
       // ObservableList<String> list=txttype.getItems();
       String lieu=txtlieu.getText();
       int nbparticipation=Integer.valueOf(txtparticip.getText());
       String description=txtdesc.getText();
       float prix=Float.valueOf(txtprix.getText());
      Sponsor rec = sp.rechercherr(new Sponsor(txtids.getValue())).get(0) ;
      String image=txtfile.getText();
      
// Sponsor s= sp.rechercherr(new Sponsor(txtids.getValue()));
   Event sp=new Event(nom,lieu,d,nbparticipation,description,prix,rec.getId(),image,Userconnected.getId());
    SEvent e=new SEvent();
   e.ajouter(sp);
         Notifications notificationBuilder = Notifications.create().title("ajout").text("event ajouté").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
        Calendar(txtnom.getText(),txtdate.getValue().format(DateTimeFormatter.ISO_DATE),txtdate.getValue().format(DateTimeFormatter.ISO_DATE));
    }
    }
    
    @FXML
    private void afficher(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherEvent.fxml"));
        Parent root = loader.load();
        AfficherEventController ap= loader.getController();
        afficher.getScene().setRoot(root);
         Notifications notificationBuilder = Notifications.create().title("affichage").text("afficher event ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("EvenementSponsor.fxml"));
        Parent root = loader.load();
        EvenementSponsorController ap= loader.getController();
        acceuil.getScene().setRoot(root);
    }

    @FXML
    private void upload(ActionEvent event) throws MalformedURLException {
       /*  FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            //path = selectedFile.getName();
            path = selectedFile.getName();
            viewimage.setImage(new Image(selectedFile.toURI().toURL().toString()));
            viewimage.setFitHeight(150);
            viewimage.setFitWidth(250);
            image.setText(path);

    }*/
       FileChooser chooser=new FileChooser();
       chooser.showOpenDialog(null);
       chooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
       File SelectedFile = chooser.showOpenDialog(null);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
         if (SelectedFile != null) {
             fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
                //System.out.println(fileName);
            }
            String key = UUID.randomUUID().toString();

        fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" + key + fileName);
        String url = "/image/" + key + fileName;
        txtfile.setText(url);
         }
}
}
