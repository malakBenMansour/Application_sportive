/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
import utils.MyDB;

/**
 *
 * @author dell
 */
public class ReclamationService implements IService<Reclamation>{
Connection cnx;
    public ReclamationService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouter(Reclamation t) {
       try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `reclamation`(`titre`, `description`, `dateajout`, `id_cat`) VALUES "
                    + "('"+t.getTitre()+"',"
                    + "'"+t.getDescription()+"',"
                    + "'"+t.getDate_ajout()+"',"
                    + "'"+t.getId_cat()
                    +"')";
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
                    + "`id_cat`='"+modifier.getId_cat()+"' "
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
            
            String query="SELECT * FROM `reclamation`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
                e.setId_cat(rs.getInt("id_cat"));
               
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
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
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
}
