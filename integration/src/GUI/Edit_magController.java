/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Magasin;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import services.ServiceMagasin;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Edit_magController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnacceuil;
    @FXML
    private TextField txtnbb;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtadresse;

    /**
     * Initializes the controller class.
     */
    public  int id_mag;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
    if (validation()==true)
    {
    String nom=txtnom.getText();
    String adresse=txtadresse.getText();
    Integer nombrebloc=Integer.parseInt(txtnbb.getText());    
    
    Magasin m =new Magasin(nom,adresse,nombrebloc,Userconnected.getId());
    ServiceMagasin as= new ServiceMagasin();
    as.modifier(id_mag, m);
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Modifier");
		alert.setHeaderText("Modifier magasin");
		alert.setContentText(" Réusssite de la modification ");

		alert.showAndWait();
                 Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Magasin Modifié ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
     FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_mag.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_magController ap= loader.getController();
        btnajouter.getScene().setRoot(root);
    }
    }
    public void setdata(int d){
    id_mag=d;
    } 
    public void charger_act(){
       
    ServiceMagasin as= new ServiceMagasin();
        List<Magasin> a =as.afficherById(id_mag);
       
        txtnom.setText(a.get(0).getNom());
   
      txtadresse.setText(a.get(0).getAdresse());
      txtnbb.setText(String.valueOf(a.get(0).getNombrebloc()));
    
    }   
     boolean validation (){
        if (txtnom.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nom magasin vide");
		alert.setContentText("veuillez saisir le nom du magasin!");

		alert.showAndWait();
                return false ;
        }
         else if (txtadresse.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ adresse magasin vide");
		alert.setContentText("veuillez saisir l'adresse du magasin!");

		alert.showAndWait();
                return false ;
        }
          else if (txtnbb.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nombre bloc  magasin vide");
		alert.setContentText("veuillez saisir le nombre du magasin!");

		alert.showAndWait();
                return false ;
        }
        return true ;
    } 
   
}
