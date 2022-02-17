/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_magasin;
import entities.Commande;
import entities.Magasin;
import entities.Produit;
import java.sql.Date;
import java.time.LocalDate;
import service.ServiceCommande;
import service.ServiceMagasin;
import service.ServiceProduit;

/**
 *
 * @author msi
 */
public class Gestion_magasin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Date d = (Date) Date.valueOf(LocalDate.now());
         
      Commande c =new Commande (1,d,"tunis",15);
        ServiceCommande Sc =new ServiceCommande();
        Sc.ajouterpst(c);
       
        //Sp.ajouterpst(p);
       // Sp.supprimer(3);
        //System.out.println(Sp.afficherById(2)); 
       // Sp.modifier(1, new Produit("short",20));
       // System.out.println(sm.afficherById(3));
        //System.out.println(sc.afficherById(3));
    }
    
}
