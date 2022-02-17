/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
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
import utils.Myconnexion;
/**
 *
 * @author dhiabensaada
 */
public class ServiceMagasin  implements IService<Magasin> {
    

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceMagasin() {
       cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
     public void ajouterpst(Magasin m) {
       String req = "INSERT into Magasin (nom,adresse,nombrebloc) values (?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
           
            pst.setString(1,  m.getNom());
            pst.setString(2, m.getAdresse());
            pst.setInt(3, m.getNombrebloc());
            
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
            
            String query="SELECT * FROM  Magasin ";
            rs=st.executeQuery(query);
            while(rs.next()){
                Magasin m =new Magasin();
                m.setIdm(rs.getInt("idm"));
                m.setNom(rs.getString("nom"));
                m.setAdresse(rs.getString("adresse"));
                m.setNombrebloc(rs.getInt("nombrebloc"));
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
              
                lm.add(m); 
            }
            
        } catch (SQLException ex) {
            System.out.println(" erreur d'affichage by id dans le magasin ");        }
        return lm ;
    }
}
