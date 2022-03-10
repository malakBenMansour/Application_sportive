/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Afficher_recfanController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnnbj;
    @FXML
    private TextField nbrec;
    @FXML
    private ComboBox<String> comborec;
    @FXML
    private RadioButton asc;
    @FXML
    private ToggleGroup ordre;
    @FXML
    private RadioButton desc;
    @FXML
    private ListView<Reclamation> listrec;
   
    String col,ord;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listrec.setCellFactory(new ReclamationCellFactory());
         ReclamationService as = new ReclamationService ();
        List<Reclamation> la = as.afficher_rec_fan();
     listrec.getItems().clear();
      ObservableList<Reclamation> datalist = FXCollections.observableArrayList(la);
      listrec.setItems(datalist);
      nbrec.setText(String.valueOf(la.size()));
      
      comborec.getItems().removeAll(comborec.getItems());
     comborec.getItems().addAll("Titre", "Etat","Date");
    comborec.getSelectionModel().select("Titre");
    col="titre";
    ord="ASC"; 
    txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        research();
            
            }
            });
            comborec.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (comborec.getSelectionModel().getSelectedItem().equals("Titre"))
            col="titre";
        else if   (comborec.getSelectionModel().getSelectedItem().equals("Etat"))
            col="etat" ;
     else if   (comborec.getSelectionModel().getSelectedItem().equals("Date"))
            col="dateajout" ;
research();

            });
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_rec_fan.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_rec_fanController ap= loader.getController();
        btnajout.getScene().setRoot(root);
    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("AcceuilFan.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilFanController ap= loader.getController();
        btnretour.getScene().setRoot(root);
        
    }

    @FXML
    private void nbrjoueur(ActionEvent event) {
    }

    @FXML
    private void ordreact(ActionEvent event) {
         if (asc.isSelected())
            ord="ASC";
        else if    (desc.isSelected())
            ord="DESC";
        
        research();
    }
    
    void research(){
         listrec.setCellFactory(new ReclamationCellFactory());
         ReclamationService as = new ReclamationService ();
        List<Reclamation> la = as.afficher_rec_critere_fan(txtrecherche.getText(),col,ord,Userconnected.getId());
     listrec.getItems().clear();
      ObservableList<Reclamation> datalist = FXCollections.observableArrayList(la);
      listrec.setItems(datalist);
      nbrec.setText(String.valueOf(la.size()));
    }
    
}
