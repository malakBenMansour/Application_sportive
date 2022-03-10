/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class EvenementSponsorController implements Initializable {

    @FXML
    private Button btnevent;
    @FXML
    private Button btnsponsor;
    @FXML
    private Button btnacceuil;
    @FXML
    private Button res;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void event(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ajoutevent.fxml"));
        Parent root = loader.load();
        AjouteventController ap= loader.getController();
        btnsponsor.getScene().setRoot(root);
    }

    @FXML
    private void sponsor(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherSponsor.fxml"));
        Parent root = loader.load();
        AfficherSponsorController ap= loader.getController();
        btnsponsor.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
        Parent root = loader.load();
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
      FXMLLoader loader= new FXMLLoader(getClass().getResource("affichereservation.fxml"));
        Parent root = loader.load();
        AffichereservationController ap= loader.getController();
        res.getScene().setRoot(root);       
    }
    
}
