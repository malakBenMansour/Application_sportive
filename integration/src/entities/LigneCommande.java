/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author msi
 */
public class LigneCommande {
  
    private int idp ;
    private int idc ;
    private int quantite ;
    private String nom_p ;

    public LigneCommande(  int idp,  int idc ,int quantite) {
       
        this.idp = idp;
        this.idc = idc;
        this.quantite = quantite;
    }


    public LigneCommande() {
    }

   

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

   

   

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "idp=" + idp + ", idc=" + idc + ", quantite=" + quantite + ", nom_p=" + nom_p + '}';
    }
    


  
    
}
