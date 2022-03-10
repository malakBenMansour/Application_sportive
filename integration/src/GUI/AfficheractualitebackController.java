/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Actualite;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ActualiteService;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class AfficheractualitebackController implements Initializable {

    @FXML
    private Button btnretour;
    @FXML
    private ImageView btnacceuil;
   @FXML
    private TableView<Actualite> tbact;
    @FXML
    private TableColumn<Actualite, String> col_titre;
    @FXML
    private TableColumn<Actualite, String> col_desc;
    @FXML
    private TableColumn<Actualite, Date> col_dat;
 @FXML
    private TableColumn<Actualite, Integer> col_id;
  @FXML
    private TableColumn<Actualite, ImageView> col_img;
    
@FXML
    private TableColumn<Actualite, Integer> col_idp;

ActualiteService as= new ActualiteService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        affiche_tab();
    
    
    }    
    private void affiche_tab() {
    
    List<Actualite> le=as.afficherA();
        ObservableList<Actualite> datalist = FXCollections.observableArrayList(le);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_dat.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
        col_idp.setCellValueFactory(new PropertyValueFactory<>("idres"));
        tbact.setItems(datalist);
      
        tbact.refresh();
        
         
    
     
    }


    private void retourHome(ActionEvent event) throws IOException {
       /*  FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFxml.fxml"));
         Parent root = loader.load();
       DocFXMLController ap= loader.getController();
        btnretour.getScene().setRoot(root);
   */ }

    @FXML
    private void retourHome(MouseEvent event) throws IOException {
       /*  FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFxml.fxml"));
         Parent root = loader.load();
       DocFXMLController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);*/
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFxml.fxml"));
         Parent root = loader.load();
       DocFXMLController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }

    
}
