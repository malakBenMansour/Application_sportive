/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AcceuilResponsableController implements Initializable {

    @FXML
    private Button btnResponsable;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnequipe;
    @FXML
    private Button logout;
    @FXML
    private Button btnrec;
    @FXML
    private Button btnact;
    @FXML
    private Button magasinbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void acceuilEvent(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("EvenementSponsor.fxml"));
        Parent root = loader.load();
        EvenementSponsorController ap= loader.getController();
        btnEvent.getScene().setRoot(root);
    }

    @FXML
    private void equipe(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("persocontrat.fxml"));
        Parent root = loader.load();
        PersocontratController ap= loader.getController();
        btnequipe.getScene().setRoot(root);
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

    @FXML
    private void profil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("ProfilResponsable.fxml"));
        Parent root = loader.load();
        ProfilResponsableController ap= loader.getController();
         logout.getScene().setRoot(root);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec.fxml"));
        Parent root = loader.load();
      MenurecController ap= loader.getController();
         btnrec.getScene().setRoot(root);
    }

    @FXML
    private void actualites(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherAct.fxml"));
        Parent root = loader.load();
      AfficherActController ap= loader.getController();
         btnact.getScene().setRoot(root);
    }

    @FXML
    private void magasin(ActionEvent event) throws IOException {
        
         FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
        Parent root = loader.load();
      MenurecController1 ap= loader.getController();
         magasinbtn.getScene().setRoot(root);
    }
    
}
