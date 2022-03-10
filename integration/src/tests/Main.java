/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Event;
import entities.Reservation;
import entities.Sponsor;
import services.SEvent;
import services.SReservation;
import services.SSponsor;
import tools.MaConnexion;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Espace Sboui
public class Main {

    /**
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Date d= Date.valueOf("2022-02-17");
        // TODO code application logic here
        //Date d= Date.valueOf("2022-05-3");
        // String str="2016-14-03";
        //Date d= Date.valueOf(str);
        MaConnexion cnx=MaConnexion.getInstance();
       //  Sponsor sp=new Sponsor(8, "ikbel", "bouzezi", d, "esprit", 123456789, "123@gmail.com");
          SSponsor ssp=new SSponsor();
        
       // ssp.ajouter(sp);
      //  Event ev2=new Event("sousou", "5544", d, 99, "syd", 13.5f,8,"image");
        //Event ev1=new Event(3,"111", "esprit", d, 15, "piwpiwpiw", 15.2f,sp);
       // Event ev3=new Event(4,"111", "esprit", d, 15, "piwpiwpiw", 15.2f);
       // Event ev4=new Event(4,"15", "esprit", d, 155, "piwpiw", 20.2f);
       // Event ev5=new Event(4,"188", "espt", d, 15885, "iw", 990.2f);
       // Event ev6=new Event(8,"188596", "espt", d, 15885, "iw", 90.2f);
    SEvent se=new SEvent();
    //ev3.setNom("malak");
   // ev4.setId(5);
    //ev4.setNom("ikbel");
    //ev4.setLieu("ariana");
   //se.ajouter(ev2);
    //se. supprimer(8);
    //se.update(ev2);
     //se.modifier(ev4);
     
    System.out.println(se.rechercheparNom("samar"));
    
    
    /*reservation*/
        System.out.println(ssp.afficherid());
    
        //Reservation rs=new Reservation("sssss");
        //Reservation rs1=new Reservation(1,"ffff");
        //Reservation rs2=new Reservation(1,"mmmm");
        SReservation sr=new SReservation();
        //sr. supprimer(2);
        //sr.ajouter(rs2);
        //sr.ajouter(rs1);
        //sr.ajouter(rs);
        //rs.setNom("malak");
        //rs.setId(5);
        //sr.modifier(rs);
        //sr.update(rs1);
        //sr.supprimer(rs1);
        //System.out.println(sr.afficher());
        
        
        
        /*sponsor*/
        
        
        //Sponsor sp=new Sponsor(1, "ikbel", "bouzezi", d, "esprit", 123456789, "123@gmail.com");
        //Sponsor sp1=new Sponsor(4, "malak", "bm", d, "esp", 123459, "13@gmail.com");
       // SSponsor ssp=new SSponsor();
        
        //ssp.ajouter(sp1);
       // sp.setPrenom("aziz");
       // sp.setNom("dhia");
        //sp.setId(3);
       // ssp.modifier(sp);
        //ssp.update(sp);
        //ssp.supprimer(4);
        //System.out.println(ssp.afficher());
    }

   
    
}
