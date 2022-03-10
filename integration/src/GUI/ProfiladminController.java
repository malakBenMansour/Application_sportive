/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import entities.Personne;
import static GUI.main.Userconnected;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ProfiladminController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label lb_nom;
    @FXML
    private Label lb_prenom;
    @FXML
    private Label lb_adresse;
    @FXML
    private Label lb_datenaissance;
    @FXML
    private Label lb_tel;
    @FXML
    private Label lb_mail;
    @FXML
    private Label lb_id;
    @FXML
    private TextField txtrech;
    @FXML
    private Button btnrech;

    Connection cnx =MaConnexion.getInstance().getCnx();
    @FXML
    private Label lb_role;
    @FXML
    private Rating userRating;
    @FXML
    private Button btnsubmit;
    @FXML
    private Button btnacceuil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setlb_nom(String email) {
        this.lb_nom.setText(email);
    }
        public void setLb_prenom(String email) {
        this.lb_prenom.setText(email);
    }
       public void setLb_role(String email) {
        this.lb_role.setText(email);
    }  
        public void setLb_mail(String email) {
        this.lb_mail.setText(email);
    }public void setLb_date(Date d) {
        this.lb_datenaissance.setText(d.toString());
    }public void setLb_tel(int email) {
        this.lb_tel.setText(Integer.toString(email));
    }
         public void setLb_adresse(String email) {
        this.lb_adresse.setText(email);
    }
    
    public void setLb_id(int email) {
        this.lb_id.setText(Integer.toString(email));
    }
    @FXML
    private void recherche(ActionEvent event) throws SQLException {
            String nom=txtrech.getText();
       
        PersonneService p=new PersonneService();
      List <Personne> l=  p.rechercherParNom(nom);
        String req="SELECT * FROM `personne` WHERE nom='"+ nom+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
               
                
       setlb_nom(rs.getString("nom"));
        setLb_prenom(rs.getString("prenom"));
        setLb_adresse(rs.getString("adresse"));
        setLb_tel(rs.getInt("tel"));
        setLb_role(rs.getString("role"));
        setLb_mail(rs.getString("mail"));
        setLb_date(rs.getDate("datenaissance"));
        setLb_id(rs.getInt("id"));
         File file = new File(rs.getString("image"));
         Image image = new Image(file.toURI().toString());
        img.setImage(image);
     }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}

    @FXML
    private void submit(ActionEvent event) {
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Evalution de cet Utilisateur"+userRating.getRating()).graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFXML.fxml"));
        Parent root = loader.load();
        DocFXMLController ap= loader.getController();
        
        btnacceuil.getScene().setRoot(root);  
    }
}
