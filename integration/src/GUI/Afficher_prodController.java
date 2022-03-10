/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import static GUI.Firstwindow.stage;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
//import java.awt.Color;
import javafx.embed.swing.SwingFXUtils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import entities.Produit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import services.ServiceProduit;
import tools.Myconnexion;
////////////

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import tools.Constants;
import tools.JFXDialogTool;
////////////////////////////////////////////////////


/**
 * FXML Controller class
 *
 * @author msi
 */
public class Afficher_prodController implements Initializable {

    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private ImageView img_supp;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView btnacceuil;
    @FXML
    private TextField nbprod;
    @FXML
    private TableView<Produit> tableprod;
    @FXML
    private TableColumn<Produit, Integer> txtid;
    @FXML
    private TableColumn<Produit, String> txtnom;
    @FXML
    private ComboBox<String> comboprod;
    @FXML
    private RadioButton ASC;
    @FXML
    private ToggleGroup ordre;
    @FXML
    private RadioButton DESC;
    @FXML
    private TableColumn<Produit, Float> txtprix;
    @FXML
    private TableColumn<Produit, String> txtcategorie;
    @FXML
    private TableColumn<Produit, String> txtmag;
    String col,ord;
    @FXML
    private StackPane stckJoueur;
    @FXML
    private AnchorPane rootJoueur;
    @FXML
    private AnchorPane ContainerCodeQR;
    @FXML
    private ImageView imgQRCodeGen;
    private JFXDialogTool dialogCodeQR;
    private Image genQRCodeImg; // Generated QR Code image
    @FXML
    private Button btnQR;
    @FXML
    private TableColumn<Produit, ImageView> txtimgpdt;
    @FXML
    private TableColumn<Produit, Integer> txtidp;
    @FXML
    private Button btnacc;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboprod.getItems().removeAll(comboprod.getItems());
    comboprod.getItems().addAll("Nom", "Prix", "Categorie");
    comboprod.getSelectionModel().select("Nom");
       col="nom_p";
       ord="ASC";  
       txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               research();
            }
        });



comboprod.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (comboprod.getSelectionModel().getSelectedItem().equals("Nom"))
            col="nom_p";
        else if   (comboprod.getSelectionModel().getSelectedItem().equals("Prix"))
            col="prix" ;
        else  if (comboprod.getSelectionModel().getSelectedItem().equals("Categorie"))
             col="categorie";
        research();
}); 
        tableprod.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
               
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Edit_prod.fxml"));
       
       Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(Afficher_prodController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        Edit_prodController act= loader.getController();
        
        act.setdata(tableprod.getSelectionModel().getSelectedItem().getIdp());
       act.charger_prod();
       btnajout.getScene().setRoot(root);       
                
                
                
            }
            }         
        });
        lister_prod();
    }    
    
    @FXML
    private void showchart(ActionEvent event){
       try {
           ServiceProduit p = new ServiceProduit();
           List<Produit> lp=p.afficher();
    ObservableList<Produit> data=FXCollections.observableArrayList(lp);
            FXMLLoader chart= new FXMLLoader(getClass().getResource("chart1.fxml"));
            Parent root = chart.load();
            Chart1Controller mc = chart.getController();
           
           
            Scene scene = new Scene(root);
           Stage modifStage = new Stage();
            
            modifStage.setTitle("Nombre de produit par catégorie");
            modifStage.setScene(scene);
            modifStage.show();
            
             Chart1Controller controller = chart.getController();
             controller.setReclamationData();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Afficher_prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Add_prod.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        Add_prodController ap= loader.getController();
        btnajout.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) {
         ServiceProduit sp = new ServiceProduit ();
        try {
         
            sp.supprimer(tableprod.getSelectionModel().getSelectedItem().getIdp());
            lister_prod ();
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



    @FXML
    private void ordreprod(ActionEvent event) {
         if (ASC.isSelected())
            ord="ASC";
        else if    (DESC.isSelected())
            ord="DESC";
        
        research();
    }

    @FXML
    private void triprod(ActionEvent event) {
        
    }
     public void lister_prod ()
    {
    ServiceProduit sp = new ServiceProduit();
     List<Produit> lp = sp.afficher();
     tableprod.getItems().clear();
      ObservableList<Produit> datalist = FXCollections.observableArrayList(lp);
        txtid.setCellValueFactory(new PropertyValueFactory<>("idp"));
          txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        txtprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        txtcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        txtmag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
        txtimgpdt.setCellValueFactory(new PropertyValueFactory<>("imv"));
        txtidp.setCellValueFactory(new PropertyValueFactory<>("idperso"));
    tableprod.setItems(datalist); 
    
    nbprod.setText(String.valueOf(lp.size()));
    
              
       
    }
     public void research()
{
  ServiceProduit sp = new ServiceProduit();
     List<Produit> lp = sp.afficherSearch(txtrecherche.getText(), col, ord);
     tableprod.getItems().clear();
      ObservableList<Produit> datalist = FXCollections.observableArrayList(lp);
        txtid.setCellValueFactory(new PropertyValueFactory<>("idp"));
          txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        txtprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        txtcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        txtmag.setCellValueFactory(new PropertyValueFactory<>("magasin"));
        txtidp.setCellValueFactory(new PropertyValueFactory<>("idperso"));
    tableprod.setItems(datalist); 
    
    nbprod.setText(String.valueOf(lp.size()));
}
/////// Excel
  /* @FXML
    public void exportExcel() throws SQLException, IOException {
        FileOutputStream file = null;
        try {
            Connection cnx;
            cnx=Myconnexion.getInstance().getCnx();
            String query = "Select * from produit";
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Details Produits");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("idproduit");
            header.createCell(1).setCellValue("nom_produit");
            header.createCell(2).setCellValue("prix produit");
            header.createCell(3).setCellValue("categorie produit ");
            header.createCell(4).setCellValue("idm magasin");
            int index = 1;
            while(rs.next()){
                
                
                
               
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("idp"));
                row.createCell(1).setCellValue(rs.getString("nom_p"));
                row.createCell(2).setCellValue(rs.getString("prix"));
                row.createCell(3).setCellValue(rs.getString("categorie"));
                row.createCell(4).setCellValue(rs.getString("idm"));
                index++;
            }
            file = new FileOutputStream("Details Produits.xlsx");
            wb.write(file);
            file.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_prodController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
          } catch (IOException ex) {
                Logger.getLogger(Afficher_prodController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
    
    //// code qr
     public static Image encode(String data, int width, int height, String foregroundColor, String backgroundColor) {
        try {
            BitMatrix byteMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(java.awt.Color.decode(backgroundColor));
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.decode(foregroundColor));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }
    }
   public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }
    private void showDialogCodeQR() {

        rootJoueur.setEffect(Constants.BOX_BLUR_EFFECT);
        ContainerCodeQR.setVisible(true);

        dialogCodeQR = new JFXDialogTool(ContainerCodeQR, stckJoueur);
        dialogCodeQR.show();

        dialogCodeQR.setOnDialogClosed(ev -> {
            
            tableprod.setDisable(false);
            rootJoueur.setEffect(null);
            ContainerCodeQR.setVisible(false);
            lister_prod();
        });
    }

 
    @FXML
    private void hideDialogCodeQR(MouseEvent event) {
        if (dialogCodeQR != null) {
            dialogCodeQR.close();
        }
    }

    @FXML
    private void showQR(ActionEvent event) {
        try{
             String nom_p = tableprod.getSelectionModel().getSelectedItem().getNom_p();
                float prix = tableprod.getSelectionModel().getSelectedItem().getPrix();
             //   int nbr = tableprod.getSelectionModel().getSelectedItem().getNbr();
               String categorie = tableprod.getSelectionModel().getSelectedItem().getCategorie();
               
                String AllInfo = " nom_p " + nom_p + "\n prix " + prix +  "\n categorie " + categorie + "";
                ////////////////////////:
                System.out.println("" + AllInfo);
                if (!AllInfo.isEmpty()) {
                    String foregroundColor = "#2E3437";
                    String backgroundColor = "#FFFFFF";
                    genQRCodeImg = encode(AllInfo, Integer.parseInt("300"), Integer.parseInt("300"), foregroundColor, backgroundColor);
                    if (genQRCodeImg != null) {
                        imgQRCodeGen.setImage(genQRCodeImg);
                    }

                    showDialogCodeQR();
                }
        }catch(Exception ex)
        {
            
        }
    }

    @FXML
    private void acc(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("menurec1.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        MenurecController1 ap= loader.getController();
        btnacc.getScene().setRoot(root);
    }
}
