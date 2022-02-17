/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
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
public class CategorieService implements IService<Categorie> {

    Connection cnx;
    public CategorieService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouter(Categorie t) {
          try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `categorie`(`nom`) VALUES ('"+t.getNom()+"')";
                   
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="DELETE FROM `categorie` WHERE id="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id_amodifier, Categorie modifier) {
         try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `categorie` SET `nom`='"+modifier.getNom()+"' WHERE id="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> afficher() {
         List<Categorie> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `categorie`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Categorie e=new Categorie();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le; 
    }

    
    @Override
    public Categorie afficher_ById(int id) {
       Categorie e=new Categorie();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `categorie` WHERE id="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
}
