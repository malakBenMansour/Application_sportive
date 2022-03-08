/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
 * @author Aziz
 */
public class PersocontratController implements Initializable {

    @FXML
    private Button btnperso;
    @FXML
    private Button btncontrat;
    @FXML
    private Button btnacceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void personnel(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherPerso.fxml"));
        Parent root = loader.load();
        AfficherPersoController ap= loader.getController();
        btnperso.getScene().setRoot(root);
    
    }

    @FXML
    private void contrat(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("affichercontrat.fxml"));
        Parent root = loader.load();
        AffichercontratController ap= loader.getController();
        btncontrat.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
     FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }
    
}
