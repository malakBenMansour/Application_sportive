/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Actualite;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import services.ActualiteService;
import services.CategorieService;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherCatController implements Initializable {

   
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnnbj;
    @FXML
    private TextField nbact;

    @FXML
    private Button btnajoutcat;
   
    
    
    @FXML
    private TableView<Categorie> tbcat;
    @FXML
    private TextField nbcat;
    @FXML
    private TableColumn<Categorie, String> col_nom;
    /**
     * Initializes the controller class.
     */
    CategorieService as= new CategorieService();
   
    @FXML
    private Button btnajout;
    @FXML
    private TableColumn<Categorie, Integer> col_id;
    @FXML
    private TableColumn<Categorie, Integer> col_idc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        affiche_tab();
 
tbcat.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
               
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Edit_cat.fxml"));
       
       Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherActController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        Edit_catController act= loader.getController();
        
 act.setdata(tbcat.getSelectionModel().getSelectedItem().getId());
act.charger_act();
     btnajout.getScene().setRoot(root);       
                              
            }
            }         
        });
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_cat.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_catController ap= loader.getController();
        btnajoutcat.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
    try{
         CategorieService cs= new CategorieService();
      cs.supprimer(tbcat.getSelectionModel().getSelectedItem().getId()); 
      affiche_tab();
       Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Catégorie Supprimée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
		alert.setTitle("Supression");
		alert.setHeaderText("Supprimer catégorie");
		alert.setContentText("Supression effectuée avec succès!");

		alert.showAndWait();
        }
        catch (Exception ex) {
                    
                }
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
   FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }

    private void afficher(ActionEvent event) {
      affiche_tab();
    }
    private void affiche_tab() {
        List<Categorie> le=as.afficher();
        tbcat.getItems().clear();
        ObservableList<Categorie> datalist = FXCollections.observableArrayList(le);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_idc.setCellValueFactory(new PropertyValueFactory<>("idpr"));
        tbcat.setItems(datalist);
        tbcat.refresh();
        nbcat.setText(String.valueOf(le.size()));
    }

    @FXML
    private void nbrjoueur(ActionEvent event) {
    }
    
}
