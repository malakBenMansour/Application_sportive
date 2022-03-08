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
public class AcceuilController implements Initializable {

    @FXML
    private Button txtpers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void perso(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("persocontrat.fxml"));
        Parent root = loader.load();
        PersocontratController ap= loader.getController();
        txtpers.getScene().setRoot(root);
    }
    
}
