/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import entities.Personne;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class NewUserController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txttel;
    @FXML
    private TextField txtmdp;
    @FXML
    private TextField txtnomEquipe;
    @FXML
    private ComboBox<String> txtetat;
    @FXML
    private DatePicker txtdatenaissance;
    @FXML
    private Button txtajouter;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txtmail;
     ObservableList<String> list= FXCollections.observableArrayList("admin","respo","fan");
    ObservableList<String> list_etat= FXCollections.observableArrayList("active","desactive");
    @FXML
    private TextField txtfile;
    @FXML
    private Button btnimage;
     private String fcs;
    private String fileName;
    private File selectedFile;
    private String path;
    @FXML
    private ImageView recaptchaCheckMark;

    int etatrecaptcha = 0;
    Stage window;
    WebView webView2;
    WebEngine webEngine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         txtrole.setItems(list); 
          txtetat.setItems(list_etat);
          /////
          
          window = new Stage();
        webView2 = new WebView();
        webEngine = webView2.getEngine();
        window.setOnCloseRequest(e -> {
            if (webEngine != null && webEngine.getTitle().contains("success")) {
                etatrecaptcha = 1;
               // recaptchaCheckMark.setImage(new Image("/gui/img/checkMark.png"));
                File file = new File("/gui/img/checkMark.png");
        Image image = new Image(file.toURI().toString());
        recaptchaCheckMark.setImage(image);
            }
            System.out.println("etat recaptcha=" + etatrecaptcha);
        });
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
    }    

    private Boolean testSaisieMembre() {
        String erreur = "";

        
        if (etatrecaptcha == 0) {
            erreur = erreur + ("Veuillez valider la recaptcha\n");
            recaptchaCheckMark.setImage(new Image("/gui/img/notifications.png"));
        }

        if ( etatrecaptcha == 0) {
           Notifications   n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return  etatrecaptcha == 1;
    }
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
      /*   String nom=txtnom.getText();
      String prenom=txtprenom.getText();
        Date d = Date.valueOf(txtdatenaissance.getValue());
        String adresse=txtadresse.getText(); 
        String mail=txtmail.getText();
     int tel=Integer.valueOf(txttel.getText());
     String mdp=txtmdp.getText();
    */
       long millis=System.currentTimeMillis();  
      
    
    java.sql.Date date = new java.sql.Date(millis);
     String nomEquipe=txtnomEquipe.getText();
     ObservableList<String> list=txtrole.getItems();
     ObservableList<String> list_etat=txtetat.getItems(); 
     // String image=txtfile.getText();
        if ( txtnom.getText().isEmpty() || txtnom.getText().matches("[0-9]") || txttel.getText().length()!=8 || txttel.getText().isEmpty() || txttel.getText().matches("[a-z]") || txtprenom.getText().isEmpty() || txtprenom.getText().matches("[0-9]") 
          || txtmdp.getText().isEmpty() ||  txtmail.getText().isEmpty()  ||  txtadresse.getText().isEmpty() || txtadresse.getText().matches("[0-9]") ||  txtfile.getText().isEmpty() ||Date.valueOf(txtdatenaissance.getValue()).after(date)  ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ecrits").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
       
      
       else {
           //Personne p=new Personne(nom,prenom,d,adresse,mail,tel,txtrole.getSelectionModel().getSelectedItem(),mdp,nomEquipe,txtetat.getSelectionModel().getSelectedItem(),image);
     Personne p=new Personne(txtnom.getText(),txtprenom.getText(),Date.valueOf(txtdatenaissance.getValue()),txtadresse.getText(),txtmail.getText(),Integer.valueOf(txttel.getText()),txtrole.getSelectionModel().getSelectedItem(),txtmdp.getText(),nomEquipe,txtetat.getSelectionModel().getSelectedItem(),txtfile.getText());
     
           PersonneService sp=new PersonneService();
      
      
      if(testSaisieMembre()){
      sp.ajouter(p);
      /*
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur ajouté avec Sucees!");
            alert.show();*/
      Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Utilisateur ajouuté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
     
    }}}

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
   LoginController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }

    @FXML
    private void image(ActionEvent event) {
        FileChooser chooser=new FileChooser();
       chooser.showOpenDialog(null);
       chooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\"));
       File SelectedFile = chooser.showOpenDialog(null);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
         if (SelectedFile != null) {
             fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
                //System.out.println(fileName);
            }
            String key = UUID.randomUUID().toString();

        fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" + key + fileName);
        String url = "/image/" + key + fileName;
        txtfile.setText(url);
         }  
         
         
         
         
         
    }

    @FXML
    private void recaptcha(MouseEvent event) {
        webView2.setPrefSize(400, 500);
        webEngine.setUserAgent("use required / intended UA string");
        webEngine.load("https://patrickhlauke.github.io/recaptcha/");

        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> window.close());
        etatrecaptcha=1;

        VBox layout = new VBox(10);
        layout.getChildren().addAll(webView2);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
