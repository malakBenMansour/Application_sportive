/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
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
import entities.LigneCommande ;
/**
 *
 * @author msi
 */
public class ServiceLigneCommande implements DService<LigneCommande> {
 private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceLigneCommande() {
       cnx=Myconnexion.getInstance().getCnx();
    }
     @Override
    public void ajouterpst(LigneCommande t) {
         String req = "insert into LigneCommande (idp,idc,quantite) values (?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,  t.getIdp());
            pst.setInt(2, t.getIdc());
            pst.setInt(3, t.getQuantite());
            
            pst.executeUpdate();
            System.out.println(" LigneCommande ajoutée avec succés ");

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
        } catch (SQLException ex) {
             System.out.println(" erreur de suppression de la LigneCommande ! "); 
        }
    }

    @Override
    public void modifier(int id_amodifier, LigneCommande t) {
          /* String req = "UPDATE LigneCommande SET idp=?,idc=?,quantite=? where idc=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getIdp());
            pst.setInt(2, t.getIdc());
            pst.setInt(3, t.getQuantite());
            pst.setInt(4, id_amodifier);
            
            pst.executeUpdate();
        } catch (SQLException ex) { 
             System.out.println("erreur de modification de la LigneCommande ");
        }*/
    }

    @Override
    public List<LigneCommande> afficher() {
       
       return null ;
    }

    @Override
    public List<LigneCommande> afficherById(int idc) {
         List<LigneCommande> LC =new ArrayList<>();
        try {
            
            st=cnx.createStatement();
            
            String query="SELECT * FROM LigneCommande WHERE idc="+idc;
            rs=st.executeQuery(query);
            while(rs.next()){
                LigneCommande L = new LigneCommande();
               
                L.setIdp(rs.getInt("idp"));
                L.setIdc(rs.getInt("idc"));
                L.setQuantite(rs.getInt("quantite"));
              
                LC.add(L); 
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage by id dans la LigneCommande ");        }
        return LC ;
      
    }

  public List<LigneCommande> afficherpdt_cmd (int idc)
  {
  List<LigneCommande> LC =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT LigneCommande.*,produit.nom_p  FROM LigneCommande,produit where LigneCommande.idp=produit.idp AND idc="+idc+" ";
            rs=st.executeQuery(query);
            while(rs.next()){
                LigneCommande L =new LigneCommande();
                
                L.setIdp(rs.getInt("idp"));
                L.setIdc(rs.getInt("idc"));
                L.setQuantite(rs.getInt("quantite"));
                L.setNom_p(rs.getString("nom_p"));
                
                
                LC.add(L);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LC;
    
  }
   
    
}
