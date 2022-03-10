/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.main.Userconnected;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Actualite;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import services.ActualiteService;
import static services.ActualiteService.decodeToImage;
import sun.misc.BASE64Decoder;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ActfanController implements Initializable {

    @FXML
    private Button txtacceuil;
    @FXML
    private ImageView imgact;
    @FXML
    private Label ltitre;
    @FXML
    private Label ldesc;
    @FXML
    private Label ldate;
    @FXML
    private Button btnprec;
    @FXML
    private Button btnsuiv;
    @FXML
    private Button btnimp;

    /**
     * Initializes the controller class.
     */
    ActualiteService as=new ActualiteService();
      List<Actualite> le=as.afficherF();
      
      int pos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         //System.out.println("mchee");
         pos=0;
        aff_act(pos);
        
    
    }  
    void aff_act(int p)
    {
          Image img=SwingFXUtils.toFXImage(decodeToImage(le.get(p).getImage()), null);
          imgact.setImage(img);
          ltitre.setText(le.get(p).getTitre());
          ldesc.setText(le.get(p).getDescription());
          ldate.setText(le.get(p).getDate_ajout().toString());
    }
            

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilfan.fxml"));
       
       Parent root = (Parent)loader.load();
        
        
        AcceuilFanController ap= loader.getController();
        btnprec.getScene().setRoot(root);
    }

    @FXML
    private void previous(ActionEvent event) {
   if(pos<=0)
        pos=le.size()-1;
   else pos=pos-1;
   
   aff_act(pos);
   
    }

    @FXML
    private void next(ActionEvent event) {
 if(pos>=le.size()-1)
        pos=0;
   else pos=pos+1;
   
   aff_act(pos);    
    }

    @FXML
    private void print(ActionEvent event) {
      try{
        Actualite a=as.afficher_ById(le.get(pos).getId());
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
	com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance(imgb);
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
  
    public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    
    
}
