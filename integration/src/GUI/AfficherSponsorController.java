/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import entities.Sponsor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.SSponsor;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AfficherSponsorController implements Initializable {

    @FXML
    private TableView<Sponsor> listSponsor;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnretour;
    public ObservableList <Sponsor> data=FXCollections.observableArrayList();
    @FXML
    private Button btnaffiche;
    @FXML
    private TableColumn<Sponsor, Integer> txtid;
    @FXML
    private TableColumn<Sponsor, String> txtnom;
    @FXML
    private TableColumn<Sponsor, String> txtprenom;
    @FXML
    private TableColumn<Sponsor, Date> txtpdate;
    @FXML
    private TableColumn<Sponsor, String> txtadress;
    @FXML
    private TableColumn<Sponsor, Integer> txttel;
    @FXML
    private TableColumn<Sponsor, String> txtmail;
    @FXML
    private ComboBox<String> tri;
    @FXML
    private Button bnttrier;
    @FXML
    private TableColumn<Sponsor, Integer> txtidp;
    @FXML
    private ImageView exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> option=FXCollections.observableArrayList("nom","adresse");
        tri.setItems(option);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ajoutSponsor.fxml"));
        Parent root = loader.load();
        AjoutSponsorController ap= loader.getController();
        btnajout.getScene().setRoot(root);
         Notifications notificationBuilder = Notifications.create().title("sponsor").text("ajout ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
        
    }

    private boolean isClear(){
        Boolean clear = false;
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("this is the content of the warning");
        alert.setTitle("clear all fields");
        alert.setHeaderText("are you sure you want to clear all fields");
        ButtonType cancelButtonType =new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() &&result.get()==ButtonType.OK ) {
        clear = true;
    }
        return clear;
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if(isClear()) {
            txtnom.setText("");
            txtprenom.setText("");
            txtadress.setText("");
        
            
        }
       Sponsor sponsor=listSponsor.getSelectionModel().getSelectedItem();
       SSponsor sp=new SSponsor();
       //sp.supprimer(sponsor.getId());
       sp.supprimer(sponsor.getId());
       data.clear();
       this.afficher(event);
        Notifications notificationBuilder = Notifications.create().title("supprimer").text("suppression avec succée ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
    }

    @FXML
    private void rechercher(ActionEvent event) {
        FilteredList<Sponsor> filteredData = new FilteredList<>(data, b -> true);

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
SortedList<Sponsor> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(listSponsor.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
listSponsor.setItems(sortedData);

    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("EvenementSponsor.fxml"));
        Parent root = loader.load();
        EvenementSponsorController ap= loader.getController();
        btnretour.getScene().setRoot(root);
         Notifications notificationBuilder = Notifications.create().title("home").text("home").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
    }

    @FXML
    private void afficher(ActionEvent event) {
        SSponsor p=new SSponsor();
         Connection cnx =MaConnexion.getInstance().getCnx();
         List <Sponsor> l=p.afficher();
         
         data=FXCollections.observableArrayList(l);
       
      
        txtid.setCellValueFactory(new PropertyValueFactory <Sponsor,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("prenom"));
          txtpdate.setCellValueFactory(new PropertyValueFactory <Sponsor,Date>("datenaissance"));
           txtadress.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("adresse"));
           txttel.setCellValueFactory(new PropertyValueFactory <Sponsor,Integer>("tel"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("mail"));
             
               txtidp.setCellValueFactory(new PropertyValueFactory <Sponsor,Integer>("idp"));
             
        listSponsor.setItems(data);
         txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateNom(listSponsor.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listSponsor.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listSponsor.refresh();
         });
        txtprenom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtprenom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatePrenom(listSponsor.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listSponsor.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listSponsor.refresh();
         });
        txtmail.setCellFactory(TextFieldTableCell.forTableColumn());
        txtmail.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateMail(listSponsor.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listSponsor.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listSponsor.refresh();
         });
        txtadress.setCellFactory(TextFieldTableCell.forTableColumn());
        txtadress.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatePrenom(listSponsor.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listSponsor.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listSponsor.refresh();
         });
           listSponsor.setEditable(true);
            Notifications notificationBuilder = Notifications.create().title("affichage").text("afficher sponsors ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
           
       
    }

    @FXML
    private void trier(ActionEvent event) {
        String type=tri.getValue();
        List<Sponsor> list=new ArrayList<>();
        if(type=="nom"){
            SSponsor sp=new SSponsor();
            list=sp.TRIparNom();
            
        }
        else {
            SSponsor sp=new SSponsor();
            list=sp.TRIparAdresse();
            
        }
         ObservableList<Sponsor> m=FXCollections.observableArrayList();
       
      
        txtid.setCellValueFactory(new PropertyValueFactory <Sponsor,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("prenom"));
          txtpdate.setCellValueFactory(new PropertyValueFactory <Sponsor,Date>("datenaissance"));
           txtadress.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("adresse"));
           txttel.setCellValueFactory(new PropertyValueFactory <Sponsor,Integer>("tel"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Sponsor,String>("mail"));
            list.forEach(e->{
                m.add(e);
                System.out.println(e);
            });
            listSponsor.setItems(m);
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    
    
    
}
