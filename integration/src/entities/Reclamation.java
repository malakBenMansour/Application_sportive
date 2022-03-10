/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;



/**
 *
 * @author dell
 */
public class Reclamation {
    private int id,id_cat,id_per;
    private String titre, description;
    private Date date_ajout;
    private String etat;
    private String num_commande;
    //private String nom_personne;
   private String categorie;


    public Reclamation(int id, String titre, String description, Date date_ajout, String etat, String num_commande, String categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.etat = etat;
        this.num_commande = num_commande;
        //this.nom_personne = nom_personne;
        this.categorie = categorie;
    }

    /*public String getNom_personne() {
        return nom_personne;
    }

    public void setNom_personne(String nom_personne) {
        this.nom_personne = nom_personne;
    }
*/
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public Reclamation() {
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNum_commande() {
        return num_commande;
    }

    public void setNum_commande(String num_commande) {
        this.num_commande = num_commande;
    }

    public Reclamation(int id_cat, int id_per, String titre, String description, Date date_ajout, String etat, String num_commande) {
        this.id_cat = id_cat;
        this.id_per = id_per;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.etat = etat;
   
        this.num_commande = num_commande;
    }

    public Reclamation(int id, int id_cat, int id_per, String titre, String description, Date date_ajout, String etat, String num_commande) {
        this.id = id;
        this.id_cat = id_cat;
        this.id_per = id_per;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.etat = etat;
        this.num_commande = num_commande;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_per() {
        return id_per;
    }

    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }
/*
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_cat=" + id_cat + ", id_per=" + id_per + ", titre=" + titre + ", description=" + description + ", date_ajout=" + date_ajout + ", etat=" + etat + ", num_commande=" + num_commande + ", nom_personne=" + nom_personne + ", categorie=" + categorie + '}';
    }

    
*/
    

    
}
