/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static GUI.main.Userconnected;
import entities.Categorie;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyDB;

/**
 *
 * @author dell
 */
public class ReclamationService implements Service<Reclamation>{
Connection cnx;
    public ReclamationService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouter(Reclamation t) {
       try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `reclamation`(`titre`, `description`, `dateajout`, `id_cat`, `etat`, `num_commande`, `id_per`) VALUES "
                    + "('"+t.getTitre()+"',"
                    + "'"+t.getDescription()+"',"
                    + "'"+t.getDate_ajout()+"',"
                    + ""+t.getId_cat()+","
                    + "'"+t.getEtat()+"',"
                    + "'"+t.getNum_commande()+"',"
                    + ""+t.getId_per()
                    +")";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
          try {
            Statement st;
            st=cnx.createStatement();
            String query="DELETE FROM `reclamation` WHERE id="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id_amodifier, Reclamation modifier) {
         try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `reclamation` SET `titre`='"+modifier.getTitre()+"',"
                    + "`description`='"+modifier.getDescription()+"',"
                    + "`dateajout`='"+modifier.getDate_ajout()+"',"
                    + "`id_cat`='"+modifier.getId_cat()+"',"
                    + "`etat`='"+modifier.getEtat()+"',"
                    + "`num_commande`='"+modifier.getNum_commande()+"',"
                    + "`id_per`='"+modifier.getId_per()+"' "
                    + "WHERE id="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `reclamation` where id_per='"+Userconnected.getId()+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setId_cat(rs.getInt("id_cat"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               e.setId_per(rs.getInt("id_per"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }

    public List<Reclamation> afficher_rec() {
        List<Reclamation> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT reclamation.* ,categorie.nom as nomc,concat('"+Userconnected.getNom()+"',' ','"+Userconnected.getPrenom()+"') as nomp FROM `reclamation`,categorie where reclamation.id_per='"+Userconnected.getId()+"' and reclamation.id_cat=categorie.id";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setCategorie(rs.getString("nomc"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               //e.setNom_personne(Userconnected.getNom());
              
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
        public List<Reclamation> afficher_rec_critere(String critere,String col, String ordre) {
        List<Reclamation> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT reclamation.* ,categorie.nom as nomc,concat('"+Userconnected.getNom()+"','"+Userconnected.getPrenom()+"') as nomp FROM `reclamation`,categorie where reclamation.id_per='"+Userconnected.getId()+"' and reclamation.id_cat=categorie.id and (titre like '%"+critere+"%' or description like '%"+critere+"%' or cast(dateajout as char) like '%"+critere+"%' or  categorie.nom like '%"+critere+"%' or '"+Userconnected.getNom()+"' like '%"+critere+"%' or '"+Userconnected.getPrenom()+"'like '%"+critere+"%'  or  num_commande like '%"+critere+"%') order by "+col+" "+ordre;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setCategorie(rs.getString("nomc"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               //e.setNom_personne(Userconnected.getNom());
              
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
        
    public Reclamation afficher_recbyid(int id) {
        Reclamation e=new Reclamation();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT reclamation.* ,categorie.nom as nomc FROM `reclamation`,categorie where  reclamation.id_cat=categorie.id and reclamation.id="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setCategorie(rs.getString("nomc"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               e.setId_per(rs.getInt("id_per"));
              
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    @Override
    public Reclamation afficher_ById(int id) {
          Reclamation e=new Reclamation();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `reclamation` WHERE id="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setId_cat(rs.getInt("id_cat"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               e.setId_per(rs.getInt(Userconnected.getId()));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
      public String[] mail_pers() {
         String[] m=new String[2];
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT concat('"+Userconnected.getNom()+"' , '"+Userconnected.getPrenom()+"') as nomp, mail FROM personne";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
              
                m[0]=rs.getString("mail");
                m[1]=rs.getString("nomp");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m; 
    }  
public List<Reclamation> afficher_rec_fan() {
        List<Reclamation> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT reclamation.*  FROM `reclamation` where id_per='"+Userconnected.getId()+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
               
                e.setEtat(rs.getString("etat"));
               
              
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
 public List<Reclamation> afficher_rec_critere_fan(String critere,String col, String ordre, int id_pers) {
        List<Reclamation> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT reclamation.* ,categorie.nom as nomc,concat('"+Userconnected.getNom()+"','"+Userconnected.getPrenom()+"') as nomp FROM `reclamation`,categorie where reclamation.id_per='"+Userconnected.getId()+"' and reclamation.id_cat=categorie.id and id_per="+id_pers+" and  (titre like '%"+critere+"%' or description like '%"+critere+"%' or cast(dateajout as char) like '%"+critere+"%' or  categorie.nom like '%"+critere+"%' or  '"+Userconnected.getNom()+"'like '%"+critere+"%' or  '"+Userconnected.getPrenom()+"' like '%"+critere+"%' or  etat like '%"+critere+"%' or  num_commande like '%"+critere+"%') order by "+col+" "+ordre;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setCategorie(rs.getString("nomc"));
                e.setEtat(rs.getString("etat"));
               e.setNum_commande(rs.getString("num_commande"));
               //e.setNom_personne(Userconnected.getNom());
              
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
}
