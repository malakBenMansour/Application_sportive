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
 * @author msi
 */
public class MenurecController1 implements Initializable {

    @FXML
    private Button btnprd;
    @FXML
    private Button btnmag;
    @FXML
    private Button btnacceuil;
    @FXML
    private Button btncom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ouvrirprod(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_prod.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_prodController ap= loader.getController();
        btnprd.getScene().setRoot(root);
    }

    @FXML
    private void ouvrirmag(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_mag.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_magController ap= loader.getController();
        btnmag.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
         
    }

    @FXML
    private void ouvrircom(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_commande.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_commandeController ap= loader.getController();
        btncom.getScene().setRoot(root);
    }
    
}
