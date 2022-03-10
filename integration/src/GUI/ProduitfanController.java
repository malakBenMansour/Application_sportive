/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXScrollPane;
import entities.Produit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ServiceProduit;
import static services.ServiceProduit.decodeToImage;
import sun.misc.BASE64Decoder;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ProduitfanController implements Initializable {

    @FXML
    private Button reserv;
    @FXML
    private Button txtacceuil;
    @FXML
    private VBox boxprod;
   
    public static List <Produit> listp= new ArrayList <Produit> ();
    public static List <Integer> listqt= new ArrayList <Integer> () ;
     List<Produit> lp;
     int i;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boxprod.setAlignment(Pos.CENTER);
        ServiceProduit sp = new ServiceProduit();
         lp=sp.afficherF();
         
        for ( i=0 ; i<lp.size(); i++){
             javafx.scene.image.Image img=SwingFXUtils.toFXImage(decodeToImage(lp.get(i).getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(100);
                v.setFitHeight(100);
                
                Label Lind= new Label(String.valueOf(i));
                Lind.setVisible(false);
                Label L = new Label("\n"+lp.get(i).getNom_p()+"\n"+lp.get(i).getPrix()+"\n"+"Disponible au magasin "+lp.get(i).getMagasin());
                L.setStyle("-fx-font-weight:bold");
                L.setMinWidth(300);
                 L.setMaxWidth(300);
                 Button Btn = new Button("Ajouter au panier");
                   Btn.setStyle("-fx-background-color:  #aac0f9; -fx-padding: 15px; -fx-border-insets: 5px; -fx-background-insets: 5px;" );
                  
               
                  // Btn.getStylesheets().add("DocFXML.css");

               //  Btn.getStyleClass().add("button1");
        Btn.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                      int j = Integer.valueOf(Lind.getText());
                  
                    if (!(listp.contains(lp.get(j))))
                     {
                         System.out.println("oooooooooooooooooooookkkkkkkkkkkkkkkkkkkkkkkkkkk   "+lp.get(j));
                         listp.add(lp.get(j));
                         listqt.add(1);
                     } else {
                        System.out.println("ellllllseee");
                         
                         int ind=listp.indexOf(lp.get(j));
                        
                         listqt.set(ind, listqt.get(ind)+1);
                     }   
                 }
             });
         HBox hb=new HBox(10,v,L,Btn, Lind);
            boxprod.getChildren().add(hb);
       boxprod.setSpacing(10);
        }
        

        //Create buttons to display the flags
       
       
     
    }    

    @FXML
    private void reserver(ActionEvent event) {
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 40, Font.UNDERLINE, new CMYKColor(0, 100, 0, 50));
Font titledate = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new CMYKColor(0, 100, 100, 80));
       try
      {  
          
          Font titledesc = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new CMYKColor(50, 100, 0, 0));

          Document document = new Document();
     
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Facture.pdf"));
         document.open();
          Paragraph TitleAct = new Paragraph("Facture", titleFont);
    TitleAct.setAlignment(1);
         document.add(TitleAct);
         
          Paragraph datef = new Paragraph("\n\n\nDate Facture "+ java.time.LocalDateTime.now() +"\n\n\n\n", titledate);
    datef.setAlignment(0);
         document.add(datef);
         float total = 0 ;
        for(int i=0; i<listp.size();i++)
        {
           
        
       
        


         
//Add Image
javafx.scene.image.Image img=SwingFXUtils.toFXImage(decodeToImage(listp.get(i).getImage()), null);
//Add Image
BASE64Decoder decoder = new BASE64Decoder();
           byte[] imgb = decoder.decodeBuffer(listp.get(i).getImage());
	Image image1 = Image.getInstance(imgb);
			//For Fixed Positioning
			//image1.setAbsolutePosition(100f, 550f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(40, 40);
                        image1.setAlignment(0);
			//Add to document
			document.add(image1);
                        Paragraph libelle = new Paragraph(listp.get(i).getNom_p() + "       "+listqt.get(i)+ "     "+listp.get(i).getPrix()*listqt.get(i)+"\n", titledate);
    libelle.setAlignment(0);
         document.add(libelle);
total=total+listp.get(i).getPrix()*listqt.get(i);
    
    //Add to document
    //document.add((Element) img);
        
        
     
            
        }
        Paragraph libelle = new Paragraph("\nLe montant à payer est "+total, titledate);
    libelle.setAlignment(0);
         document.add(libelle);
         document.close();
         writer.close();
         File myFile = new File("C:\\Users\\malak_6\\Desktop\\integration\\Facture.pdf");
             Desktop.getDesktop().open(myFile);
             } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        
    catch(Exception e){}
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilFan.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilFanController ap= loader.getController();
        reserv.getScene().setRoot(root);
    }
    
}
