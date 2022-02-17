/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Myconnexion;

/**
 *
 * @author dhiabensaada
 */
public class ServiceProduit implements IService<Produit> {
    

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceProduit() {
       cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
     public void ajouterpst(Produit p) {
       String req = "INSERT into Produit (nom_p,prix) values (?,?)";
        try {
            pst = cnx.prepareStatement(req);
           
            pst.setString(1,p.getNom_p());
            pst.setFloat(2, p.getPrix());
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
         
           String req = "UPDATE Produit SET nom_p=?,prix=? where idp=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom_p());
            pst.setFloat(2, p.getPrix());
            pst.setInt(3, id_amodifier);
            pst.executeUpdate();
        } catch (SQLException ex) { 
             System.out.println("erreur de modification du produit ");
        }
    }

    @Override
    public List<Produit> afficher() {
        List<Produit> lp =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT * FROM  Produit ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Produit p =new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
               
                
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
            
            String query="SELECT * FROM Produit WHERE idp="+id;
            rs=st.executeQuery(query);
            while(rs.next()){
             Produit p = new Produit();
                p.setIdp(rs.getInt("idp"));
                p.setNom_p(rs.getString("nom_p"));
                
                
              
                lp.add(p); 
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage by id dans produit ");        }
        return lp ;
    }
    
}
