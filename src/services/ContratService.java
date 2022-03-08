/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Contrat;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyDB;
import java.util.ArrayList;
import java.sql.ResultSet;
import entities.Personnel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 *
 * @author Aziz
 */
public class ContratService implements IContrat<Contrat> {

    Connection connexion;
    Statement stm;

    public ContratService() {
        connexion = MyDB.getInstance().getConnexion();
    }
 @Override
    public void ajouterc(Contrat p) throws SQLException {
          String req = "INSERT INTO contrat ( `nom`, `datecontrat`, `type`, `idp`) VALUES  (  '"
                + p.getNom() + "', '" + p.getDatecontrat() + "','" + p.getType()+ "','" + p.getIdp()+"') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);


    }

    @Override
    public List<Contrat> afficherc() throws SQLException {
List<Contrat> Contrat = new ArrayList<>();
        String sql ="select * from contrat";
        try {
            Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Contrat c = new Contrat();
              //  c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDatecontrat(rs.getDate("datecontrat"));
                  c.setType(rs.getString("type"));
                c.setIdp(rs.getInt("idp"));
                Contrat.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Contrat;
    }

    @Override
    public void supprimerc(int t) throws SQLException {
 String req="DELETE FROM contrat WHERE id="+t+";";
   
        Statement st=connexion.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifierc(Contrat t) throws SQLException {
String req="UPDATE `contrat` SET `nom`='"+t.getNom()+"',`datecontrat`='"+t.getDatecontrat()+"' WHERE `id`='"+t.getId()+"';";
        Statement st=connexion.createStatement();
        st.executeUpdate(req);   
    }
        public boolean updateNom(Contrat p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update contrat set nom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le nom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
          public String totalpro()throws SQLException
         {
             
             String req="Select count(*) from contrat where type='"+"professionnel"+"'";
             
        String x=null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             System.out.println("total contrat proffetionnel  "+x);
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
         }
           public String totaljeun()throws SQLException
         {
             
             String req="Select count(*) from contrat where type='"+"jeune"+"'";
             
        String x=null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             System.out.println("total contrat jeune  "+x);
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
         }
            public String totaldeb()throws SQLException
         {
             
             String req="Select count(*) from contrat where type='"+"debutant"+"'";
             
        String x=null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             System.out.println("total contrat debutant  "+x);
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
         }
}
