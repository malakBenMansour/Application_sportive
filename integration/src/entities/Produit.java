/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author dhiabensaada
 */
public class Produit {
    
     private int idp ;
     private String nom_p ;
     private float prix ;
     private String categorie ;
     private int idm ;
     private String magasin ;
     private String image ;
     private ImageView imv ;
     private int idperso;
    public Produit() {
    }

    public Produit(int idp, String nom_p, float prix, String categorie, int idm, String magasin,int idperso) {
        this.idp = idp;
        this.nom_p = nom_p;
        this.prix = prix;
        this.categorie = categorie;
        this.idm = idm;
        this.magasin = magasin;
        this.idperso=idperso;
    }

    public Produit(int idp, String nom_p, float prix, String categorie ,int idm,int idperso) {
        this.idp = idp;
        this.nom_p = nom_p;
        this.prix = prix;
        this.categorie=categorie;
        this.idm=idm ;
        this.idperso=idperso;
    }

    public Produit(String nom_p, float prix,String categorie,int idm,String image,int idperso) {
        this.nom_p = nom_p;
        this.prix = prix;
        this.categorie=categorie;
         this.idm=idm ;
         this.image=image;
         this.idperso=idperso;
         
         
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

   
    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImv() {
        return imv;
    }

    public void setImv(ImageView imv) {
        this.imv = imv;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idp;
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
        final Produit other = (Produit) obj;
        if (this.idp != other.idp) {
            return false;
        }
        return true;
    }

    public int getIdperso() {
        return idperso;
    }

    public void setIdperso(int idperso) {
        this.idperso = idperso;
    }

    @Override
    public String toString() {
        return "Produit{" + "idp=" + idp + ", nom_p=" + nom_p + ", prix=" + prix + ", categorie=" + categorie + ", idm=" + idm + ", magasin=" + magasin + ", image=" + image + ", imv=" + imv + ", idperso=" + idperso + '}';
    }

   

   

   

   

   

  

}
