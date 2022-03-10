/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import static GUI.main.Userconnected;
import entities.Produit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import tools.Myconnexion;

/**
 *
 * @author dhiabensaada
 */
public class ServiceProduit implements DService<Produit> {
    

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceProduit() {
       cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
     public void ajouterpst(Produit p) {
       String req = "INSERT into produit (nom_p,prix,categorie,idm,image,idperso) values (?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
           
            pst.setString(1,p.getNom_p());
            pst.setFloat(2, p.getPrix());
            pst.setString(3, p.getCategorie());
             pst.setInt(4, p.getIdm());   
            pst.setString(5, p.getImage());
             pst.setInt(6, p.getIdperso());
            pst.executeUpdate();

        } catch (SQLException ex) {
          System.out.println(" erreur d'ajout dans le produit  ");

        }
        

    }


    @Override
    public void supprimer(int id) {
        try {
           
            st=cnx.createStatement();
            String query="DELETE FROM Produit WHERE idp="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
             System.out.println(" erreur de suppression  du produit  "); 
        }
    }

    @Override
    public void modifier(int id_amodifier, Produit p) {
         
           String req = "UPDATE Produit SET nom_p=?,prix=?,categorie=?,idm=? where idp=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom_p());
            pst.setFloat(2, p.getPrix());
           
            pst.setString(3, p.getCategorie());
          pst.setInt(4, p.getIdm());
          //pst.setString(5, p.getImage());
            pst.setInt(5, id_amodifier);
            pst.setInt(6,p.getIdperso());
            pst.executeUpdate();
        } catch (SQLException ex) { 
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> afficher() {
        List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT produit.* ,magasin.nom as mag FROM  produit,magasin where( produit.idm=magasin.idm and idperso='"+Userconnected.getId()+"') ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                 p.setPrix(rs.getFloat("prix"));
               p.setCategorie(rs.getString("categorie"));
               p.setMagasin(rs.getString("mag"));
              p.setImage(rs.getString("image"));
              p.setIdperso(rs.getInt("idperso"));
              try{
                   Image img=SwingFXUtils.toFXImage(decodeToImage(p.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               p.setImv(v);
              }
               catch(Exception ex){}
                lp.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lp ;
    }
    @Override
    public List<Produit> afficherById(int id) {
        List<Produit> lp =new ArrayList<>();
        try {
            
            st=cnx.createStatement();
            
            String query="SELECT produit.*,magasin.nom as mag FROM Produit,Magasin WHERE produit.idm=magasin.idm AND idp="+id;
            rs=st.executeQuery(query);
            while(rs.next()){
             Produit p = new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                 p.setPrix(rs.getInt("prix"));
                 p.setCategorie(rs.getString("categorie"));
                 p.setMagasin(rs.getString("mag"));
                 p.setImage(rs.getString("image"));
                lp.add(p); 
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage by id dans produit ");        }
        return lp ;
    }

    /**
     *
     * @param nomp
     */
    //@Override
    public  List<Produit> rechercherparnom(String nomp)
       {
             List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query=" SELECT * FROM  produit where( nom_p='"+nomp+"' and idperso='"+Userconnected.getId()+"') ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                p.setPrix(rs.getFloat("prix"));
              p.setCategorie(rs.getString("categorie"));
                p.setIdm(rs.getInt("idm"));
   p.setIdperso(rs.getInt("idperso"));
                
                lp.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lp ;
       }
    
    
     public  List<Produit> triparnom()
       {
             List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query=" SELECT * FROM  produit ORDER BY nom_p ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                 p.setPrix(rs.getFloat("prix"));
               p.setCategorie(rs.getString("categorie"));
                p.setIdm(rs.getInt("idm"));
                p.setIdperso(rs.getInt("idperso"));
                lp.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lp ;
       }
     
    
     
     
     
     
     
     
     
     
      
    public List<Produit> afficherSearch(String critere , String col ,String ordre) {
        List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
           
            String query="SELECT produit.* ,magasin.nom as mag FROM  Produit,Magasin where idperso='"+Userconnected.getId()+"' and produit.idm=magasin.idm and (nom_p like '%"+critere+"%' or cast(prix as char) like '%"+critere+"%' or categorie like '%"+critere+"%' or magasin.nom like '%"+critere+"%') order by "+col+" "+ordre+"" ;
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                 p.setPrix(rs.getFloat("prix"));
               p.setCategorie(rs.getString("categorie"));
               p.setMagasin(rs.getString("mag"));
               p.setImage(rs.getString("image"));
               p.setIdperso(rs.getInt("idperso"));
               
                Image img=SwingFXUtils.toFXImage(decodeToImage(p.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               p.setImv(v);
                lp.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lp ;
    }
    
    
    
    
    public Map<String, Integer> NbPdtByCat() {
        Map<String, Integer> mp =new HashMap<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT count(*) as nbp, categorie  FROM  Produit where idperso='"+Userconnected.getId()+"'group by categorie  ";
            rs=st.executeQuery(query);
            while(rs.next()){
               
                mp.put(rs.getString("categorie"),rs.getInt("nbp"));
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return mp ;
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
    
    
     public boolean existe_pdt(String nom_p, int idm , int id) {
        boolean b=false;
        try {
            Statement st;
            st=cnx.createStatement();
           String query; 
           if(id==0)
           query="SELECT * FROM `produit` WHERE nom_p='"+nom_p+"' and idm="+idm+"";
           else
           query="SELECT * FROM `produit` WHERE nom_p='"+nom_p+"' and idm="+idm+" and idp<>"+id+"";
           ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              b=true; 
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(b);
        return b; 
    } 
     
      public List<Produit> afficherF() {
        List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT produit.* ,magasin.nom as mag FROM  Produit,Magasin where produit.idm=magasin.idm  ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                 p.setPrix(rs.getFloat("prix"));
               p.setCategorie(rs.getString("categorie"));
               p.setMagasin(rs.getString("mag"));
                p.setImage(rs.getString("image"));
                p.setIdp(rs.getInt("idperso"));
              try{
                   Image img=SwingFXUtils.toFXImage(decodeToImage(p.getImage()), null);
                ImageView v = new ImageView();
                v.setImage(img);
                v.setFitWidth(60);
                v.setFitHeight(60);
               p.setImv(v);
              }
               catch(Exception ex){}
                lp.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lp ;
    }

 public Map<String, Integer> NbPdtByCatF() {
        Map<String, Integer> mp =new HashMap<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT count(*) as nbp, categorie  FROM  Produit group by categorie ";
            rs=st.executeQuery(query);
            while(rs.next()){
               
                mp.put(rs.getString("categorie"),rs.getInt("nbp"));
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return mp ;
    }
    

}



 

