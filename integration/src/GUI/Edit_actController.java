/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import entities.Actualite;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import static jdk.nashorn.tools.ShellFunctions.input;
import services.ActualiteService;
import sun.misc.BASE64Decoder;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Edit_actController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea tfDescription;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnacceuil;
public  int id_act;
    @FXML
    private Button btnparc;
    @FXML
    private ImageView imgact;
    /**
     * Initializes the controller class.
     */
    String img64,path="";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void EditActualite(ActionEvent event) throws IOException {
        if(verif()){
        String titre=tfTitre.getText();
    String description=tfDescription.getText();
        System.out.println(tfDate.getValue().toString());
    Date date_ajout =(Date) Date.valueOf( tfDate.getValue());
    Actualite a;
    if(path.equals("")==false)
    {
    a =new Actualite(titre,description,encodeb64(path),date_ajout,Userconnected.getId());
    }
    else a =new Actualite(titre,description,img64,date_ajout,Userconnected.getId());
    ActualiteService as= new ActualiteService();
    
    as.modifier(id_act, a);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Modification");
		alert.setHeaderText("Modifier Actualité");
		alert.setContentText("Modification effectuée avec succées");
		alert.showAndWait();
               
    FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherAct.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AfficherActController ap= loader.getController();
        btnModifier.getScene().setRoot(root);
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
    id_act=d;
    } 
    public void charger_act(){
        ActualiteService as= new ActualiteService();
        Actualite a =as.afficher_ById(id_act);
    tfTitre.setText(a.getTitre());
    tfDescription.setText(a.getDescription());
    LocalDate date = Date.valueOf(a.getDate_ajout().toString()).toLocalDate();
    // LocalDate date = a.getDate_ajout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
tfDate.setValue(date);
        img64=a.getImage();
Image img=SwingFXUtils.toFXImage(decodeToImage(a.getImage()), null);
   
imgact.setImage(img);
   
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
    private void parcourir(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.setTitle("choisir une image");
     FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("image file","*.png","*.jpg","*.jpeg");
   chooser.getExtensionFilters().add(filter);
    File file = chooser.showOpenDialog(btnparc.getScene().getWindow());
   if(file!=null){
       Image img= new Image(file.toURI().toString());
   path= file.getPath();
   imgact.setImage(img);
   
   }
    }
    boolean verif(){
          if (tfTitre.getText().length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Titre!");

		alert.showAndWait();
                return false;
          }
          else if (tfDescription.getText().length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Descrption!");

		alert.showAndWait();
                return false;
          }
          else if (tfDate.getValue()==null){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Champ vide");
		alert.setContentText("Veuillez remplir le champ Date d'Ajout!");

		alert.showAndWait();
                return false;
          }
          else if (img64.length()==0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Saisie invalide");
		alert.setHeaderText("Image vide");
		alert.setContentText("Veuillez ajoutez une Image!");

		alert.showAndWait();
                return false;
                
          }
           ActualiteService as= new ActualiteService();
           Date d =(Date) Date.valueOf( tfDate.getValue());
          if(as.existe_act(tfTitre.getText(), d,id_act)){
              Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Actualité existante");
		alert.setHeaderText("Existe");
		alert.setContentText("Cette actualité est déja insérée!");

		alert.showAndWait(); 
                return false;
          }
          return true;
          }
}
