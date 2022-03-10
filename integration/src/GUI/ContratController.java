/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Contrat;
import entities.Personnel;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import services.ContratService;
import services.PersonnelService;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ContratController implements Initializable  {

    @FXML
    private TextField txtnom;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtidp;
    @FXML
    private ComboBox<String> txttype;
    @FXML
    private DatePicker txtdatec;
    @FXML
    private Button butajoutc;
ObservableList<String> list= FXCollections.observableArrayList("professionnel","jeune","debutant");
PersonnelService p= new PersonnelService();

       PersonnelService s=new PersonnelService();
 
       
     ObservableList<String> listidp= FXCollections.observableList(s.affichernom());
       
      //  ObservableList<Integer> listidp= FXCollections.observableList(s.affichecin());
    @FXML
    private Button logout;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    
             txtidp.setItems(listidp);
        
txttype.setItems(list);
// TODO
    }    


    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
    
       FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
        Parent root = loader.load();
        AcceuilResponsableController a= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }

    @FXML
    private void addcontrat(ActionEvent event) throws SQLException, IOException {
        /*Contrat c= new Contrat();
        String nom=txtnom.getText();
        Date d = Date.valueOf(txtdatec.getValue());
        int idp=c.getPersonnel().getId();
        ObservableList<String> list=txttype.getItems();
        
       ContratService sp=new ContratService();*/
      
   
  
     //   String nom=txtnom.getText();
        //Date d = Date.valueOf(txtdatec.getValue());
       ObservableList<String> listdp=txtidp.getItems();
        ObservableList<String> list=txttype.getItems();
       //Personnel pr= p.rechercherr(new Personnel(txtidp.getValue())).get(0);
        long millis=System.currentTimeMillis();  
    java.sql.Date date = new java.sql.Date(millis); 
      /* Personnel rec = p.rechercherr(new Personnel(txtidp.getValue())).get(0) ;
   Contrat sp=new Contrat(nom,d,txttype.getSelectionModel().getSelectedItem(),rec.getId());
    ContratService s=new ContratService();
   s.ajouterc(sp);*/
       if ( txtnom.getText().isEmpty() || txtnom.getText().matches("[0-9]") ||Date.valueOf(txtdatec.getValue()).after(date) || Date.valueOf(txtdatec.getValue()).equals(date)){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("VERIFIER CHAMP").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
            Personnel rec = p.rechercherr(new Personnel(txtidp.getValue())).get(0) ;
   Contrat sp=new Contrat(txtnom.getText(),Date.valueOf(txtdatec.getValue()),txttype.getSelectionModel().getSelectedItem(),rec.getId(),Userconnected.getId());
    ContratService s=new ContratService();
   s.ajouterc(sp);
   Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Contrat Ajoute ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
   FXMLLoader loader= new FXMLLoader(getClass().getResource("affichercontrat.fxml"));
        Parent root = loader.load();
        AffichercontratController a= loader.getController();
        txtacceuil.getScene().setRoot(root);
       }
      
    }

    @FXML
    private void out(ActionEvent event) throws IOException {
        Userconnected.setId(0);
                  Userconnected.setNom("");
                  Userconnected.setPrenom("");              
                  //Userconnected.setDatenaissance(d);                      
                  Userconnected.setAdresse("");                              
                  Userconnected.setMail("");                                    
                  Userconnected.setTel(0);
                  Userconnected.setRole("");
                  Userconnected.setMdp("");
                  Userconnected.setNomEquipe("");
                  Userconnected.setEtat("");
       
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController ap= loader.getController();
       
        logout.getScene().setRoot(root);
    }
    
}
