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
public class Sponsor {
    private int id;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String adresse;
    private int tel;
    private String mail;
    private int idp;
public Sponsor(int l)
{
    
}
    public Sponsor(String nom) {
        this.nom = nom;
    }

    public Sponsor(int id, String nom, String prenom, Date datenaissance, String adresse, int tel, String mail,int idp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.idp=idp;
    }

    public Sponsor() {
    }

    public Sponsor(String nom, String prenom, Date datenaissance, String adresse, int tel, String mail,int idp) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.idp=idp;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", adresse=" + adresse + ", tel=" + tel + ", mail=" + mail + ", idp=" + idp + '}';
    }

    
    
}
