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
    private int id,id_cat;
    private String titre, description;
    private Date date_ajout;

    public Reclamation() {
    }

    public Reclamation(int id_cat, String titre, String description, Date date_ajout) {
        this.id_cat = id_cat;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
    }

    public Reclamation(int id, int id_cat, String titre, String description, Date date_ajout) {
        this.id = id;
        this.id_cat = id_cat;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
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

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_cat=" + id_cat + ", titre=" + titre + ", description=" + description + ", date_ajout=" + date_ajout + '}';
    }
    
}
