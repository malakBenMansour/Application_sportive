/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import tools.MyDB;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
//import java.util.Date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Actualite;
import java.awt.Color;
import java.awt.Desktop;
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
import javafx.embed.swing.SwingFXUtils;
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
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import org.controlsfx.control.Notifications;
import services.ActualiteService;
import static services.ActualiteService.decodeToImage;
import sun.misc.BASE64Decoder;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherActController implements Initializable {

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
    @FXML
    private TextField nbact;
    @FXML
    private TableView<Actualite> tbact;
    @FXML
    private TableColumn<Actualite, String> col_titre;
    @FXML
    private TableColumn<Actualite, String> col_desc;
    @FXML
    private TableColumn<Actualite, Date> col_dat;

    /**
     * Initializes the controller class.
     */
    ActualiteService as= new ActualiteService();
  
    @FXML
    private ImageView btnacceuil;
    @FXML
    private TableColumn<Actualite, Integer> col_id;
    @FXML
    private ComboBox<String> comboact;
    @FXML
    private RadioButton asc;
    @FXML
    private RadioButton desc;
    @FXML
    private ToggleGroup ordre;
 String col,ord;
    @FXML
    private TableColumn<Actualite, ImageView> col_img;
    @FXML
    private Button btnPDF;
    @FXML
    private ImageView btnacceuil1;
    @FXML
    private ImageView btnacceuil11;
    @FXML
    private TableColumn<Actualite, Integer> col_idp;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
    comboact.getItems().removeAll(comboact.getItems());
     comboact.getItems().addAll("Titre", "Date ajout");
    comboact.getSelectionModel().select("Titre");
    col="titre";
    ord="ASC";        
        
        
        
        affiche_tab();
 
tbact.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
               
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Edit_act.fxml"));
       
       Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherActController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        Edit_actController act= loader.getController();
        
 act.setdata(tbact.getSelectionModel().getSelectedItem().getId());
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
        //@Override
        //public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //}
        });
comboact.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    System.out.println("ooooooooooooooooooooooooo  " + comboact.getSelectionModel());
    if (comboact.getSelectionModel().getSelectedItem().equals("Titre"))
            col="titre";
        else if   (comboact.getSelectionModel().getSelectedItem().equals("Date ajout"))
            col="dateajout" ;
research();
});
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_act.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_actController ap= loader.getController();
        btnajout.getScene().setRoot(root);
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try{
      as.supprimer(tbact.getSelectionModel().getSelectedItem().getId()); 
      affiche_tab();
      Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Actualité Supprimée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
		alert.setHeaderText("Supprimer actualité");
		alert.setContentText("Supression effectuée avec succès!");

		alert.showAndWait();
        }
        catch (Exception ex) {
                    
                }
    
    }


    @FXML
    private void retourHome(ActionEvent event) throws IOException {
              FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilResponsableController ap= loader.getController();
        btnajout.getScene().setRoot(root);
        
    }

    private void afficher(ActionEvent event) {
        affiche_tab();
    }
private void affiche_tab() {
    
    List<Actualite> le=as.afficher();
        ObservableList<Actualite> datalist = FXCollections.observableArrayList(le);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_dat.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
        col_idp.setCellValueFactory(new PropertyValueFactory<>("idres"));
        tbact.setItems(datalist);
      nbact.setText(String.valueOf(le.size()));
        tbact.refresh();
        
         
    
     
    }
    @FXML
    private void nbrjoueur(ActionEvent event) {
    
                                }

    @FXML
    private void retourHome(MouseEvent event) throws IOException {
     
}

    @FXML
    private void triact(ActionEvent event) {
  
    }
    public void research()
{
  ActualiteService as = new ActualiteService ();
        List<Actualite> la = as.recherchermutli(txtrecherche.getText(),col,ord);
     tbact.getItems().clear();
      ObservableList<Actualite> datalist = FXCollections.observableArrayList(la);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_dat.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
        col_idp.setCellValueFactory(new PropertyValueFactory<>("idres"));
    tbact.setItems(datalist); 
    
    nbact.setText(String.valueOf(la.size()));
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
    private void exportPDF(ActionEvent event) {
        
        try{
        Actualite a=as.afficher_ById(tbact.getSelectionModel().getSelectedItem().getId());
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 40, Font.UNDERLINE, new CMYKColor(0, 100, 0, 50));
Font titledate = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new CMYKColor(0, 100, 100, 80));

Font titledesc = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new CMYKColor(50, 100, 0, 0));

          Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(""+a.getTitre()+".pdf"));
         document.open();
          Paragraph TitleAct = new Paragraph(a.getTitre(), titleFont);
    TitleAct.setAlignment(1);
         document.add(TitleAct);
         
//Add Image
javafx.scene.image.Image img=SwingFXUtils.toFXImage(decodeToImage(a.getImage()), null);
//Add Image
BASE64Decoder decoder = new BASE64Decoder();
           byte[] imgb = decoder.decodeBuffer(a.getImage());
	Image image1 = Image.getInstance(imgb);
			//For Fixed Positioning
			//image1.setAbsolutePosition(100f, 550f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(250, 250);
                        image1.setAlignment(1);
			//Add to document
			document.add(image1);
                        Paragraph DateAct = new Paragraph("Date de l'actualité : "+a.getDate_ajout().toString(), titledate);
    DateAct.setAlignment(0);
         document.add(DateAct);
Paragraph DescAct = new Paragraph("Description : "+a.getDescription(), titledesc);
    DescAct.setAlignment(0);
         document.add(DescAct);
  
    
    //Add to document
    //document.add((Element) img);
        
         document.close();
         writer.close();
         Notifications notificationBuilder=Notifications.create()
              .title("Succées").text("Actualité Exportée ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
         File myFile = new File("C:\\Users\\malak_6\\Desktop\\integration\\"+a.getTitre()+".pdf");
             Desktop.getDesktop().open(myFile);
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        
    }catch(Exception e){}
    }

    public void exportExcel() throws SQLException, FileNotFoundException, IOException{
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
            
            Alert alert = new Alert(AlertType.INFORMATION);
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