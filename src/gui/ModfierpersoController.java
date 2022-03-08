/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ModfierpersoController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txttel;
    @FXML
    private TextField txtsalaire;
    @FXML
    private ComboBox<String> txtsport;
    @FXML
    private DatePicker txtdate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txtmail;
    @FXML
    private ComboBox<String> txtcategorie;
 ObservableList<String> list=txtsport.getItems();
        ObservableList<String> listcat=txtcategorie.getItems();
        ObservableList<String> list_role=txtrole.getItems();
        String query=null;
        private int idp;
        private Boolean update;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        txtsport.setItems(list);  
        txtcategorie.setItems(listcat);
        txtrole.setItems(list_role);
      
         // TODO
    }    

    @FXML
    private void modif(ActionEvent event) throws SQLException {
        query = "UPDATE `personne` SET "
                    + "`nom`=?,"
                    + "`prenom`=?,"
                    + "`datenaissance`=?,"
                    + "`adresse`=?,"
                    + "`mail`=?,"
                    + "`tel`=?,"
                    + "`salaire`=?,"
               
                
                
                + "`sport`=?,"
                    + "`categorie`=?,"
                    + "`role`= ? WHERE id = '"+idp+"'";
        
    insert();
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
     public void setTextField(int id,String nom,String prenom,Date d,String adresse, String mail,int tel,String role,Float salaire,String categorie,String sport )
   {   idp=id;
       txtnom.setText(nom);
       txtprenom.setText(prenom);
       txtdate.setValue(d.toLocalDate());
       txtadresse.setText(adresse);
      
       txtmail.setText(mail);
       txtrole.setValue(role);
       //txtsalaire.setValue(salaire);
       txtsport.setValue(sport);
       txtcategorie.setValue(categorie);
       
   }
   
   private void insert() throws SQLException {

       Connection cnx =MyDB.getInstance().getConnexion(); 
    
          PreparedStatement ste= cnx.prepareStatement(query);
          
          ste.setString(1,txtnom.getText());
          ste.setString(2,txtprenom.getText());
          ste.setString(3,String.valueOf(txtdate.getValue()));
          ste.setString(4,txtadresse.getText());
          ste.setString(5,txtmail.getText());
          ste.setString(6,txttel.getText());
         ste.setFloat(7,Float.valueOf(txtsalaire.getText()));
          ste.setString(8,String.valueOf(txtsport.getValue()));
          ste.setString(9,String.valueOf(txtcategorie.getValue()));
          ste.setString(10,String.valueOf(txtrole.getValue()));
         // ste.setString(9,txtnomEquipe.getText());
          //ste.setString(10,String.valueOf(txtetat.getValue()));
   }
    @FXML
    private void retourAcceuil(ActionEvent event) {
        
    }
    
}
