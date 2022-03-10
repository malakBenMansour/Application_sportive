/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import static GUI.main.Userconnected;
import entities.Commande;
import entities.Magasin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.Myconnexion;
/**
 *
 * @author dhiabensaada
 */
public class ServiceMagasin  implements DService<Magasin> {
    

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceMagasin() {
       cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
     public void ajouterpst(Magasin m) {
       String req = "INSERT into Magasin (nom,adresse,nombrebloc,idps) values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
           
            pst.setString(1,  m.getNom());
            pst.setString(2, m.getAdresse());
            pst.setInt(3, m.getNombrebloc());
            pst.setInt(4,m.getIdps());
            
            pst.executeUpdate();

        } catch (SQLException ex) {
          System.out.println(" erreur d'ajout dans le magasin ");

        }

    }


    @Override
    public void supprimer(int id) {
        try {
           
            st=cnx.createStatement();
            String query="DELETE FROM Magasin WHERE idm="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
             System.out.println(" erreur de suppression  du magasin "); 
        }
    }

    @Override
    public void modifier(int id_amodifier, Magasin m) {
         
           String req = "UPDATE Magasin SET nom=?,adresse=?,nombrebloc=? where idm=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getAdresse());
            pst.setInt(3, m.getNombrebloc());
            pst.setInt(4, id_amodifier);
            
            pst.executeUpdate();
        } catch (SQLException ex) { 
             System.out.println("erreur de modification du magasin ");
        }
    }

    @Override
    public List<Magasin> afficher() {
        List<Magasin> lm =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT * FROM  Magasin where idps='"+Userconnected.getId()+"' ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Magasin m =new Magasin();
                m.setIdm(rs.getInt("idm"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("adresse"));
                m.setNombrebloc(rs.getInt("nombrebloc"));
                m. setIdps(rs.getInt("idps"));
                lm.add(m);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lm ;
    }

    @Override
    public List<Magasin> afficherById(int id) {
        List<Magasin> lm =new ArrayList<>();
        try {
            
            st=cnx.createStatement();
            
            String query="SELECT * FROM Magasin WHERE idm="+id;
            rs=st.executeQuery(query);
            while(rs.next()){
                Magasin m = new Magasin();
                m.setIdm(rs.getInt("idm"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("adresse"));
                m.setNombrebloc(rs.getInt("nombrebloc"));
                m.setIdps(rs.getInt("idps"));
                lm.add(m); 
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage by id dans le magasin ");        }
        return lm ;
    }
public  List<Magasin> recherchermutli(String critere, String col, String ordre)
       {
             List<Magasin> lm =new ArrayList<>();
        try {
            System.out.println(critere);
            st=cnx.createStatement();
           // System.out.println(" SELECT * FROM  magasin where nom like '%"+critere+"%' or adresse like '%"+critere+"%' or cast(nombrebloc as char) like '%"+critere+"%' order by "+col+" "+ordre);
            String query=" SELECT * FROM  magasin   where idps='"+Userconnected.getId()+"' and nom like '%"+critere+"%' or adresse like '%"+critere+"%' or cast(nombrebloc as char) like '%"+critere+"%' order by "+col+" "+ordre;
           
                rs=st.executeQuery(query);
                while(rs.next()){
                Magasin m =new Magasin();
                m.setIdm(rs.getInt("idm"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("Adresse"));
              m.setNombrebloc(rs.getInt("nombrebloc"));
              
 m.setIdps(rs.getInt("idps"));
                
                lm.add(m);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lm ;
       }
    
    
    
     public List<String> afficher_nommag() {
        List<String> lm =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT nom FROM  Magasin ";
            rs=st.executeQuery(query);
            while(rs.next()){
               
                lm.add(rs.getString("nom"));
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du nom  du magasin  ");
        }
        return lm ;
    }
  public int return_idm(String nom) {
        int idmag=0 ;
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT idm FROM  Magasin where nom='"+nom+"' ";
            rs=st.executeQuery(query);
            while(rs.next()){
               
               idmag=rs.getInt("idm");
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du nom  du magasin  ");
        }
        return idmag ;
    }
  
  
  
  
 public boolean existe_pdt(String nom_mag,  int id) {
        boolean b=false;
        try {
            Statement st;
            st=cnx.createStatement();
           String query; 
           if(id==0)
           query="SELECT * FROM `magasin` WHERE nom='"+nom_mag+"' ";
           else
           query="SELECT * FROM `magasin` WHERE nom='"+nom_mag+"'   and idm<>"+id+"";
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
 public List<Magasin> afficherF() {
        List<Magasin> lm =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT * FROM  Magasin  ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Magasin m =new Magasin();
                m.setIdm(rs.getInt("idm"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("adresse"));
                m.setNombrebloc(rs.getInt("nombrebloc"));
                m.setIdps(rs.getInt("idps"));
                lm.add(m);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage du magasin  ");
        }
        return lm ;
    }
}
