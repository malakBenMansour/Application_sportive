/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Personne;
import utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malak_6
 */
public class PersonneService implements PersonneInterface <Personne> {
        Connection cnx =MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Personne t) throws SQLException{
        
    String req="INSERT INTO `personne`(`nom`, `prenom`, `datenaissance`, `adresse`, `mail`, `tel`, `role`,`mdp`,`nomEquipe`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getDatenaissance()+"','"+t.getAdresse()+"','"+t.getMail()+"','"+t.getTel()+"','"+t.getRole()+"','"+t.getMdp()+"','"+t.getNomEquipe()+"');";
        Statement st = null;
            
                st = cnx.createStatement();
           
                st.executeUpdate(req);
          
      
    
    }

    @Override
    public List<Personne> afficher() {
       List<Personne> Perso = new ArrayList<>();
        String sql ="select * from personne";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Perso;
    }
    
     public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM `personne` WHERE id="+t+";";
   
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
        
    }
    
    
    
        @Override
   public void modifier(Personne t) throws SQLException{

        String req="UPDATE `personne` SET `nom`='"+t.getNom()+"',`prenom`='"+t.getPrenom()+"',`datenaissance`='"+t.getDatenaissance()+"',`adresse`='"+t.getAdresse()+"',`mail`='"+t.getMail()+"',`tel`='"+t.getTel()+"',`role`='"+t.getRole()+"',`mdp`='"+t.getMdp()+"',`nomEquipe`='"+t.getNomEquipe()+"' WHERE `id`='"+t.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
     
     
     
   }
         
    }
    
    
    
    
    
    
    
    
    
    
        
    

