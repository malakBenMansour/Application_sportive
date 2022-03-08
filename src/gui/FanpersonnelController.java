/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.PersonnelService;
import java.util.List;
import entities.Personnel;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.image.Image;
import utils.MyDB;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class FanpersonnelController implements Initializable {

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
    private TextField txtrech;
    @FXML
    private Button btnrech;
    @FXML
    private Label lb_role;
    @FXML
    private Button btnacceuil;
  Connection cnx =MyDB.getInstance().getConnexion() ;
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
        
    public void setLb_date(Date d) {
        this.lb_datenaissance.setText(d.toString());
    }public void setLb_tel(String email) {
        this.lb_tel.setText(email);
    }
         public void setLb_adresse(String email) {
        this.lb_adresse.setText(email);
    }
    
   
    @FXML
    private void recherche(ActionEvent event) throws SQLException {
          String nom=txtrech.getText();
       
        PersonnelService p=new PersonnelService();
      List <Personnel> l=  p.rechercheParNom(nom);
        String req="SELECT * FROM `personnel` WHERE nom='"+ nom+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
               
                
       setlb_nom(rs.getString("nom"));
        setLb_prenom(rs.getString("prenom"));
        setLb_adresse(rs.getString("categorie"));
        setLb_tel(rs.getString("sport"));
        setLb_role(rs.getString("role"));
      
        setLb_date(rs.getDate("datenaissance"));
       
         File file = new File(rs.getString("image"));
         Image image = new Image(file.toURI().toString());
        img.setImage(image);
     }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }


    @FXML
    private void acceuil(ActionEvent event) {
    }
    
}
