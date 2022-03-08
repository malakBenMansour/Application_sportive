/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Contrat;
import entities.Personnel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import services.ContratService;
import services.PersonnelService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AffichercontratController implements Initializable {

    @FXML
    private HBox calcul;
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
    private TableView<Contrat> tablecontrat;
    @FXML
    private TableColumn<Contrat, Integer> txtid;
    @FXML
    private TableColumn<Contrat, String> txtnom;
    @FXML
    private TableColumn<Contrat, Date> txtdate;
    @FXML
    private TableColumn<Contrat,String> txtidp;
    @FXML
    private Button btnpdf;
    private Button btnvideo;
    @FXML
    private Button btnexit;
    private Button calculatrice;
    @FXML
    private TableColumn<Contrat, String> txttype;
public ObservableList <Contrat> data=FXCollections.observableArrayList();
    @FXML
    private Button btncontpro;
    @FXML
    private TextField nb;
    @FXML
    private Button btncontdebut;
    @FXML
    private Button btncontjeun;
    @FXML
    private TextField nb1;
    @FXML
    private TextField nb2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/contrat.fxml"));
        Parent root = loader.load();
       ContratController ap= loader.getController();
        btnajout.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         Contrat contrat=tablecontrat.getSelectionModel().getSelectedItem();
       ContratService sp=new ContratService();
       sp.supprimerc(contrat.getId());
       data.clear();
       this.afficher(event);
    }

    @FXML
    private void rechercher(ActionEvent event) {
         FilteredList<Contrat> filteredData = new FilteredList<>(data, b -> true);
         txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(p -> {
// If filter text is empty, display all persons.

if (newValue == null || newValue.isEmpty()) {
return true;
}
        String lowerCaseFilter = newValue.toLowerCase();

if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
return true; // Filter matches first name.
} /*else if (p.get().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}*/
else if (p.getType().indexOf(lowerCaseFilter)!=-1)
    return true;
    else  
    return false; // Does not match.
});
});
               
     
     
      // 3. Wrap the FilteredList in a SortedList.
SortedList<Contrat> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(tablecontrat.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
tablecontrat.setItems(sortedData);


    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
        
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }

    @FXML
    private void afficher(ActionEvent event) throws SQLException {
     ContratService p=new ContratService();
         Connection cnx =MyDB.getInstance().getConnexion();
         List <Contrat> l=p.afficherc();
         Contrat c=new Contrat();
         data=FXCollections.observableArrayList(l);

       // txtid.setCellValueFactory(new PropertyValueFactory <Contrat,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Contrat,String>("nom"));
        // txtprenom.setCellValueFactory(new PropertyValueFactory <Personnel,String>("prenom"));
          txtdate.setCellValueFactory(new PropertyValueFactory <Contrat,Date>("datecontrat"));
           txttype.setCellValueFactory(new PropertyValueFactory <Contrat,String>("type"));
            txtidp.setCellValueFactory(new PropertyValueFactory <Contrat,String>("idp"));
             
      
        tablecontrat.setItems(data);
             
      
        tablecontrat.setItems(data);
        
        // modification 
        
        
      txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateNom(tablecontrat.getItems().get(e.getTablePosition().getRow()),e.getNewValue()));
                     tablecontrat.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablecontrat.refresh();
         });
       
       /* txtprenom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtprenom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatePrenom(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
                     tablepersonnel.refresh();
         });
        
        txtadresse.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtadresse.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateAdresse(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setAdresse(e.getNewValue());
                     tablepersonnel.refresh();
         });
          
        txtmail.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtmail.setOnEditCommit((e) ->
                {
            if( p.updatemail(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setMail(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtsport.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtsport.setOnEditCommit((e) ->
                {
            if( p.upsport(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtrole.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtrole.setOnEditCommit((e) ->
                {
            if( p.updaterole(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
           txtcategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtcategorie.setOnEditCommit((e) ->
                {
            if( p.updatecategorie(tablepersonnel.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablepersonnel.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablepersonnel.refresh();
         });
         
       
        
         
        */
        
        tablecontrat.setEditable(true);
                    
                    
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
   String FILE_NAME = "C:\\Users\\aziz\\Desktop\\java.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Liste Contrat");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            Connection cnx =MyDB.getInstance().getConnexion();
            String query = "select * from contrat";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
                p3 = new Paragraph();
                
                
              
                p3.add(rs.getString("nom"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

 

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

  

    @FXML
    private void contpro(ActionEvent event) throws SQLException {
    
     ContratService c=new ContratService();
     
         Connection cnx =MyDB.getInstance().getConnexion();
    
        String x=c.totalpro();
     nb.setText(x);
    }

    @FXML
    private void contdebu(ActionEvent event) throws SQLException {
        ContratService c=new ContratService();
     
         Connection cnx =MyDB.getInstance().getConnexion();
    
        String x=c.totaldeb();
     nb1.setText(x);
    }

    @FXML
    private void contj(ActionEvent event) throws SQLException {
        ContratService c=new ContratService();
     
         Connection cnx =MyDB.getInstance().getConnexion();
    
        String x=c.totaljeun();
     nb2.setText(x);
    }
    
}
