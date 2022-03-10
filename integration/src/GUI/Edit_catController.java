/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Actualite;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ActualiteService;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Edit_catController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnacceuil;
public  int id_cat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuil.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    private void editCategorie(ActionEvent event) throws IOException {
  if(verif()){
        String nom=tfNom.getText();
    
    Categorie c =new Categorie(nom,Userconnected.getId());
    CategorieService cs= new CategorieService();
    cs.modifier(id_cat, c);
     FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherCat.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AfficherCatController ap= loader.getController();
        btnmodifier.getScene().setRoot(root);
    }
    }
    public void setdata(int d){
    id_cat=d;
    } 
    public void charger_act(){
        CategorieService cs= new CategorieService();
        Categorie c =cs.afficher_ById(id_cat);
    tfNom.setText(c.getNom());
    
    }  
    boolean verif(){
          
       
           CategorieService cs= new CategorieService();
          
          if(cs.existe_cat(tfNom.getText(),0)){
              Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Catégorie existante");
		alert.setHeaderText("Existe");
		alert.setContentText("Cette catégorie est déja insérée!");

		alert.showAndWait(); 
                return false;
          }
          return true;
          }
}
