/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Actualite;
import entities.Categorie;
import entities.Reclamation;
import static GUI.Edit_actController.decodeToImage;
import static GUI.main.Userconnected;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyDB;
import java.sql.Date;
import java.util.Collections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
/**
 *
 * @author dell
 */
public class ActualiteService implements Service<Actualite> {
 Connection cnx;
    public ActualiteService(){
        cnx=MyDB.getInstance().getConnexion();
        System.out.println("connexion etablie");
    }
    @Override
    public void ajouter(Actualite t) {
         try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `actualite`(`titre`, `description`, `image`,`dateajout`,`idres`) VALUES "
                    + "('"+t.getTitre()+"',"
                    + "'"+t.getDescription()+"',"
                    + "'"+t.getImage()+"',"
                    + "'"+t.getDate_ajout()+"',"
                     + "'"+t.getIdres()
                    +"')";
            st.executeUpdate(query);
             System.out.println("Actualité ajoutée avec succées");
        } catch (SQLException ex) {
             System.out.println("erreur d'ajout d'une actualité");
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @Override
    public void supprimer(int id) {
         try {
            Statement st;
            st=cnx.createStatement();
            String query="DELETE FROM `actualite` WHERE id="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id_amodifier, Actualite modifier) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `actualite` SET `titre`='"+modifier.getTitre()+"',"
                    + "`description`='"+modifier.getDescription()+"',"
                    + "`image`='"+modifier.getImage()+"',"
                    + "`dateajout`='"+modifier.getDate_ajout()+"' "     
                    // + "`idres`='"+modifier.getIdres()+"' "     
                    + "WHERE id="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Actualite> afficher() {
         List<Actualite> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `actualite` where idres='"+Userconnected.getId()+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              //  Actualite e=new Actualite(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getString("image"),rs.getDate("dateajout"));
                
              Actualite e=new Actualite();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                Image img=SwingFXUtils.toFXImage(decodeToImage(e.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               e.setImg(v);
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setIdres(rs.getInt("idres"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }

    @Override
    public Actualite afficher_ById(int id) {
         Actualite e=new Actualite();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `actualite` WHERE id='"+id+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                e.setDate_ajout(rs.getDate("dateajout"));
               e.setIdres(rs.getInt("idres"));
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
   
          public  List<Actualite> recherchermutli(String critere, String col, String ordre)
       {
             List<Actualite> lm =new ArrayList<>();
        try {
            System.out.println(critere);
            Statement st;
            st=cnx.createStatement();
            
            String query=" SELECT * FROM  actualite where titre like '%"+critere+"%' or description like '%"+critere+"%' or cast(dateajout as char) like '%"+critere+"%' order by "+col+" "+ordre;
           
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Actualite e =new Actualite();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                Image img=SwingFXUtils.toFXImage(decodeToImage(e.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               e.setImg(v);
                e.setDate_ajout(rs.getDate("dateajout"));
               e.setIdres(rs.getInt("idres"));

                
                lm.add(e);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lm ;
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
          
      
    public boolean existe_act(String titre, Date d, int id) {
        boolean b=false;
        try {
            Statement st;
            st=cnx.createStatement();
           String query; 
           if(id==0)
           query="SELECT * FROM `actualite` WHERE titre='"+titre+"' and dateajout='"+d+"' ";
           else
           query="SELECT * FROM `actualite` WHERE titre='"+titre+"' and dateajout='"+d+"' and id<>"+id+"";
           ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              b=true; 
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(b);
        return b; 
    }  
     public List<Actualite> afficherF() {
         List<Actualite> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `actualite`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              //  Actualite e=new Actualite(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getString("image"),rs.getDate("dateajout"));
                
              Actualite e=new Actualite();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                Image img=SwingFXUtils.toFXImage(decodeToImage(e.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               e.setImg(v);
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setIdres(rs.getInt("idres"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
public List<Actualite> afficherA() {
         List<Actualite> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `actualite`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              //  Actualite e=new Actualite(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getString("image"),rs.getDate("dateajout"));
                
              Actualite e=new Actualite();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                Image img=SwingFXUtils.toFXImage(decodeToImage(e.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               e.setImg(v);
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setIdres(rs.getInt("idres"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
}
