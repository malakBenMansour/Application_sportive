/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import entities.Sponsor;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.SSponsor;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AjoutSponsorController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txtmail;
    @FXML
    private TextField txttel;
    @FXML
    private DatePicker txtdate;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnacceuil;
ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private Button logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //0000 validationSupport.setErrorDecorationEnabled(false);
        validationSupport.registerValidator(txtnom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtprenom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtadresse, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtmail, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txttel, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtdate, Validator.createEmptyValidator("Date Selection Required"));
      
       
    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
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
    if(txtadresse.getText().length()==0)
        {
            txtadresse.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtadresse).play();
        }
        else 
            txtadresse.setStyle(null); 
    if(txtmail.getText().length()==0)
        {
            txtmail.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtmail).play();
        }
        else 
            txtmail.setStyle(null); 
    if(txttel.getText().length()==0)
        {
            txttel.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txttel).play();
        }
        else 
            txttel.setStyle(null); 
        
        
        String nom=txtnom.getText();
      String prenom=txtprenom.getText();
        Date d = Date.valueOf(txtdate.getValue());
        String adresse=txtadresse.getText(); 
        String mail=txtmail.getText();
     int tel=Integer.valueOf(txttel.getText());
     System.out.println(Userconnected.getId());
     Sponsor p=new Sponsor(nom,  prenom, d, adresse,  tel,  mail,Userconnected.getId());
      SSponsor sp=new SSponsor();
      
    
      
      sp.ajouter(p);
      
       Notifications notificationBuilder = Notifications.create().title("ajout").text("ajout event avec succée ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
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
        btnacceuil.getScene().setRoot(root);
        
       
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
