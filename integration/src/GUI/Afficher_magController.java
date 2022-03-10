/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import entities.Magasin;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.ServiceMagasin;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Commande;
import entities.LigneCommande;
import java.awt.Desktop;
import java.io.File;
import java.sql.Date;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import tools.Myconnexion;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Afficher_magController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView btnacceuil;
    @FXML
    private TableView<Magasin> tablemag;
    @FXML
    private TableColumn<Magasin, Integer> txtid;
    @FXML
    private TableColumn<Magasin, String> txtnom;
    @FXML
    private TableColumn<Magasin, String> txtadresse;
    @FXML
    private TableColumn<Magasin, Integer> txtnbb;
    @FXML
    private ImageView img_supp;
    @FXML
    private TextField nbmag;
    @FXML
    private ComboBox<String> combomag;
    @FXML
    private RadioButton ASC;
    @FXML
    private RadioButton DESC;
    @FXML
    private ToggleGroup ordre;
     
    String col,ord ;
    @FXML
    private ImageView btnsms;
    @FXML
    private Button btnacc;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        combomag.getItems().removeAll(combomag.getItems());
    combomag.getItems().addAll("Nom", "Adresse", "Nombre Bloc");
    combomag.getSelectionModel().select("Nom");
    col="nom";
    ord="ASC";        
        lister_mag();
        
 img_supp.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                 ServiceMagasin sm = new ServiceMagasin ();
        try {
            System.out.println(tablemag.getSelectionModel().getSelectedItem().getIdm());
            sm.supprimer(tablemag.getSelectionModel().getSelectedItem().getIdm());
            lister_mag ();
            
        } catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
            }
         });
        tablemag.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
               
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Edit_mag.fxml"));
       
       Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(Afficher_magController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        Edit_magController act= loader.getController();
        
        act.setdata(tablemag.getSelectionModel().getSelectedItem().getIdm());
       act.charger_act();
       btnajout.getScene().setRoot(root);       
                
                
                
            }
            }         
        });

txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               research();
            }
        });



combomag.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (combomag.getSelectionModel().getSelectedItem().equals("Nom"))
            col="nom";
        else if   (combomag.getSelectionModel().getSelectedItem().equals("Adresse"))
            col="adresse" ;
        else  if (combomag.getSelectionModel().getSelectedItem().equals("Nombre Bloc"))
             col="nombrebloc";
        research();
}); 
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_mag.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_magController ap= loader.getController();
        btnajout.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceMagasin sm = new ServiceMagasin ();
        try {
            System.out.println(tablemag.getSelectionModel().getSelectedItem().getIdm());
            sm.supprimer(tablemag.getSelectionModel().getSelectedItem().getIdm());
              Notifications notificationBuilder=Notifications.create()
              .title("Succee").text("Magsin Supprimé ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
            lister_mag ();
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Suppression");
		alert.setHeaderText("Supprimer");
		alert.setContentText("Réussite de la suppression");

		alert.showAndWait();
            
        } catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
        
    }

   
    @FXML
    private void retourHome(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController1 ap= loader.getController();
        btnacceuil.getScene().setRoot(root);

    }

    @FXML
    private void retourHome(MouseEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController1 ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }


    public void lister_mag ()
    {
    ServiceMagasin sm = new ServiceMagasin();
     List<Magasin> lm = sm.afficher();
     tablemag.getItems().clear();
      ObservableList<Magasin> datalist = FXCollections.observableArrayList(lm);
        txtid.setCellValueFactory(new PropertyValueFactory<>("idm"));
          txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        txtnbb.setCellValueFactory(new PropertyValueFactory<>("nombrebloc"));
    tablemag.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lm.size()));
    }

    @FXML
    private void trimag(ActionEvent event) {
        if (combomag.getSelectionModel().equals("Nom"))
            col="nom";
        else if   (combomag.getSelectionModel().equals("Adresse"))
            col="adresse" ;
        else  if (combomag.getSelectionModel().equals("Nombre Bloc"))
            col="nombrebloc";
        research();
        

        

    }

    @FXML
    private void ordremag(ActionEvent event) {
         if (ASC.isSelected())
            ord="ASC";
        else if    (DESC.isSelected())
            ord="DESC";
        
        research();
    }
public void research()
{
  ServiceMagasin sm = new ServiceMagasin ();
        List<Magasin> lm = sm.recherchermutli(txtrecherche.getText(),col,ord);
     tablemag.getItems().clear();
      ObservableList<Magasin> datalist = FXCollections.observableArrayList(lm);
        txtid.setCellValueFactory(new PropertyValueFactory<>("idm"));
          txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        txtnbb.setCellValueFactory(new PropertyValueFactory<>("nombrebloc"));
    tablemag.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lm.size()));
}

///// PDF

    @FXML
    private void exportPDF(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
        try {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sport", "root", "");
                 PreparedStatement pt = con.prepareStatement("select * from magasin");
                 ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document(){};
                       
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                              my_pdf_report.open();  
//                             my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Magasins"));
                             my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(3);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" nom"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("adresse"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("nombrebloc"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String nom= rs.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(nom));
                                       my_report_table.addCell(table_cell);
                                       String adresse=rs.getString("adresse");
                                       table_cell=new PdfPCell(new Phrase(adresse));
                                       my_report_table.addCell(table_cell);
                                       String nombrebloc=rs.getString("nombrebloc");
                                       table_cell=new PdfPCell(new Phrase(nombrebloc));
                                       my_report_table.addCell(table_cell);
                                       
                                        
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
                       System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "impression effectuée");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               
    File myFile = new File("C:\\Users\\malak_6\\Desktop\\integration\\pdf_report_from_sql_using_java.pdf");
    Desktop.getDesktop().open(myFile);

       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }


    }

    @FXML
    private void sms(MouseEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("SMS.fxml"));
        Parent root= loader.load();
        Scene scene= new Scene(root);
                Stage primaryStage = new Stage();

        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
   
    ///////////// Excel
   
  /* @FXML
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
            File myFile = new File("C:/Users/dell/Desktop/Java-2021/netbeans/connect_sport/Détails Réclamations.xlsx");
             Desktop.getDesktop().open(myFile);
    }*/

    @FXML
    private void acc(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController1 ap= loader.getController();
        btnacc.getScene().setRoot(root);
    }


}
