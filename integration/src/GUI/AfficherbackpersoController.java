/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Personnel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import services.PersonnelService;
import tools.MyDB;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AfficherbackpersoController implements Initializable {

    @FXML
    private HBox calcul;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnretour;
    @FXML
    private TableView<Personnel> tablepersonnel;
    @FXML
    private TableColumn<Personnel, Integer> txtcin;
    @FXML
    private TableColumn<Personnel, String> txtnom;
    @FXML
    private TableColumn<Personnel, String> txtprenom;
    @FXML
    private TableColumn<Personnel, Date> txtdate;
    @FXML
    private TableColumn<Personnel, String> txtadresse;
    @FXML
    private TableColumn<Personnel, String> txtmail;
    @FXML
    private TableColumn<Personnel, Integer> txttel;
    @FXML
    private TableColumn<Personnel, Float> txtsalaire;
    @FXML
    private TableColumn<Personnel, String> txtimage;
    @FXML
    private TableColumn<Personnel, String> txtsport;
    @FXML
    private TableColumn<Personnel, String> txtcategorie;
    @FXML
    private TableColumn<Personnel, String> txtrole;
    @FXML
    private TableColumn<Personnel, Integer> txtid;
    @FXML
    private TableColumn<Personnel, Integer> txtidr;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnshow;
public ObservableList <Personnel> data=FXCollections.observableArrayList();
    @FXML
    private ImageView imag;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPersoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

   
    @FXML
    private void rechercher(ActionEvent event) {
     FilteredList<Personnel> filteredData = new FilteredList<>(data, b -> true);

// 2. Set the filter Predicate whenever the filter changes.
txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(p -> {
// If filter text is empty, display all persons.

if (newValue == null || newValue.isEmpty()) {
return true;
}

// Compare first name and last name of every person with filter text.
String lowerCaseFilter = newValue.toLowerCase();

if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
return true; // Filter matches first name.
} else if (p.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}
else if (p.getAdresse().indexOf(lowerCaseFilter)!=-1)
    return true;
    else  
    return false; // Does not match.
});
});
               
     
     
      // 3. Wrap the FilteredList in a SortedList.
SortedList<Personnel> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(tablepersonnel.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
tablepersonnel.setItems(sortedData);

    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFXML.fxml"));
        Parent root = loader.load();
        DocFXMLController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }

   
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
private void afficher () throws SQLException {
    PersonnelService p=new PersonnelService();
         Connection cnx =MyDB.getInstance().getConnexion();
         List <Personnel> l=p.afficherAdmin();
       
         data=FXCollections.observableArrayList(l);

        txtcin.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("cin"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Personnel,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Personnel,String>("prenom"));
          txtdate.setCellValueFactory(new PropertyValueFactory <Personnel,Date>("datenaissance"));
           txtadresse.setCellValueFactory(new PropertyValueFactory <Personnel,String>("adresse"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Personnel,String>("mail"));
             txttel.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("tel"));
             txtsalaire.setCellValueFactory(new PropertyValueFactory <Personnel,Float>("salaire"));
              txtimage.setCellValueFactory(new PropertyValueFactory <Personnel , String>("image")); 
             txtrole.setCellValueFactory(new PropertyValueFactory <Personnel,String>("role"));
              txtsport.setCellValueFactory(new PropertyValueFactory <Personnel,String>("sport"));
              txtcategorie.setCellValueFactory(new PropertyValueFactory <Personnel,String>("categorie"));
                txtidr.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("idr"));
      txtid.setCellValueFactory(new PropertyValueFactory <Personnel,Integer>("id"));
        tablepersonnel.setItems(data);
        
}

    @FXML
    private void show(ActionEvent event) {
         Personnel e = tablepersonnel.getSelectionModel().getSelectedItem();
 
    File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        imag.setImage(image);
    }
}
