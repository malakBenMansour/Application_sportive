/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import services.SEvent;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AffichereventbackController implements Initializable {

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
    @FXML
    private Button btnaffiche;
    @FXML
    private TableView<Event> listEvent;
    @FXML
    private TableColumn<Event, String> txtnom;
    @FXML
    private  TableColumn<Event, String> txtlieu;
    @FXML
    private  TableColumn<Event, Date> txtdate;
    @FXML
    private  TableColumn<Event, Integer> txtnb;
    @FXML
    private  TableColumn<Event, String> txtdesc;
    @FXML
    private  TableColumn<Event, Float> txtprix;
    @FXML
    private  TableColumn<Event, Integer> txtids;
    @FXML
    private  TableColumn<Event, String> txtim;
    @FXML
    private  TableColumn<Event, Integer> txtidp;
    @FXML
    private Button totbtn;
    @FXML
    private Button minbtn;
    @FXML
    private TextField tot;
    @FXML
    private TextField min;
    @FXML
    private Button btnjour;
    @FXML
    private ImageView immm;
    @FXML
    private Button btnshow;
    @FXML
    private Label imag;
    @FXML
    private Button exit;
      ValidationSupport validationSupport = new ValidationSupport();
public ObservableList <Event> data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SEvent p=new SEvent();
         Connection cnx =MaConnexion.getInstance().getCnx();
         List <Event> l=p.afficherFan();
         
         data=FXCollections.observableArrayList(l);
       
      
       
        txtnom.setCellValueFactory(new PropertyValueFactory <Event,String>("nom"));
         txtlieu.setCellValueFactory(new PropertyValueFactory <Event,String>("lieu"));
          txtdate.setCellValueFactory(new PropertyValueFactory <Event,Date>("date"));
           txtnb.setCellValueFactory(new PropertyValueFactory <Event,Integer>("nbparticipation"));
           txtdesc.setCellValueFactory(new PropertyValueFactory <Event,String>("description"));
            txtprix.setCellValueFactory(new PropertyValueFactory <Event,Float>("prix"));
             txtids.setCellValueFactory(new PropertyValueFactory <Event,Integer>("ids"));
             txtim.setCellValueFactory(new PropertyValueFactory <Event,String>("image"));
             txtidp.setCellValueFactory(new PropertyValueFactory <Event,Integer>("idpp"));
   
        listEvent.setItems(data);
    }    

    
   

    @FXML
    private void rechercher(ActionEvent event) {
         FilteredList<Event> filteredData = new FilteredList<>(data, b -> true);

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
} else if (p.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}
else if (p.getDescription().indexOf(lowerCaseFilter)!=-1)
    return true;
    else  
    return false; // Does not match.
});
});
               
     
     
      // 3. Wrap the FilteredList in a SortedList.
SortedList<Event> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(listEvent.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
listEvent.setItems(sortedData); 
    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFxml.fxml"));
        Parent root = loader.load();
       GUI.DocFXMLController
               ap= loader.getController();
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
    private void show(ActionEvent event) {
          //immm.setImage(image);*/
    Event e = listEvent.getSelectionModel().getSelectedItem();
  
    File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        immm.setImage(image);
    }

    @FXML
    private void exit(ActionEvent event) {
         System.exit(0);
    }

 
}
