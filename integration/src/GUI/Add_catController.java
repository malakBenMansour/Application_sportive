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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import services.ActualiteService;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Add_catController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private Button btnacceuil;
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveCategorie(ActionEvent event) throws IOException {
    if(verif())
        {
        String nom=tfNom.getText();
    
    
    Categorie c =new Categorie(nom,Userconnected.getId());
    CategorieService cs= new CategorieService();
    cs.ajouter(c);
     Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Catégorie Ajoutée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ajout");
		alert.setHeaderText("Ajout Catégorie");
		alert.setContentText("Ajout effectué avec succées");
		alert.showAndWait();
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherCat.fxml"));
                  Parent root = (Parent)loader.load();
        AfficherCatController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
        }
    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }
    boolean verif(){
          
       if (tfNom.getText().length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Nom!");

		alert.showAndWait();
                return false;
          }
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
    
