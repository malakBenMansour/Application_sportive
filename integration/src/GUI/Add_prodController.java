/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Magasin;
import entities.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import services.ServiceMagasin;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Add_prodController implements Initializable {

    @FXML
    private TextField txtnomprod;
    @FXML
    private TextField txtprix_p;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnacceuil;
  
    @FXML
    private ComboBox<String> combo_mag;
    @FXML
    private ComboBox<String> combo_cat;
    @FXML
    private ImageView imgpdt;
    @FXML
    private Button btnimg;
     String pathimg="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         combo_cat.getItems().removeAll(combo_cat.getItems());
    combo_cat.getItems().addAll("Football", "Basketball", "Handball");
    combo_cat.getSelectionModel().select("");
    lister_nommag();
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         if (validation ()==true){
         Produit p ;
         ServiceProduit sp= new ServiceProduit();
          ServiceMagasin sm= new ServiceMagasin();
         
         String nom_p = txtnomprod.getText();
        float prix =Float.parseFloat(txtprix_p.getText()) ;
         String categorie = combo_cat.getSelectionModel().getSelectedItem();
         int idm = sm.return_idm(combo_mag.getSelectionModel().getSelectedItem());
 
         p=new Produit (nom_p,prix,categorie,idm,encodeb64(pathimg),Userconnected.getId());
         sp.ajouterpst(p);
           Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Magsin Ajoute ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
         //copy image produit
         
        
          FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_prod.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_prodController ap= loader.getController();
        btnajouter.getScene().setRoot(root);
    }
    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException  {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
       
    }
     public void lister_nommag ()
    {
    ServiceMagasin sm = new ServiceMagasin();
     List<String> lm = sm.afficher_nommag();
    combo_mag.getItems().clear();
      ObservableList<String> datalist = FXCollections.observableArrayList(lm);
        
    combo_mag.setItems(datalist); 
    
   
    }
      
      boolean validation (){
          ServiceProduit sp= new ServiceProduit();
          ServiceMagasin sm= new ServiceMagasin();
         
        if (txtnomprod.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nom prod vide");
		alert.setContentText("veuillez saisir le nom du produit!");

		alert.showAndWait();
                return false ;
        }
         else if (txtprix_p.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ prix du produit vide");
		alert.setContentText("veuillez saisir le prix du produit!");

		alert.showAndWait();
                return false ;
        }
        else if (combo_cat.getSelectionModel().getSelectedIndex()==-1){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Catégorie vide");
		alert.setContentText("Veuillez choisir une catégorie!");

		alert.showAndWait();
                return false;
                
          }
          else if (combo_mag.getSelectionModel().getSelectedIndex()==-1){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Magasin vide");
		alert.setContentText("Veuillez choisir une Magasin!");

		alert.showAndWait();
                return false;
                
          }
           else if (sp.existe_pdt(txtnomprod.getText(), sm.return_idm(combo_mag.getSelectionModel().getSelectedItem()), 0)){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("produit existe");
		alert.setContentText("Veuillez choisir un autre produit different!");

		alert.showAndWait();
                return false;
                
          }
          return true;
          }

    @FXML
    private void choisirimg(ActionEvent event) {
         FileChooser chooser = new FileChooser();
    chooser.setTitle("choisir une image");
     FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("image file","*.png","*.jpg","*.jpeg");
   chooser.getExtensionFilters().add(filter);
    File file = chooser.showOpenDialog(btnimg.getScene().getWindow());
   if(file!=null){
       Image img= new Image(file.toURI().toString());
   pathimg= file.getPath();
       System.out.println(pathimg);
   imgpdt.setImage(img);
   
    }
     
}
    
    private String encodeb64(String path) throws FileNotFoundException, IOException
    {
    File file = new File(path);
byte[] bytes = new byte[(int)file.length()];
FileInputStream fis = new FileInputStream(file);
fis.read(bytes); 
fis.close();
String ef = Base64.getEncoder().encodeToString(bytes);
return ef;
    }
    
    
    
    
}//class