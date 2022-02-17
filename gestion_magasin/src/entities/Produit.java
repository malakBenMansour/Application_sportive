/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author dhiabensaada
 */
public class Produit {
    
     private int idp ;
     private String nom_p ;
     private float prix ;

    public Produit() {
    }

    public Produit(int idp, String nom_p, float prix) {
        this.idp = idp;
        this.nom_p = nom_p;
        this.prix = prix;
    }

    public Produit(String nom_p, float prix) {
        this.nom_p = nom_p;
        this.prix = prix;
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

    @Override
    public String toString() {
        return "Produit{" + "idp=" + idp + ", nom_p=" + nom_p + ", prix=" + prix + '}';
    }
  

}
