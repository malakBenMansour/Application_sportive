/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Actualite;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import services.ActualiteService;
import services.CategorieService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Add_recController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea tfDescription;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnacceuil;
    @FXML
    private TextField tfNum;
    @FXML
    private ComboBox<String> comboCat;
    @FXML
    private ComboBox<String> comboEtat;
ReclamationService rs= new ReclamationService();
CategorieService cs= new CategorieService();
//int p=1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        charge_cat();
        comboEtat.getItems().addAll("En cours", "Traitée","Non Traitée");
        comboEtat.getSelectionModel().select("Non Traitée");
        comboEtat.setDisable(true);
    
    }    
 

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
     FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    private void saveReclamation(ActionEvent event) throws IOException, Exception {
       
        if(verif())
        {
    String titre=tfTitre.getText();
    String description=tfDescription.getText();
    Date date_ajout =(Date) Date.valueOf( tfDate.getValue());
        System.out.println("cat     "+comboCat.getSelectionModel().getSelectedItem());
  int cat=cs.id_categ(comboCat.getSelectionModel().getSelectedItem());
  String etat= comboEtat.getSelectionModel().getSelectedItem();
  String num_c=tfNum.getText();
  
    Reclamation r=new Reclamation(cat,Userconnected.getId(),titre,description,date_ajout,etat,num_c);
   
    rs.ajouter(r);
     Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Réclamation Ajoutée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
    String [] pers = rs.mail_pers();
    String htmlCode = "<h1> Bonjour M/Mme "+pers[1]+",   </h1> <br/> <h2><b>Votre réclamation << "+titre+" >> envoyé le "+date_ajout+" sera traitée le plus tôt possible.</b></h2> <br/> <h3><b>Notre équipe vous remercie pour votre confiance. </b></h3><br/> <h3><b>Cordialement,<br> Connect Sport </b></h3>";
     Mail_rec.sendMail(pers[0],htmlCode);
    
   FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_rec.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_recController ap= loader.getController();
        btnValider.getScene().setRoot(root); 
    }}
    void charge_cat()
    {
        comboCat.getItems().clear();//vider
         List<String> le=cs.afficher_cat();
        ObservableList<String> datalist = FXCollections.observableArrayList(le);
        comboCat.setItems(datalist);
    }
boolean verif(){
   
          if (tfTitre.getText().length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Titre!");

		alert.showAndWait();
                return false;
          }
          else if (tfDescription.getText().length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Descrption!");

		alert.showAndWait();
                return false;
          }
          else if (tfDate.getValue()==null){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Date d'Ajout!");

		alert.showAndWait();
                return false;
          }
          else if (comboEtat.getSelectionModel().getSelectedIndex()==-1){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Etat vide");
		alert.setContentText("Veuillez choisir un Etat!");

		alert.showAndWait();
                return false;
                
          }
        
         else if (comboCat.getSelectionModel().getSelectedIndex()==-1){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Catégorie vide");
		alert.setContentText("Veuillez choisir une catégorie!");

		alert.showAndWait();
                return false;
                
          }
           
          return true;
          }
}
