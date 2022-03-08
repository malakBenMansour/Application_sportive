/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import entities.Personnel;
import entities.Joueur;
import entities.Entraineur;
import entities.Staffmedical;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PersonnelService;
import java.sql.Date;
import utils.MyDB;
import entities.Contrat;
import services.ContratService;
/**
 *
 * @author Aziz
 */
public class TestPer {
     
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        System.out.println("aziz");
        MyDB mc=MyDB.getInstance();
        String str="2022-10-02";
        Date d= Date.valueOf(str);
        Personnel p = new Personnel(35,1554477,"aziz","BEN",d,"ghazella","aziz@ben",25365325,4500,"","football" ,"senior","personnel");
        Personnel p2 = new Personnel(1444,"aziz","BEN",d,"ghazella","aziz@ben",25365325,4500,"", "handball","junior","");
        PersonnelService sp = new PersonnelService();
       // Joueur j1=new Joueur(14741,"aalouch","N",d,"ghazella","aziz@en",25365325,400,"",basketball","espoir","joueur");
        Joueur j2=new Joueur(1478,"aalouch","N",d,"ghazella","aziz@en",25365325,400, "","football","junior","joueur");
        Joueur j3=new Joueur(19,"aaa","N",d,"ghazella","aziz@en",25365325,400, "","football","senior","joueur");
        Entraineur e1=new Entraineur(155555,"aaloucha","N",d,"ghazella","aziz@en",25365325,400,  "","football","senior","entraineur");
        Staffmedical s1=new Staffmedical(1451214,"aalilich","N",d,"ghazella","aziz@en",25365325,400,"" ,"","","Staffmedical");
       
        
        Entraineur e5=new Entraineur(19,"fff","mmmm",d,"ghazella","aziz@en",25365325,400,"", "","","entraineur");
        
        Contrat c3=new Contrat("test",d,"contrat",35);
      
      System.out.println(sp.getid(p));
       // Contrat c2=new Contrat("louk",d,"gjyhv","zzz");
        //Contrat c3=new Contrat("louka",d,"");
        ContratService cp = new ContratService();
      PersonnelService c=new PersonnelService();
       // sp.ajouterp(p);
        //System.out.println("ajout succeees");
        //sp.ajouterp(e5);
        //sp.ajouterp(j3);
        //sp.ajouterp(e1);
        //sp.ajouterp(s1);
        
        //cp.ajouterc(c3);
       //cp.ajouterc(c2);
        //cp.ajouterc(c3);
    // System.out.println("ajout avec succes");
        System.out.println(cp.afficherc());
       /* try {
            c2.setNom("zazzou"); 
            c2.setId(2);
            cp.modifierc(c2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
       /* try {
            p.setNom("zazzou"); 
            p.setId(11);
            sp.modifier(p);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
       /* try {
            System.out.println(sp.TriParNom());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
}}
