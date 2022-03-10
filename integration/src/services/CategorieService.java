/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static GUI.main.Userconnected;
import entities.Categorie;
import java.sql.Connection;
import java.sql.Date;
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
public class CategorieService implements Service<Categorie> {

    Connection cnx;
    public CategorieService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouter(Categorie t) {
          try {
            Statement st;
            
            st=cnx.createStatement();
            String query="INSERT INTO `categorie`(`nom`,`idpr`) VALUES ('"+t.getNom()+"','"+t.getIdpr()+"')";
                   
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
            String query="UPDATE `categorie` SET `nom`='"+modifier.getNom()+"',`idpr`='"+modifier.getIdpr()+"' WHERE id="+id_amodifier;
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
            
            String query="SELECT * FROM `categorie` where idpr='"+Userconnected.getId()+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Categorie e=new Categorie();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setIdpr(rs.getInt("idpr"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le; 
    }
    public List<String> afficher_cat() {
         List<String> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `categorie` where idpr='"+Userconnected.getId()+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                le.add(rs.getString("nom"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le; 
    }


    public int id_categ(String nom)
    {
        int num=0;
     try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT id FROM `categorie` WHERE (nom='"+nom+"' and idpr='"+Userconnected.getId()+"')";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                num=rs.getInt("id");
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return num;
    }
    @Override
    public Categorie afficher_ById(int id) {
       Categorie e=new Categorie();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `categorie` WHERE (id='"+id+"' and idpr='"+Userconnected.getId()+"')";
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
    public boolean existe_cat(String nom, int id) {
        boolean b=false;
        try {
            Statement st;
            st=cnx.createStatement();
           String query; 
           if(id==0)
           query="SELECT * FROM `categorie` `categorie` WHERE (nom='"+nom+"'and idpr='"+Userconnected.getId()+"')";
           else
           query="SELECT * FROM `categorie` WHERE (titre='"+nom+"'  and id<>"+id+" and idpr='"+Userconnected.getId()+"')";
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
    
    


 public int id_categF(String nom)
    {
        int num=0;
     try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT id FROM `categorie` WHERE nom='"+nom+"' ";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
              
                num=rs.getInt("id");
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return num;
    }
public List<String> afficher_catF() {
         List<String> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `categorie` ";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                le.add(rs.getString("nom"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le; 
    }
}
