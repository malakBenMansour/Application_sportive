/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_sport;

import entities.Actualite;
import entities.Categorie;
import entities.Reclamation;
import java.sql.Date;
import java.time.LocalDate;
import services.ActualiteService;
import services.CategorieService;
import services.ReclamationService;

/**
 *
 * @author dell
 */
public class Connect_sport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date d =(Date) Date.valueOf(LocalDate.now());
         Categorie c = new Categorie("Probleme Technique");
         Actualite a = new Actualite("Probleme Tech","jai trouve un probleme au niveau",d);
         Reclamation r = new Reclamation(1,"Probleme","niveau",d);
         CategorieService cs= new CategorieService();
          ActualiteService es= new ActualiteService();
          ReclamationService rs= new ReclamationService();
         // rs.ajouter(r);
          es.ajouter(a);
       //  ActualiteService as= new ActualiteService();
        // cs.ajouter(c);
        //cs.modifier(1, c);
   c=cs.afficher_ById(1);
        System.out.println(c);
    }
    
}
