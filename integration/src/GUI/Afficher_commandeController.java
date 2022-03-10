/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entities.LigneCommande;
import entities.Magasin;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import services.ServiceCommande;
import services.ServiceLigneCommande;
import services.ServiceMagasin;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Afficher_commandeController implements Initializable {

    @FXML
    private Button btnsupprimer;
    @FXML
    private ImageView img_supp;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnretour;
    @FXML
    private TextField nbmag;
   @FXML
    private TableView<Commande> tablecmd;
    @FXML
    private TableColumn<Commande, Integer> colidc;
    @FXML
    private TableColumn<Commande, Date> coldate;
    @FXML
    private TableColumn<Commande, String> colad;
    @FXML
    private TableColumn<Commande, Float> coltotal;
    @FXML
    private TableColumn<Commande, Integer> colidpers;
    @FXML
    private TableColumn<Commande, String> colnompers;
    @FXML
    private TableColumn<LigneCommande, Integer> colidc_ligne;
    @FXML
    private TableColumn<LigneCommande, Integer> colidp_ligne;
    @FXML
    private TableColumn<LigneCommande, String> colnomp_ligne;
    @FXML
    private TableColumn<LigneCommande,Integer > colqte_ligne;
    @FXML
    private RadioButton ASC;
    @FXML
    private ToggleGroup ordre;
    @FXML
    private RadioButton DESC;
    @FXML
    private TableView<LigneCommande> tablelignecmd;
    
     String col,ord;
    @FXML
    private ComboBox<String> combocmd;
    @FXML
    private Button btnacc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        combocmd.getItems().removeAll(combocmd.getItems());
    combocmd.getItems().addAll("Date", "Adresse", "Nom Personne");
    combocmd.getSelectionModel().select("Date");
       col="date";
       ord="ASC";  
       txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               research();
            }

           
        });



combocmd.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (combocmd.getSelectionModel().getSelectedItem().equals("Date"))
            col="date";
        else if   (combocmd.getSelectionModel().getSelectedItem().equals("Adresse"))
            col="adresse" ;
        else  if (combocmd.getSelectionModel().getSelectedItem().equals("Nom Personne"))
             col="concat(personne.nom,' ',personne.prenom)";
        research();
});  
        
     lister_cmd();
      tablecmd.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
      
      lister_Lignecmd(tablecmd.getSelectionModel().getSelectedItem().getIdc());
                
                
            }
            }         
        });
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande ();
        try {
         
            sc.supprimer(tablecmd.getSelectionModel().getSelectedItem().getIdc());
             lister_cmd();
            
        } catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void retourHome(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnretour.getScene().setRoot(root);
         
    }

    @FXML
    private void retourHome(MouseEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnretour.getScene().setRoot(root);
         
    }

    @FXML
    private void trimag(ActionEvent event) {
    }

    @FXML
    private void ordremag(ActionEvent event) {
         if (ASC.isSelected())
            ord="ASC";
        else if    (DESC.isSelected())
            ord="DESC";
        
        research();
    }
     public void lister_cmd ()
    {
    ServiceCommande sc = new ServiceCommande();
     List<Commande> lc = sc.afficher();
     tablecmd.getItems().clear();
      ObservableList<Commande> datalist = FXCollections.observableArrayList(lc);
        colidc.setCellValueFactory(new PropertyValueFactory<>("idc"));
          coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colnompers.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colidpers.setCellValueFactory(new PropertyValueFactory<>("idpers"));
        
    tablecmd.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lc.size()));
    }
     
      public void lister_Lignecmd (int idc_cmd)
    {
    ServiceLigneCommande slc = new ServiceLigneCommande();
     List<LigneCommande> llc = slc.afficherpdt_cmd(idc_cmd);
     tablelignecmd.getItems().clear();
      ObservableList<LigneCommande> datalist = FXCollections.observableArrayList(llc);
        colidc_ligne.setCellValueFactory(new PropertyValueFactory<>("idc"));
          colidp_ligne.setCellValueFactory(new PropertyValueFactory<>("idp"));
        colnomp_ligne.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colqte_ligne.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       
        
    tablelignecmd.setItems(datalist); 
    
    
    }
      
      
       public void research()
{
  ServiceCommande sc = new ServiceCommande();
     List<Commande> lc= sc.afficher_critere(txtrecherche.getText(), col, ord);
     tablecmd.getItems().clear();
      ObservableList<Commande> datalist = FXCollections.observableArrayList(lc);
        colidc.setCellValueFactory(new PropertyValueFactory<>("idc"));
          coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colnompers.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colidpers.setCellValueFactory(new PropertyValueFactory<>("idpers"));
        
    tablecmd.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lc.size()));
}

    @FXML
    private void acc(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController1 ap= loader.getController();
        btnacc.getScene().setRoot(root);
    }
     
     
}
