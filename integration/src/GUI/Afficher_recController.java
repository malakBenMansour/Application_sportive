/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Actualite;
import entities.Reclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import services.ActualiteService;
import services.ReclamationService;
import tools.MyDB;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Afficher_recController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnnbj;
    private TextField nbact;
    
    @FXML
    private TableColumn<Reclamation, String> col_titre;
    @FXML
    private TableColumn<Reclamation, String> col_desc;
    @FXML
    private TableColumn<Reclamation, Date> col_dat;
    @FXML
    private TableColumn<Reclamation, Integer> col_id;
    @FXML
    private TableColumn<Reclamation, String> col_cat;
    @FXML
    private TableColumn<Reclamation, String> col_etat;
    @FXML
    private TableColumn<Reclamation, String> col_per;

    @FXML
    private TableColumn<Reclamation, String> col_com;
    @FXML
    private TableView<Reclamation> tbrec;
    @FXML
    private TextField nbrec;
     String col,ord;
ReclamationService rs= new ReclamationService();
    @FXML
    private ComboBox<String> comborec;
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private RadioButton asc;
    @FXML
    private RadioButton desc;
    @FXML
    private ToggleGroup ordre;
    @FXML
    private Button btnexcel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comborec.getItems().removeAll(comborec.getItems());
     comborec.getItems().addAll("Titre", "etat");
    comborec.getSelectionModel().select("Titre");
    col="titre";
    ord="ASC"; 
    txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        research();
            
            }
            });
            comborec.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (comborec.getSelectionModel().getSelectedItem().equals("Titre"))
            col="titre";
        else if   (comborec.getSelectionModel().getSelectedItem().equals("etat"))
            col="etat" ;
research();

            });

tbrec.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
               
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Edit_rec.fxml"));
       
       Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(Afficher_recController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        Edit_recController rec= loader.getController();
        
 rec.setdata(tbrec.getSelectionModel().getSelectedItem().getId());
rec.charger_rec();
     btnajout.getScene().setRoot(root);       
             
            }
            }         
        
        });
affiche_tab();
            }
        //@Override
        //public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //}
       

      @FXML
    private void showchart(ActionEvent event){
        try {
            List<Reclamation> le=rs.afficher_rec();
           
    ObservableList<Reclamation> data =FXCollections.observableArrayList(le);
     
            FXMLLoader chart= new FXMLLoader(getClass().getResource("chart.fxml"));
            Parent root = chart.load();
            ChartController mc = chart.getController();
           
           
            Scene scene = new Scene(root);
           Stage modifStage = new Stage();
            
            modifStage.setTitle("Nombre de Réclamations / Catégories");
            modifStage.setScene(scene);
            modifStage.show();
          
             ChartController controller = chart.getController();
          System.out.println(le.size());
             controller.setReclamationData(data,le.size());
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Afficher_recController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_rec.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_recController ap= loader.getController();
        btnajout.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
    try{
      rs.supprimer(tbrec.getSelectionModel().getSelectedItem().getId()); 
      affiche_tab();
       Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Réclamation Supprimée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Supression");
		alert.setHeaderText("Supprimer réclamation");
		alert.setContentText("Supression effectuée avec succès!");

		alert.showAndWait();
        }
        catch (Exception ex) {
                    
                }
    }

 
    @FXML
    private void retourHome(ActionEvent event) throws IOException {
   FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController ap= loader.getController();
        btnretour.getScene().setRoot(root);
    }


    @FXML
    private void nbrjoueur(ActionEvent event) {
    }
    private void affiche_tab() {
    
    List<Reclamation> le=rs.afficher_rec();
        ObservableList<Reclamation> datalist = FXCollections.observableArrayList(le);
         col_per.setCellValueFactory(new PropertyValueFactory<>("idper"));
        col_com.setCellValueFactory(new PropertyValueFactory<>("num_commande"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_dat.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       
        
        tbrec.setItems(datalist);
      nbrec.setText(String.valueOf(le.size()));
        tbrec.refresh();
        
         
    
     
    }
     public void research()
{
    try{

  ReclamationService as = new ReclamationService ();
        List<Reclamation> la = as.afficher_rec_critere(txtrecherche.getText(),col,ord);
     tbrec.getItems().clear();
      ObservableList<Reclamation> datalist = FXCollections.observableArrayList(la);
      col_per.setCellValueFactory(new PropertyValueFactory<>("idper"));
        col_com.setCellValueFactory(new PropertyValueFactory<>("num_commande"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_dat.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    tbrec.setItems(datalist); 
    
    nbact.setText(String.valueOf(la.size()));
    }
    catch(Exception ex){}
}

    @FXML
    private void ordreact(ActionEvent event) {
    if (asc.isSelected())
            ord="ASC";
        else if    (desc.isSelected())
            ord="DESC";
        
        research();
    }

    @FXML
    private void exportExcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        Connection cnx = MyDB.getInstance().getConnexion();
        String query = "Select * from Reclamation";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails Réclamations");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Titre");
            header.createCell(1).setCellValue("Description");
            header.createCell(2).setCellValue("Dateajout");
            header.createCell(3).setCellValue("Id_cat");
             header.createCell(4).setCellValue("Etat");
              header.createCell(5).setCellValue("Num_commande");
               header.createCell(6).setCellValue("Id_per");
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("titre"));
                row.createCell(1).setCellValue(rs.getString("description"));
                row.createCell(2).setCellValue(rs.getDate("dateajout"));
                row.createCell(3).setCellValue(rs.getInt("id_cat"));
                row.createCell(4).setCellValue(rs.getString("etat"));
                row.createCell(5).setCellValue(rs.getString("num_commande"));
                row.createCell(6).setCellValue(rs.getInt("id_per"));
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails Réclamations.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            File myFile = new File("C:\\Users\\malak_6\\Desktop\\integration\\Détails Réclamations.xlsx");
             Desktop.getDesktop().open(myFile);
    }

}

