/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import entities.Personnel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
//import services.Personnel;
import services.PersonnelService;
import utils.MyDB;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import utils.MyDB;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AfficherPersoController implements Initializable {

    private ListView<Personnel> listPersonnel;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnretour;
    
    public ObservableList <Personnel> data=FXCollections.observableArrayList();
     public ObservableList <Personnel> dataa=FXCollections.observableArrayList();
    private Button modif;
    @FXML
    private Button btnnbj;
    @FXML
    private TextField nb;
    @FXML
    private Button btnnbe;
    
   
  
    @FXML
    private TextField nbe;
    @FXML
    private TableColumn<Personnel, String> txtnom;
    @FXML
    private TableColumn<Personnel, String> txtprenom;
    @FXML
    private TableColumn<Personnel, Date> txtdate;
    @FXML
    private TableColumn<Personnel, String> txtadresse;
    @FXML
    private TableColumn<Personnel, String> txtmail;
    @FXML
    private TableColumn<Personnel, Integer> txttel;
    @FXML
    private TableColumn<Personnel, Float> txtsalaire;
    @FXML
    private TableColumn<Personnel, String> txtsport;
    @FXML
    private TableColumn<Personnel, String> txtcategorie;
    @FXML
    private TableColumn<Personnel, String> txtrole;
    @FXML
    private TableView<Personnel> tablepersonnel;
    @FXML
    private Button btnpdf;
   
    private Button btnvideo;
    @FXML
    private Button btnexit;
    @FXML
    private HBox calcul;
    private Button calculatrice;
    @FXML
    private Button excel;
  
   Connection connexion;
    Statement stm;
    @FXML
    private TableColumn<Personnel, Integer> txtcin;
    @FXML
    private Button btnmail;
    @FXML
    private TableColumn<Personnel, String> txtimage;
    @FXML
    private ImageView immm;
    @FXML
    private Button btnshow;

    
      
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
       
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPersoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Personnel.fxml"));
        Parent root = loader.load();
       PersonnelController ap= loader.getController();
        btnajout.getScene().setRoot(root);
       
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Personnel personne=tablepersonnel.getSelectionModel().getSelectedItem();
       PersonnelService sp=new PersonnelService();
       sp.supprimer(personne.getId());
       data.clear();
       this.afficher(event);
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        
   
       FilteredList<Personnel> filteredData = new FilteredList<>(data, b -> true);

// 2. Set the filter Predicate whenever the filter changes.
txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(p -> {
// If filter text is empty, display all persons.

if (newValue == null || newValue.isEmpty()) {
return true;
}

// Compare first name and last name of every person with filter text.
String lowerCaseFilter = newValue.toLowerCase();

if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
return true; // Filter matches first name.
} else if (p.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}
else if (p.getAdresse().indexOf(lowerCaseFilter)!=-1)
    return true;
    else  
    return false; // Does not match.
});
});
               
     
     
      // 3. Wrap the FilteredList in a SortedList.
SortedList<Personnel> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(tablepersonnel.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
tablepersonnel.setItems(sortedData);


    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }

    private void afficher(ActionEvent event) throws SQLException {
         
        }
private void afficher () throws SQLException {
    PersonnelService p=new PersonnelService();
         Connection cnx =MyDB.getInstance().getConnexion();
         List <Personnel> l=p.afficherpersonne();
       
         data=FXCollections.observableArrayList(l);

        txtcin.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("cin"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Personnel,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Personnel,String>("prenom"));
          txtdate.setCellValueFactory(new PropertyValueFactory <Personnel,Date>("datenaissance"));
           txtadresse.setCellValueFactory(new PropertyValueFactory <Personnel,String>("adresse"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Personnel,String>("mail"));
             txttel.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("tel"));
             txtsalaire.setCellValueFactory(new PropertyValueFactory <Personnel,Float>("salaire"));
              txtimage.setCellValueFactory(new PropertyValueFactory <Personnel , String>("image")); 
             txtrole.setCellValueFactory(new PropertyValueFactory <Personnel,String>("role"));
              txtsport.setCellValueFactory(new PropertyValueFactory <Personnel,String>("sport"));
              txtcategorie.setCellValueFactory(new PropertyValueFactory <Personnel,String>("categorie"));
      
        tablepersonnel.setItems(data);
        
        // modification 
        
        
        txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateNom(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
       
        txtprenom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtprenom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatePrenom(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
                     tablepersonnel.refresh();
         });
        
        txtadresse.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtadresse.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateAdresse(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setAdresse(e.getNewValue());
                     tablepersonnel.refresh();
         });
          
        txtmail.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtmail.setOnEditCommit((e) ->
                {
            if( p.updatemail(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setMail(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtsport.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtsport.setOnEditCommit((e) ->
                {
            if( p.upsport(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtrole.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtrole.setOnEditCommit((e) ->
                {
            if( p.updaterole(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtcategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtcategorie.setOnEditCommit((e) ->
                {
            if( p.updatecategorie(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
         
       
        
         
        
        
        tablepersonnel.setEditable(true);
                    
}
   @FXML
    private void nbrjoueur(ActionEvent event) throws SQLException {
     PersonnelService sp=new PersonnelService();
     
         Connection cnx =MyDB.getInstance().getConnexion();
     
        String x=sp.totalJoueurs();
     nb.setText(x);
          
    }

    @FXML
    private void nbrentraineurs(ActionEvent event) throws SQLException {
    PersonnelService sp=new PersonnelService();
     
         Connection cnx =MyDB.getInstance().getConnexion();
    
        String x=sp.totalEntraineurs();
     nbe.setText(x);
    }

    @FXML
    private void pdf(ActionEvent event) throws IOException {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("web.fxml"));
        Parent root = loader.load();
        WebController ap= loader.getController();
        btnvideo.getScene().setRoot(root);
    }

   /* private void play(ActionEvent event) {
         String path = new File("C:\\Users\\Aziz\\Desktop\\connect_sport\\src\\gui\\img\\aziz.mp4").getAbsolutePath();
         media=new Media(new File(path).toURI().toString());
         mediaplayer =new MediaPlayer(media);
         video.setMediaPlayer(mediaplayer);
         mediaplayer.setAutoPlay(true);
       
    }*/

   

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

   

    @FXML
    private void exporterexcel(ActionEvent event) {
        connexion = MyDB.getInstance().getConnexion();
        excel.setFont(Font.font("personnel",15));
        excel.setOnAction(e ->{
        
        String req = "SELECT * FROM personnel ";
            Statement st;
            try {
                st = connexion.prepareStatement(req);
                ResultSet rs = st.executeQuery(req);
               XSSFWorkbook wb=new XSSFWorkbook();
               XSSFSheet sheet=wb.createSheet("aziz");
               XSSFRow header=sheet.createRow(0);
               header.createCell(0).setCellValue("cin");
               header.createCell(1).setCellValue("nom");
               header.createCell(2).setCellValue("prenom");
               header.createCell(3).setCellValue("datenaissance");
               header.createCell(4).setCellValue("adresse");
               header.createCell(5).setCellValue("mail");
               header.createCell(6).setCellValue("tel");
               header.createCell(7).setCellValue("salaire");
               header.createCell(8).setCellValue("sport");
               header.createCell(9).setCellValue("categorie");
               header.createCell(10).setCellValue("role");
               int index=1;
               while (rs.next()) {
                XSSFRow row=sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                 
         row.createCell(1).setCellValue(rs.getString("nom"));
          row.createCell(2).setCellValue(rs.getString("prenom"));
          row.createCell(3).setCellValue(rs.getString("datenaissance"));
          row.createCell(4).setCellValue(rs.getString("adresse"));
          row.createCell(5).setCellValue(rs.getString("mail"));
          row.createCell(6).setCellValue(rs.getString("tel"));
          row.createCell(7).setCellValue(rs.getString("salaire"));
           row.createCell(8).setCellValue(rs.getString("sport"));
            row.createCell(9).setCellValue(rs.getString("categorie"));
             row.createCell(10).setCellValue(rs.getString("role"));
             index++;
           
        }
               FileOutputStream fileout= new FileOutputStream("Personnel.xlsx");
               wb.write(fileout);
               fileout.close();
               
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(AfficherPersoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AfficherPersoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
        
    
             });  
       

    }

    @FXML
    private void mail(ActionEvent event) {
        Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                  
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mohamedaziz.benzarti@esprit.tn", "191JMT3169");
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mohamedaziz.benzarti@esprit.tn"));
                        String mail=((String) txtmail.getCellData(tablepersonnel.getSelectionModel().getFocusedIndex()));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(mail));
			message.setSubject("Avertissement!!");
			message.setText("Salut Monsieur "+((String) txtnom.getCellData(tablepersonnel.getSelectionModel().getFocusedIndex()))+" "+((String) txtprenom.getCellData(tablepersonnel.getSelectionModel().getFocusedIndex()))+".\n\n Votre compte est en etat de desactivation veuillez verifier vos parametres!! ");
                        
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
                }catch (Exception e){
                }
    }

    @FXML
    private void show(ActionEvent event) {
         Personnel e = tablepersonnel.getSelectionModel().getSelectedItem();
 
    File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        immm.setImage(image);

    }
}

  

