/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import static GUI.main.Userconnected;
import entities.Sponsor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 *
 * @author Espace Sboui
 */
public class SSponsor implements Iservice<Sponsor> {
    Connection cnx=MaConnexion.getInstance().getCnx();
     
    public void ajouter(Sponsor s) {
        String sql ="insert into sponsor(nom,prenom,datenaissance,adresse,tel,mail,idp) values(?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, s.getNom());
            ste.setString(2, s.getPrenom());
            ste.setDate(3, s.getDatenaissance());
            ste.setString(4, s.getAdresse());
            ste.setInt(5, s.getTel());
            ste.setString(6, s.getMail());
            ste.setInt(7, s.getIdp());
            ste.executeUpdate();
            System.out.println("sponsor ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Sponsor> afficher() {
        List<Sponsor> sp = new ArrayList<>();
        String sql ="select * from sponsor where idp='"+Userconnected.getId()+"'";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Sponsor e = new Sponsor();
                System.out.println("mcheee");
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDatenaissance(rs.getDate("datenaissance"));
                e.setAdresse(rs.getString("adresse"));
                e.setTel(rs.getInt("tel"));
                e.setMail(rs.getString("mail"));
                e.setIdp(rs.getInt("idp"));
                sp.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sp;
    }
public List<Integer> afficherid()  {
        List<Integer> Quiz = new ArrayList<>();
        try {
            String req = "SELECT id FROM sponsor ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Quiz.add(rs.getInt(1));
        
    }
            } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Quiz;
        }
 public int getid() throws SQLException  {
      String sql= "select  id  from sponsor";
     int x=0;
      try {
    Statement ste=cnx.createStatement();
    ResultSet rs =ste.executeQuery(sql);

while(rs.next()){
x=rs.getInt(1);
     } }catch (SQLException ex)
{
System.out.println(ex.getMessage());
}
      return x;
 }
public List<String> affichernom()  {
        List<String> Quiz = new ArrayList<>();
        try {
            String req = "SELECT nom FROM sponsor ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Quiz.add(rs.getString(1));
        
    }
            } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return Quiz;
        }
public List<Sponsor> rechercherr(Sponsor r) throws SQLException {
        List<Sponsor> recette = afficher();
        return recette.stream().filter(b -> b.getNom().equals(r.getNom())).collect(Collectors.toList());
}
public List<Sponsor> TRIparNom()
    {
        
        List<Sponsor> ev = new ArrayList<>();
        String sql ="select * from sponsor ORDER BY nom";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Sponsor e = new Sponsor();
                 e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDatenaissance(rs.getDate("datenaissance"));
                e.setAdresse(rs.getString("adresse"));
                e.setTel(rs.getInt("tel"));
                e.setMail(rs.getString("mail"));
                e.setIdp(rs.getInt("idp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;     
        
    }
public List<Sponsor> TRIparAdresse()
    {
        
        List<Sponsor> ev = new ArrayList<>();
        String sql ="select * from sponsor ORDER BY adresse";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Sponsor e = new Sponsor();
                 e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDatenaissance(rs.getDate("datenaissance"));
                e.setAdresse(rs.getString("adresse"));
                e.setTel(rs.getInt("tel"));
                e.setMail(rs.getString("mail"));
                e.setIdp(rs.getInt("idp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;     
        
    }
    
    /*public void supprimer(Sponsor s) {
        String requete = "DELETE FROM sponsor WHERE id=?";
        try {
            
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,s.getId());
            pst.executeUpdate();
            System.out.println("sponsor supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }*/
    public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM sponsor WHERE id='"+t+"';";
   
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
        
    }
    /*public void update(Sponsor s) {
        
    String sql="update sponsor set  nom=?, prenom= ?, datenaissance=?, adresse=?, tel=? , mail=? where id='"+s.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, s.getNom());
            ste.setString(2, s.getPrenom());
            ste.setDate(3, s.getDatenaissance());
            ste.setString(4, s.getAdresse());
            ste.setInt(5, s.getTel());
            ste.setString(6, s.getMail());
            
           ste.executeUpdate();
            System.out.println("sponsor Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}*/
    public void modifier(Sponsor s) throws SQLException{

        String req="UPDATE sponsor SET `nom`='"+s.getNom()+"',`prenom`='"+s.getPrenom()+"',`datenaissance`='"+s.getDatenaissance()+"',`adresse`='"+s.getAdresse()+"',`tel`='"+s.getTel()+"',`mail`='"+s.getMail()+"',`idp`='"+s.getIdp()+"' WHERE `id`='"+s.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
    }
     public boolean updateDate(Sponsor p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set datenaissance=? where id = ? ");
            if (newValue.matches("[1-9]+")) {
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
      public boolean updateNom(Sponsor p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set nom=? where id = ? ");
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
       public boolean updatePrenom(Sponsor p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set prenom=? where id = ? ");
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
        public boolean updateAdresse(Sponsor p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set adresse=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "L'adresse ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
         public boolean updateMail(Sponsor p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set mail=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Mail ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}



}
