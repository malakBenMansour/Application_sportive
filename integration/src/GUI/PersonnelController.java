/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import javafx.scene.image.Image;
import entities.Joueur;
import entities.Personnel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import static java.time.LocalDate.now;
import java.util.List;
import java.util.UUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.PersonnelService;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PersonnelController implements Initializable {

    @FXML
    private ComboBox<String> txtsport;
    @FXML
    private Button butajout;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtsalaire;
    @FXML
    private ComboBox<String> txtcategorie;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txttel;
ObservableList<String> list= FXCollections.observableArrayList("Football","Handball","BasketBall");
        ObservableList<String> listcat= FXCollections.observableArrayList("cadet","junior","senior","espoire");
        ObservableList<String> list_role= FXCollections.observableArrayList("joueur","entraineur","staff medical");
     private ExtensionFilter filter;
        @FXML
    private Button txtacceuil;
    @FXML
    private TextField txtcin;
    private File Current_file;
 
    private String file_image;
    /**
     * Initializes the controller class.
     */
       ValidationSupport validationSupport = new ValidationSupport();
    private FileChooser choisir;
    private TextField txtimage;
private String fcs;
private String fileName;
private File selectedFile;
private String path;
    @FXML
    private AnchorPane txtup;
    @FXML
    private ImageView photo;
    @FXML
    private Button logout;
    @FXML
    private void addpersonnel(ActionEvent event) throws SQLException, IOException {
        
          //int cin=Integer.valueOf(txtcin.getText());
        //String nom=txtnom.getText();
       // String prenom=txtprenom.getText(); 
       // String adresse=txtadresse.getText();
       // String mail=txtmail.getText();
        //Date.getValue(txtdate);
        
       // Date d = Date.valueOf(txtdate.getValue());
        //int tel=Integer.valueOf(txttel.getText());
     //  float salaire=Float.valueOf(txtsalaire.getText());
       // java.util.Date date=new java.util.Date();
       // java.sql.Date sqldate =new Date(date.getTime());
        ObservableList<String> list=txtsport.getItems();
        ObservableList<String> listcat=txtcategorie.getItems();
        ObservableList<String> list_role=txtrole.getItems();
       file_image = "src/gui/img/" + file_image;
        long millis=System.currentTimeMillis();  
      
    
    java.sql.Date date = new java.sql.Date(millis); 
//        System.out.println(Date.valueOf(txtdate.getValue()));
       if ( txtnom.getText().isEmpty() || txtnom.getText().matches("[0-9]") || txtcin.getText().length()!=8 || txtcin.getText().isEmpty() || txtcin.getText().matches("[a-z]")||Date.valueOf(txtdate.getValue()).after(date) || Date.valueOf(txtdate.getValue()).equals(date) || txtprenom.getText().isEmpty() || txtprenom.getText().matches("[0-9]") 
          ||  txttel.getText().length()!=8  || txttel.getText().isEmpty() || txttel.getText().matches("[a-z]") || txtsalaire.getText().isEmpty() || txtsalaire.getText().matches("[a-z]") ||  txtmail.getText().isEmpty() || txtmail.getText().matches("[0-9]") ||  txtadresse.getText().isEmpty() || txtadresse.getText().matches("[0-9]") ){
         
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("VERIFIER CHAMP").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
       }
       else{
             Personnel p= new Personnel(Integer.valueOf(txtcin.getText()),txtnom.getText(),txtprenom.getText(),Date.valueOf(txtdate.getValue()),txtadresse.getText(),txtmail.getText(),Integer.valueOf(txttel.getText()),Float.valueOf(txtsalaire.getText()),file_image,txtsport.getSelectionModel().getSelectedItem(),txtcategorie.getSelectionModel().getSelectedItem(),txtrole.getSelectionModel().getSelectedItem(),Userconnected.getId());
        PersonnelService ps=new PersonnelService();
        ps.ajouterp(p);
         Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Personnel Ajoute ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherPerso.fxml"));
        Parent root= loader.load();
        AfficherPersoController a=loader.getController();
        
        butajout.getScene().setRoot(root);
       }
       
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        txtsport.setItems(list);  
        txtcategorie.setItems(listcat);
        txtrole.setItems(list_role);
        // TODO
        
       validationSupport.registerValidator(txtnom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtprenom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtmail, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtadresse, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtcin, Validator.createEmptyValidator("text is required"));
          validationSupport.registerValidator(txtdate, Validator.createEmptyValidator("Date Selection Required"));
           validationSupport.registerValidator(txttel, Validator.createEmptyValidator("text is Required"));
           validationSupport.registerValidator(txtsalaire, Validator.createEmptyValidator("text is Required"));
    } 

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
        Parent root = loader.load();
        AcceuilResponsableController a= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }

    private void upp(ActionEvent event) throws IOException {
       /* FileChooser chooser=new FileChooser();
       chooser.showOpenDialog(null);
       chooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
       File SelectedFile = chooser.showOpenDialog(null);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));*/
       choisir = new FileChooser();
       // choisir.setInitialDirectory(new File(System.getProperty("c:\\user") + "\\" ));
       
       filter =new  ExtensionFilter (" Image :",".jpg",".png");
        choisir.setSelectedExtensionFilter(filter);
        File file = choisir.showOpenDialog(null); 
       if (file != null) {
            /* fcs = file.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
                //System.out.println(fileName);*/
            fcs = file.getAbsolutePath();
        File source = new File(fcs);
        //File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" + key + fileName);
        //String url = "/image/" + key + fileName;
        txtimage.setText(fcs);
            }
          //  String key = UUID.randomUUID().toString();

       
    }

    @FXML
    private void pho(DragEvent event) {
    Dragboard board = event.getDragboard();
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);}
    }

    @FXML
    private void phd(DragEvent event) throws FileNotFoundException {
        Dragboard board = event.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
        fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
        File selectedFile = phil.get(0);
        if (selectedFile != null) {

            String test = selectedFile.getAbsolutePath();
            System.out.println(test);

            Current_file = selectedFile.getAbsoluteFile();
            file_image = Current_file.getName();
            Personnel e = new Personnel();
            e.setImage(selectedFile.getName());
            photo.setImage(image);
        }
        
    }

    @FXML
    private void out(ActionEvent event) throws IOException {
        Userconnected.setId(0);
                  Userconnected.setNom("");
                  Userconnected.setPrenom("");              
                  //Userconnected.setDatenaissance(d);                      
                  Userconnected.setAdresse("");                              
                  Userconnected.setMail("");                                    
                  Userconnected.setTel(0);
                  Userconnected.setRole("");
                  Userconnected.setMdp("");
                  Userconnected.setNomEquipe("");
                  Userconnected.setEtat("");
       
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController ap= loader.getController();
       
        logout.getScene().setRoot(root);
    }

  
       
       
    }

