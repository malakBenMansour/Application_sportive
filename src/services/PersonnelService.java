/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import utils.MyDB;
/**
 *
 * @author Aziz
 */
public class PersonnelService implements IPersonnel<Personnel> {

    Connection connexion;
    Statement stm;

    public PersonnelService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterp(Personnel p) throws SQLException {
        String req = "INSERT INTO `personnel` (`cin`,`nom`, `prenom`,`datenaissance`,`adresse`,`mail`,`tel`,`salaire`,`image`,`sport`,`categorie`,`role`) VALUES ('" + p.getCin() + "', '"
                + p.getNom() + "', '" + p.getPrenom() + "','" + p.getDatenaissance()+ "','" + p.getAdresse()+ "','" + p.getMail()+ "','" + p.getTel()+ "','" + p.getSalaire()+ "','" + p.getImage()+ "','" + p.getSport() + "','" + p.getCategorie() + "','" + p.getRole()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    
    public List<Personnel> afficherpersonne() throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
          
            Personnel p = new Personnel(rst.getInt("cin"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                      rst.getString("image"),
                    rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
    public List<Personnel> afficherperid() throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
          
            Personnel p = new Personnel(rst.getInt("id"),
                    rst.getInt("cin"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                                          rst.getString("image"),

                    rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
    public List<String> affichernom()  {
        List<String> Quiz = new ArrayList<>();
        try {
            String req = "SELECT nom FROM personnel ";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Quiz.add(rs.getString(1));
        
    }
            } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Quiz;
        }
    public List<Integer> affichecin()  {
        List<Integer> Quiz = new ArrayList<>();
        try {
            String req = "SELECT cin FROM personnel ";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Quiz.add(rs.getInt(1));
        
    }
            } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Quiz;
        }
    public List<Personnel> rechercherr(Personnel r) throws SQLException {
        List<Personnel> recette = afficherperid();
        return recette.stream().filter(b -> b.getNom().equals(r.getNom())).collect(Collectors.toList());
}
/*public List<Personnel> rechercher(Personnel r) throws SQLException {
        List<Personnel> recette = afficherpersonne();
        return recette.stream().filter(b -> b.getCin()==r.getCin()).collect(Collectors.toList());
}*/
    public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM personnel WHERE id="+t+";";
   
        Statement st=connexion.createStatement();
        st.executeUpdate(req);

    }
     public void modifier(Personnel t) throws SQLException{
       String req="UPDATE `personnel` SET `nom`='"+t.getNom()+"',`prenom`='"+t.getPrenom()+"',`datenaissance`='"+t.getDatenaissance()+"',`adresse`='"+t.getAdresse()+"',`mail`='"+t.getMail()+"',`tel`='"+t.getTel()+"',`salaire`='"+t.getSalaire()+"',`image`='"+t.getImage()+"',`sport`='"+t.getSport()+"',`categorie`='"+t.getCategorie()+"',`role`='"+t.getRole()+"' WHERE `id`='"+t.getId()+"';";
        Statement st=connexion.createStatement();
        st.executeUpdate(req);
     
     
     
   }
     
     public List<Personnel> rechercheParNom(String nom) throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel where `nom`='"+nom+"' ";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
          
            Personnel p = new Personnel(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                                        rst.getString("image"),

                    rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
     public List<Personnel> rechercheParprenom(String prenom) throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel where `nom`='"+prenom+"' ";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
         
            Personnel p = new Personnel(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                                                            rst.getString("image"),

                    rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
     
       public List<Personnel> rechercheParSalaire(int salaire) throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel where `salaire`='"+salaire+"' ";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
        
            Personnel p = new Personnel(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                                                            rst.getString("image"),

                    rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
         public List<Personnel> TriParNom() throws SQLException {
        List<Personnel> presonnels = new ArrayList<>();
        String req = "select * from personnel ORDER BY nom asc ";
       Personnel p1= new Personnel ();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
         
            Personnel p = new Personnel(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getDate("datenaissance"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getInt("tel"),
                    rst.getFloat("salaire"),
                                                            rst.getString("image"),

                     rst.getString("sport"),
                    rst.getString("categorie"),
                    rst.getString("role")
                
            );
            presonnels.add(p);
        }
        return presonnels;
    }
    
         public String totalJoueurs()throws SQLException
         {
             
             String req="Select count(*) from personnel where role='"+"joueur"+"'";
             
        String x=null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             System.out.println("total joueurs  "+x);
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
         }
         
         
         public String totalEntraineurs()throws SQLException
         {
             
             String req="Select count(*) from personnel where role='"+"entraineur"+"'";
             
        String x=null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             System.out.println("total entraineurs  "+x);
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
         }
         
      /* public String getId(Personnel p) throws SQLException
       {
           
          String req="Select `id` from personnel where `nom` like '"+p.getNom()+"'";
             
     String x = null;     
        
        try
    {     Statement ste= connexion.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             x = rs.getString(1);
             
             
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        
           return x;  
       
           
       }*/
       public int getid(Personnel p) throws SQLException  {
      String sql= "select  id from personnel where `nom` like '"+p.getNom()+"'";
     int x=0;
      try {
    Statement ste=connexion.createStatement();
    ResultSet rs =ste.executeQuery(sql);

while(rs.next()){
x=rs.getInt(1);
     } }catch (SQLException ex)
{
System.out.println(ex.getMessage());
}

return x;
       }
        public boolean updateNom(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update personnel set nom=? where id = ? ");
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
           public boolean updatePrenom(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update personnel set prenom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
              public boolean updatemail(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update personnel set mail=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
                 public boolean updateAdresse(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update personnel set adresse=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le adresse peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
                    public boolean upsport(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update personnel set sport=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
                       public boolean updatecategorie(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update categorie set nom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
       public boolean updaterole(Personnel p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = connexion.prepareStatement("update role set nom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le role ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
        
}

