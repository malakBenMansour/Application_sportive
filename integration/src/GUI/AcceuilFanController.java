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
 * @author malak_6
 */
public class AcceuilFanController implements Initializable {

    @FXML
    private Button equipebtn;
    @FXML
    private Button magasinbtn;
    @FXML
    private Button actualitebtn;
    @FXML
    private Button eventbtn;
    @FXML
    private Button logout;
    @FXML
    private Button btnprofil;
    @FXML
    private Button recbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goequipe(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("fanpersonnel.fxml"));
        Parent root = loader.load();
        GUI.FanpersonnelController ap= loader.getController();
        equipebtn.getScene().setRoot(root);
        
    }

    @FXML
    private void goevent(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("eventfan.fxml"));
        Parent root = loader.load();
        EventfanController ap= loader.getController();
        eventbtn.getScene().setRoot(root);
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
    private void actualite(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("actfan.fxml"));
        Parent root = loader.load();
        GUI.ActfanController ap= loader.getController();
        actualitebtn.getScene().setRoot(root);
    }

    @FXML
    private void profil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ProfilFan.fxml"));
        Parent root = loader.load();
        ProfilFanController ap= loader.getController();
        btnprofil.getScene().setRoot(root);
    }

    @FXML
    private void recn(ActionEvent event) throws IOException {
     FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_recfan.fxml"));
        Parent root = loader.load();
        Afficher_recfanController ap= loader.getController();
        recbtn.getScene().setRoot(root);
    }

    @FXML
    private void magasin(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("produitfan.fxml"));
        Parent root = loader.load();
        ProduitfanController ap= loader.getController();
        magasinbtn.getScene().setRoot(root);
    }
    
}
