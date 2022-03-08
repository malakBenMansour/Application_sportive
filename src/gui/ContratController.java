/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    
       FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController a= loader.getController();
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
      
   
  
        String nom=txtnom.getText();
        Date d = Date.valueOf(txtdatec.getValue());
       ObservableList<String> listdp=txtidp.getItems();
        ObservableList<String> list=txttype.getItems();
       //Personnel pr= p.rechercherr(new Personnel(txtidp.getValue())).get(0);
       Personnel rec = p.rechercherr(new Personnel(txtidp.getValue())).get(0) ;
   Contrat sp=new Contrat(nom,d,txttype.getSelectionModel().getSelectedItem(),rec.getId());
    ContratService s=new ContratService();
   s.ajouterc(sp);
      FXMLLoader loader= new FXMLLoader(getClass().getResource("affichercontrat.fxml"));
        Parent root = loader.load();
        AffichercontratController a= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }
    
}
