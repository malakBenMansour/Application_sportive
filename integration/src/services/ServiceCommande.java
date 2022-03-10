/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static GUI.main.Userconnected;
import entities.Commande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tools.Myconnexion;
/**
 *
 * @author dhiabensaada
 */

public class ServiceCommande implements DService<Commande>{

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceCommande() {
       cnx=Myconnexion.getInstance().getCnx();
    }
    

  //  @Override
   /* public void ajouter(Commande c) {
         try {
            
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `evenement`( `date`, `adresse`, `prix`) VALUES "
                   
                    + "('"+c.getDate()+"',"
                    + "'"+c.getadresse()+"',"
                    + "'"+c.getPrix()+"',"
                    +"')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
             System.out.println(" erreur d'ajout ");
        }
    }*/
    @Override
     public void ajouterpst(Commande c) {
       String req = "insert into Commande (date,adresse,Prix,idpers) values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setDate(1, (Date) c.getDate());
            pst.setString(2, c.getadresse());
            pst.setFloat(3, c.getPrix());
           pst.setInt(4,c.getIdpers());
            
            pst.executeUpdate();
            System.out.println(" commande ajoutée avec succés ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        

        }

    }


    @Override
    public void supprimer(int idc) {
        try {
           
            st=cnx.createStatement();
             String query="DELETE FROM LigneCommande WHERE idc="+idc;
            st.executeUpdate(query);
            
            query="DELETE FROM commande WHERE idc="+idc;
            st.executeUpdate(query);
             System.out.println(" commande supprimée avec succés ");

        } catch (SQLException ex) {
             System.out.println(" erreur de suppression "); 
        }
    }

    @Override
    public void modifier(int id_amodifier, Commande c) {
         
           String req = "UPDATE Commande SET date=?,adresse=?,Prix=? where idc=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setDate(1, (Date) c.getDate());
            pst.setString(2, c.getadresse());
            pst.setFloat(3, c.getPrix());
            
            pst.setInt(4, id_amodifier);
              
            
            pst.executeUpdate();
            System.out.println(" commande modifiée avec succés ");

        } catch (SQLException ex) { 
             System.out.println("erreur de modification");
        }
    }

    @Override
    public List<Commande> afficher() {
        List<Commande> lc =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT commande.*,concat('"+Userconnected.getNom()+"','"+Userconnected.getPrenom()+"') as pers FROM commande ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Commande c =new Commande();
                c.setIdc(rs.getInt("idc"));
                c.setadresse(rs.getString("adresse"));
                c.setDate(rs.getDate("date"));
                c.setPrix(rs.getFloat("prix"));
                c.setNom_p(rs.getString("pers"));
                c.setIdpers(rs.getInt("idpers"));
                
                lc.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage ");
        }
        return lc;
    }
 public List<Commande> afficher_critere(String critere, String col ,String ord) {
        List<Commande> lc =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT commande.*,concat('"+Userconnected.getNom()+"','"+Userconnected.getPrenom()+"') as pers FROM commande where commande.idpers='"+Userconnected.getId()+"'  and (date like '%"+critere+"%' or commande.adresse like '%"+critere+"%' or concat('"+Userconnected.getNom()+"','"+Userconnected.getPrenom()+"') like '%"+critere+"%') order by "+col+" "+ord+"                  ";
            System.out.println(query);
            rs=st.executeQuery(query);
            while(rs.next()){
                Commande c =new Commande();
                c.setIdc(rs.getInt("idc"));
                c.setadresse(rs.getString("adresse"));
                c.setDate(rs.getDate("date"));
                c.setPrix(rs.getFloat("prix"));
                c.setNom_p(rs.getString("pers"));
                c.setIdpers(rs.getInt("idpers"));
                
                lc.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lc;
    }

    @Override
    public List<Commande> afficherById(int id) {
        List<Commande> lc =new ArrayList<>();
        try {
            
            st=cnx.createStatement();
            
            String query="SELECT * FROM commande WHERE idc="+id;
            rs=st.executeQuery(query);
            while(rs.next()){
                Commande c =new Commande();
                c.setIdc(rs.getInt("idc"));
                c.setadresse(rs.getString("adresse"));
                c.setDate(rs.getDate("date"));
                c.setPrix(rs.getFloat("prix"));
                 
              
                lc.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
        return lc;
    }

   
    
}
