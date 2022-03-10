/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static GUI.main.Userconnected;
import entities.Event;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 *
 * @author Espace Sboui
 */
public class SEvent implements Iservice<Event>{
     Connection cnx=MaConnexion.getInstance().getCnx();
     //Connection conx;
    Statement stm=null;
    
    public void ajouter(Event e)throws SQLException {
       /* String sql ="insert into evenement(nom,lieu,date,nbparticipation,description,prix,ids) values(?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getLieu());
            ste.setDate(3, e.getDate());
            ste.setInt(4, e.getNbparticipation());
            ste.setString(5, e.getDescription());
            ste.setFloat(6, e.getPrix());
            ste.setInt(7, e.getS().getId());
            ste.executeUpdate();
            System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
         String req = "INSERT INTO `evenement` (`nom`,`lieu`,`date`,`nbparticipation`,`description`,`prix`,`ids`,`image`,`idpp` ) VALUES ( '"
                + e.getNom() + "', '" + e.getLieu() + "','" + e.getDate()+ "','" + e.getNbparticipation()+ "','" + e.getDescription()+ "','" + e.getPrix()+ "','" + e.getIds()+ "','" + e.getImage()+ "','"+e.getIdpp()+"') ";
        
             stm = cnx.createStatement();
       stm.executeUpdate(req);
        
    }

    @Override
    public List<Event> afficher() {
        List<Event> ev = new ArrayList<>();
        String sql ="select * from evenement where idpp='"+Userconnected.getId()+"'";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setNbparticipation(rs.getInt("nbparticipation"));
                e.setDescription(rs.getString("description"));
                e.setPrix(rs.getFloat("prix"));
                e.setIds(rs.getInt("ids"));
                 e.setImage(rs.getString("image"));
                 e.setIdpp(rs.getInt("idpp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;
    }

    
   
    public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM evenement WHERE id="+t+";";
   
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
        
    }

       
    public void modifier(Event e) throws SQLException{

        String req="UPDATE evenement SET `nom`='"+e.getNom()+"',`lieu`='"+e.getLieu()+"',`date`='"+e.getDate()+"',`Nbparticipation`='"+e.getNbparticipation()+"',`description`='"+e.getDescription()+"',`prix`='"+e.getPrix()+"',`ids`='"+e.getIds()+"',`image`='"+e.getImage()+"' WHERE `id`='"+e.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
    }
    
    
    public List<Event> rechercheparNom(String nom)
    {
        
        List<Event> ev = new ArrayList<>();
        String sql ="select * from evenement where nom ='"+nom+"'";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setNbparticipation(rs.getInt("nbparticipation"));
                e.setDescription(rs.getString("description"));
                e.setPrix(rs.getFloat("prix"));
                  e.setImage(rs.getString("image"));
                  e.setIdpp(rs.getInt("idpp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;   
    }
    
    
     public List<Event> TRIparNom()
    {
        
        List<Event> ev = new ArrayList<>();
        String sql ="select * from evenement ORDER BY nom";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setNbparticipation(rs.getInt("nbparticipation"));
                e.setDescription(rs.getString("description"));
                e.setPrix(rs.getFloat("prix"));
                e.setImage(rs.getString("image"));
                e.setIdpp(rs.getInt("idpp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;     
        
    }
     
     
     public String TotalEvent()
    {
        
       String x=null;
        String sql ="select count(*) from evenement";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
             x=rs.getString(1);
             System.out.println("total event "+x);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
return x;
        
    }
     
     public List<Integer> afficherid()  {
        List<Integer> Quiz = new ArrayList<>();
        try {
            String req = "SELECT id FROM evenement ";
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
     
     
     
     public String MinEvent()
    {
        //List<Event> ev = new ArrayList<>();
       String x=null;
        String sql ="select nom from evenement where prix in (select min(prix) from evenement)";
        try {
             Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
             x=rs.getString(1);
             System.out.println("min event "+x);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x; 
        
    }
    /* public boolean updateDate(Sponsor p, String newValue) {
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
   
}*/
      public boolean updateNom(Event p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update evenement set nom=? where id = ? ");
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
       public boolean updateLieu(Event p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update evenement set lieu=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Lieu ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
        public boolean updateDescripton(Event p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update sponsor set description=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "description ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
   
}
         public void modifierNB() throws SQLException{

        String req="UPDATE evenement E join reservation R SET `Nbparticipation`=Nbparticipation-1 where E.id=R.idr ;";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
    }
      

   public boolean test()
   {
       
       String x=null;
       Boolean t = false;
       
       java.util.Date date=new java.util.Date();
       java.sql.Date sqlDate=new Date(date.getTime());
       
       
        String sql ="select date from evenement where date='"+sqlDate+"'";
        try {
             Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
             x=rs.getString(1);
             System.out.println("min event "+x);
             t=true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
   }
   public String test1(Event e)
   {
       
     String x = null;
       
       java.util.Date date=new java.util.Date();
       java.sql.Date sqlDate=new Date(date.getTime());
      
       
        String sql ="select DATEDIFF((select date from evenement where id='"+e.getId()+"'),'"+sqlDate+"')";
        
        try {
             Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
             x=rs.getString(1);
             System.out.println("event date "+x);
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
   }
   
   public List<Event> afficherE() {
        List<Event> ev = new ArrayList<>();
        java.util.Date date=new java.util.Date();
       java.sql.Date sqlDate=new Date(date.getTime());
        String sql ="select * from evenement where date >='"+sqlDate+"'";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setNbparticipation(rs.getInt("nbparticipation"));
                e.setDescription(rs.getString("description"));
                e.setPrix(rs.getFloat("prix"));
                e.setIds(rs.getInt("ids"));
                e.setImage(rs.getString("image"));
                e.setIdpp(rs.getInt("idpp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;
    }
   
    public List<Event> afficherFan() {
        List<Event> ev = new ArrayList<>();
        String sql ="select * from evenement ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setNbparticipation(rs.getInt("nbparticipation"));
                e.setDescription(rs.getString("description"));
                e.setPrix(rs.getFloat("prix"));
                e.setIds(rs.getInt("ids"));
                 e.setImage(rs.getString("image"));
                 e.setIdpp(rs.getInt("idpp"));
                ev.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;
    }
}
