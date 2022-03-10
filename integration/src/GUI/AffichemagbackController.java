/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Magasin;
import java.io.IOException;
import java.net.URL;
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
import services.ServiceMagasin;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AffichemagbackController implements Initializable {

    @FXML
    private Button btnretour;
    @FXML
    private ImageView btnacceuil;
    private TextField nbmag;
    @FXML
    private TableView<Magasin> tablemag;
    @FXML
    private TableColumn<Magasin, Integer> txtid;
    @FXML
    private TableColumn<Magasin, String> txtnom;
    @FXML
    private TableColumn<Magasin, String> txtadresse;
    @FXML
    private TableColumn<Magasin, Integer> txtnbb;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lister_mag();
    }   
    public void lister_mag ()
    {
    ServiceMagasin sm = new ServiceMagasin();
     List<Magasin> lm = sm.afficherF();
     tablemag.getItems().clear();
      ObservableList<Magasin> datalist = FXCollections.observableArrayList(lm);
        txtid.setCellValueFactory(new PropertyValueFactory<>("idm"));
          txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        txtnbb.setCellValueFactory(new PropertyValueFactory<>("nombrebloc"));
    tablemag.setItems(datalist); 
    
    
    }

   

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFxml.fxml"));
         Parent root = loader.load();
       DocFXMLController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    private void retourHome(MouseEvent event) {
    }

    
}
