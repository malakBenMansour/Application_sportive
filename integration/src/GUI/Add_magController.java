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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import services.ServiceMagasin;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Add_magController implements Initializable {
    @FXML
    private Button btnacceuil;
    @FXML
    private TextField txtnom_m;
    @FXML
    private TextField txtadresse_mag;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtnbb;

    /**
     * Initializes the controller class.
     */
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
    private void ajouter(ActionEvent event) throws IOException {
        if (validation ()==true)
        {
         Magasin m ;
         ServiceMagasin sm= new ServiceMagasin();
         String nom_mag = txtnom_m.getText();
         String adresse_mag = txtadresse_mag.getText();
         int nbb = Integer.parseInt(txtnbb.getText());
 
         m=new Magasin (nom_mag,adresse_mag,nbb,Userconnected.getId());
         sm.ajouterpst(m);
        Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Magsin Ajoute ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
    boolean validation (){
        if (txtnom_m.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nom magasin vide");
		alert.setContentText("veuillez saisir le nom du magasin!");

		alert.showAndWait();
                return false ;
        }
         else if (txtadresse_mag.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ adresse magasin vide");
		alert.setContentText("veuillez saisir l'adresse du magasin!");

		alert.showAndWait();
                return false ;
        }
          else if (txtnbb.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nombre bloc  magasin vide");
		alert.setContentText("veuillez saisir le nombre du magasin!");

		alert.showAndWait();
                return false ;
        }
        
        return true ;
    } 
   
    }


