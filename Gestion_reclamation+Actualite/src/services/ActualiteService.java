/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Actualite;
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
public class ActualiteService implements IService<Actualite> {
 Connection cnx;
    public ActualiteService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouter(Actualite t) {
         try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `actualite`(`titre`, `description`, `dateajout`) VALUES "
                    + "('"+t.getTitre()+"',"
                    + "'"+t.getDescription()+"',"
                    + "'"+t.getDate_ajout()
                    +"')";
            st.executeUpdate(query);
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
                    + "`dateajout`='"+modifier.getDate_ajout()+"' "                    
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
            
            String query="SELECT * FROM `actualite`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Actualite e=new Actualite();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
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
            
            String query="SELECT * FROM `actualite` WHERE id="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setDate_ajout(rs.getDate("dateajout"));
               
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
}
