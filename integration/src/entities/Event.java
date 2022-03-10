/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Espace Sboui
 */
public class Event {
    private int id;
    private String nom;
    private  String lieu;
    private Date date;
    private int nbparticipation;
    private String description;
    private float prix;
    private int ids;
    private String image;
    private int idpp;
    public Event() {
    }

   /* public Event(int id, String nom, String lieu, Date date, int nbparticipation, String description, float prix, Sponsor s) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.nbparticipation = nbparticipation;
        this.description = description;
        this.prix = prix;
        this.s = s;
    }

    public Event(String nom, String lieu, Date date, int nbparticipation, String description, float prix, Sponsor s) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.nbparticipation = nbparticipation;
        this.description = description;
        this.prix = prix;
        this.s = s;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return date;
    }

    public int getNbparticipation() {
        return nbparticipation;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

   
    public Sponsor getS() {
        return s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNbparticipation(int nbparticipation) {
        this.nbparticipation = nbparticipation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    public void setS(Sponsor s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", nbparticipation=" + nbparticipation + ", description=" + description + ", prix=" + prix + ", ids=" + ids + ", s=" + s + '}';
    }

    
    
    */

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public Event(int id, String nom, String lieu, Date date, int nbparticipation, String description, float prix, int ids, String image,int idpp) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.nbparticipation = nbparticipation;
        this.description = description;
        this.prix = prix;
        this.ids = ids;
        this.image=image;
        this.idpp=idpp;
    }

    public Event(String nom, String lieu, Date date, int nbparticipation, String description, float prix, int ids,String image,int idpp) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.nbparticipation = nbparticipation;
        this.description = description;
        this.prix = prix;
        this.ids = ids;
        this.image=image;
        this.idpp=idpp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbparticipation() {
        return nbparticipation;
    }

    public void setNbparticipation(int nbparticipation) {
        this.nbparticipation = nbparticipation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIdpp() {
        return idpp;
    }

    public void setIdpp(int idpp) {
        this.idpp = idpp;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", nbparticipation=" + nbparticipation + ", description=" + description + ", prix=" + prix + ", ids=" + ids + ", image=" + image + ", idpp=" + idpp + '}';
    }

    

   
    
}
