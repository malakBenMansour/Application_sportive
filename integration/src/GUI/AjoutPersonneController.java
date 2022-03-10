/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import java.net.URL;
import java.util.ResourceBundle;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import entities.Personne;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class AjoutPersonneController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txttel;
    @FXML
    private TextField txtmdp;
    @FXML
    private TextField txtnomEquipe;
    @FXML
    private ComboBox<String> txtetat;
    @FXML
    private DatePicker txtdatenaissance;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txtmail;
    
    ObservableList<String> list= FXCollections.observableArrayList("admin","respo","fan");
    ObservableList<String> list_etat= FXCollections.observableArrayList("active","desactive");
    
    
    @FXML
    private Button txtajouter;
    @FXML
    private Button btnimage;
    @FXML
    private TextField txtfile;
    
    
     private String fcs;
    private String fileName;
    private File selectedFile;
    private String path;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          txtrole.setItems(list); 
          txtetat.setItems(list_etat);
          
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
    /*  String nom=txtnom.getText();
      String prenom=txtprenom.getText();
        Date d = Date.valueOf(txtdatenaissance.getValue());
        String adresse=txtadresse.getText(); 
        String mail=txtmail.getText();
     int tel=Integer.valueOf(txttel.getText());
     String mdp=txtmdp.getText();
      String image=txtfile.getText();
    */
    long millis=System.currentTimeMillis();  
      
    
    java.sql.Date date = new java.sql.Date(millis);
     String nomEquipe=txtnomEquipe.getText();
     ObservableList<String> list=txtrole.getItems();
     ObservableList<String> list_etat=txtetat.getItems(); 
    // controle de saisie //
    
       if ( txtnom.getText().isEmpty() || txtnom.getText().matches("[0-9]") || txttel.getText().length()!=8 || txttel.getText().isEmpty() || txttel.getText().matches("[a-z]") || txtprenom.getText().isEmpty() || txtprenom.getText().matches("[0-9]") 
          || txtmdp.getText().isEmpty() ||  txtmail.getText().isEmpty()  ||  txtadresse.getText().isEmpty() || txtadresse.getText().matches("[0-9]") ||  txtfile.getText().isEmpty() ||Date.valueOf(txtdatenaissance.getValue()).after(date)  ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ecrits").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
       }
       
      
       else {
    
          
     ////
     Personne p=new Personne(txtnom.getText(),txtprenom.getText(),Date.valueOf(txtdatenaissance.getValue()),txtadresse.getText(),txtmail.getText(),Integer.valueOf(txttel.getText()),txtrole.getSelectionModel().getSelectedItem(),txtmdp.getText(),nomEquipe,txtetat.getSelectionModel().getSelectedItem(),txtfile.getText());
      PersonneService sp=new PersonneService();
      
      sp.ajouter(p);
      /*
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur ajouté avec Sucees!");
            alert.show();*/
      Notifications notificationBuilder=Notifications.create()
              .title("Notifications").text("Utilisateur ajouuté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
     
      
       } } 

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
        Parent root = loader.load();
       AfficherPersonneController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    
    }

    @FXML
    private void image(ActionEvent event) {
        FileChooser chooser=new FileChooser();
       chooser.showOpenDialog(null);
      chooser.setInitialDirectory(new File("C:\\Users\\malak_6\\Desktop\\JavaFx_pi\\src\\gui\\img"));
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
        File destination = new File(System.getProperty("user.dir") + "\\src\\gui\\img\\" + key + fileName);
        String url = "C:/Users/malak_6/Desktop/JavaFx_pi/src/gui/img/" + key + fileName;
        txtfile.setText(url);
         }
         //////////////////
        
         
         
         
    }


   
}
