/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author dhiabensaada
 */
public class Commande {
          private int idc ;
          private Date date ;
          private String adresse ;
          private float prix ;
          private String nom_p;
          private int idpers ;
         

    public Commande() {
    }

    public Commande(int idc, Date date, String adresse, float prix,int idpers ) {
        this.idc = idc;
        this.date = date;
        this.adresse = adresse;
        this.prix = prix;
        this.idpers=idpers;
        
    }

    public Commande(Date date, String adresse, float prix,int idpers) {
        this.date = date;
        this.adresse = adresse;
        this.prix = prix;
        this.idpers=idpers;
    }

    

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getadresse() {
        return adresse;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idc;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.idc != other.idc) {
            return false;
        }
        return true;
    }

    public int getIdpers() {
        return idpers;
    }

    public void setIdpers(int idpers) {
        this.idpers = idpers;
    }

    @Override
    public String toString() {
        return "Commande{" + "idc=" + idc + ", date=" + date + ", adresse=" + adresse + ", prix=" + prix + '}';
    }

    

   
       
     }

