/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.scene.image.Image;

import entities.Event;
import entities.Sponsor;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
//import org.apache.commons.io.FileUtils;
//import javafx.scene.image.Image;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.SEvent;
import tools.MaConnexion;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
//javafx.scene.image.*;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
//import com.itextpdf.text.Image;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AfficherEventController implements Initializable {

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
    public ObservableList <Event> data=FXCollections.observableArrayList();
  //  public ObservableList <String> data1=FXCollections.observableArrayList();
    @FXML
    private Button btnaffiche;
    
   
    @FXML
    private TableView<Event> listEvent;
    @FXML
    private TableColumn<Event,String> txtnom;
    @FXML
    private TableColumn<Event,String> txtlieu;
    @FXML
    private TableColumn<Event,Date> txtdate;
    @FXML
    private TableColumn<Event, Integer> txtnb;
    @FXML
    private TableColumn<Event, String> txtdesc;
    @FXML
    private TableColumn<Event, Float> txtprix;
    @FXML
    private TableColumn<Event, Integer> txtids;
     @FXML
    private TableColumn<Event, String> txtim;
    @FXML
    private TextField tot;
    @FXML
    private TextField min;
    @FXML
    private Button totbtn;
    @FXML
    private Button minbtn;
    ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private Button btnjour;
    
    @FXML
    private ImageView immm;
    @FXML
    private Button btnshow;
    @FXML
    private Label imag;
    @FXML
    private TableColumn<Event, Integer> txtidp;
    @FXML
    private Button exit;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // alerte();
       
        
    }    
//ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Ajoutevent.fxml"));
        Parent root = loader.load();
        AjouteventController ap= loader.getController();
        btnajout.getScene().setRoot(root);
         Notifications notificationBuilder = Notifications.create().title("kdjsd").text("saved ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Event e=listEvent.getSelectionModel().getSelectedItem();
       SEvent sp=new SEvent();
       //sp.supprimer(sponsor.getId());
       sp.supprimer(e.getId());
       data.clear();
       this.afficher(event);
        Notifications notificationBuilder = Notifications.create().title("suppression").text("suppression avec succée ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
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
           SEvent p=new SEvent();
         Connection cnx =MaConnexion.getInstance().getCnx();
         List <Event> l=p.afficher();
         
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
         txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateNom(listEvent.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listEvent.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listEvent.refresh();
         });
        txtlieu.setCellFactory(TextFieldTableCell.forTableColumn());
        txtlieu.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateLieu(listEvent.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listEvent.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listEvent.refresh();
         });
        txtdesc.setCellFactory(TextFieldTableCell.forTableColumn());
        txtdesc.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateDescripton(listEvent.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     listEvent.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     listEvent.refresh();
         });
       
           listEvent.setEditable(true);
           alerte();
       
    }

    @FXML
    private void totalevent(ActionEvent event) {
        
        SEvent s=new SEvent();
        Connection cnx =MaConnexion.getInstance().getCnx();
         
              String l=s.TotalEvent();
              
                    
                    
                    
                    tot.setText(l);
             
        
    }

    @FXML
    private void minevent(ActionEvent event) {
        
         SEvent s=new SEvent();
        Connection cnx =MaConnexion.getInstance().getCnx();
         
              String l=s.MinEvent();
              
                    
                    
                    
                    min.setText(l);
    }
    
    
    public void alerte()
    {
         SEvent s=new SEvent();
        Connection cnx =MaConnexion.getInstance().getCnx();
         
             boolean t;
             t=s.test();
             if(t==true)
             {
                 JOptionPane.showMessageDialog(null,"vous avez un evenement ajourd'hui");
             }
             else {
                 JOptionPane.showMessageDialog(null,"aucun evenement ajourd'hui");
             }
        
    }

    @FXML
    private void rester(ActionEvent event) {
        Event e=listEvent.getSelectionModel().getSelectedItem();
       SEvent sp=new SEvent();
       //sp.supprimer(sponsor.getId());
       String x=null;
      x=sp.test1(e);
              JOptionPane.showMessageDialog(null,"jours restants "+x);
             
    }

    @FXML
    private void show(ActionEvent event) throws MalformedURLException {
      //String url=((String)txtim.getCellData(listEvent.getSelectionModel().getFocusedIndex()));
    /* String url="C:\\Users\\Espace Sboui\\Desktop\\connectsport\\src\\GUI\\img\\2.jpg";
      Image image=new Image(url);
      immm.setImage(image);*/
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
    
    
    

    
