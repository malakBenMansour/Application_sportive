/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Magasin;
import entities.Produit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ServiceMagasin;
import services.ServiceProduit;
import sun.misc.BASE64Decoder;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Edit_prodController implements Initializable {

    @FXML
    private TextField txtnomp;
    @FXML
    private Button btnacceuil;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<String> combo_cat;
    @FXML
    private ComboBox<String> combo_mag;
 int idprod;
  String pathimg="";
  
    @FXML
    private ImageView imgpdt;
    @FXML
    private Button btnimg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_cat.getItems().removeAll(combo_cat.getItems());
    combo_cat.getItems().addAll("Football", "Basketball", "Handball");
    combo_cat.getSelectionModel().select("Football");
    lister_nommag();
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        if (validation()==true)
    {
         Produit p ;
         ServiceProduit sp= new ServiceProduit();
          ServiceMagasin sm= new ServiceMagasin();
         
         String nom_p = txtnomp.getText();
        float prix =Float.parseFloat(txtprix.getText()) ;
         String categorie = combo_cat.getSelectionModel().getSelectedItem();
         int idm = sm.return_idm(combo_mag.getSelectionModel().getSelectedItem());
 
         p=new Produit (nom_p,prix,categorie,idm,encodeb64(pathimg),Userconnected.getId());
         sp.modifier(idprod,p);
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
          FXMLLoader loader= new FXMLLoader(getClass().getResource("Afficher_prod.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Afficher_prodController ap= loader.getController();
        btnmodifier.getScene().setRoot(root);

    }
    }

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }
     public void setdata(int d){
    idprod=d;
    } 
    public void charger_prod(){
       
    ServiceProduit ap= new ServiceProduit();
     ServiceMagasin am= new ServiceMagasin();
        List<Produit> a =ap.afficherById(idprod);
       
        txtnomp.setText(a.get(0).getNom_p());
   
      txtprix.setText(String.valueOf(a.get(0).getPrix()));
      combo_cat.setValue(a.get(0).getCategorie());
      combo_mag.setValue(a.get(0).getMagasin());
      try{
      pathimg=a.get(0).getImage();
      Image img=SwingFXUtils.toFXImage(decodeToImage(a.get(0).getImage()), null);
   
        imgpdt.setImage(img);
      }
      catch (Exception ex){System.out.println(ex.getMessage());}
    
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
         
        if (txtnomp.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("validation");
		alert.setHeaderText("champ nom prod vide");
		alert.setContentText("veuillez saisir le nom du produit!");

		alert.showAndWait();
                return false ;
        }
         else if (txtprix.getText().length()==0)
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
           else if (sp.existe_pdt(txtnomp.getText(), sm.return_idm(combo_mag.getSelectionModel().getSelectedItem()), idprod)){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("produit existant ");
		alert.setContentText("Veuillez choisir un autre produit different!");

		alert.showAndWait();
                return false;
                
          }
          return true;
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
    
public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
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
}
