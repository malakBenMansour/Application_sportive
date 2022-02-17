/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import utils.MaConnexion;
import entites.Administrateur;
import entites.Fan;
import entites.Personne;
import entites.Responsable;
import java.sql.Date;
import Services.PersonneService;

/**
 *
 * @author malak_6
 */
public class MainClass {
    public static void main(String[] args)
    {
        MaConnexion mc=MaConnexion.getInstance();
        String str="2015-12-03";
        Date d= Date.valueOf(str);
       
        Personne p1= new Personne("ben mansour","malak",d,"tunis","malak@esprit.tn",123456,"admin","malak2001","");
        Administrateur a1=new Administrateur("benzarti","aziz",d,"sousse","aziz@esprit.tn",456789,"admin","aziz123","");
         
         Responsable r1=new Responsable("xx","yy",d,"ariana","x@gmail.com",97896,"responsable","mdp","EST");
         Fan f1=new Fan("client","foulen",d,"gabes","foulen@html.com",4789,"fan","78zz","");
         Responsable r2=new Responsable("RESPO MM","BB",d,"paris","RESPO@gmail.com",77776,"responsable","mdp","Club Africain");
          PersonneService us = new PersonneService();
         
          Fan f3=new Fan("client","foulen",d,"gabes","foulen@html.com",4789,"admin","78zz","mm"); // test si role =admin et nomEquipe existe meme si FAN
          
          try {
           
              // us.ajouter(f3);
             //us.ajouter(r2);
             //us.ajouter(a1);
             //us.ajouter(r1);
            // us.ajouter(f1);
            r2.setNom("malouka");
            r2.setId(18);
            //r2.setPrenom("louka");
            r1.setId(13);
            //f1.setId(22);
            //f1.setId(23);
            //r1.setId(18);
            //r1.setNom("zazzou");
            us.modifier(r2);
           
           
          //us.modifier(r2);
          // us.supprimer(20);
           //us.supprimer(21);
            //us.supprimer(3);
              System.out.println(us.afficher());
            
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
          
          
          
     
        
    }
    
}
