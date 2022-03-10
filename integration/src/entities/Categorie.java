/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author dell
 */
public class Categorie {
    private int id;
    private String nom;
    private int idpr;

    public Categorie(String nom,int idpr) {
        this.nom = nom;
        this.idpr=idpr;
    }

    public Categorie() {
    }

   

    public Categorie(int id, String nom,int idpr) {
        this.id = id;
        this.nom = nom;
        this.idpr=idpr;
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

    public int getIdpr() {
        return idpr;
    }

    public void setIdpr(int idpr) {
        this.idpr = idpr;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + ", idpr=" + idpr + '}';
    }

    
    
}
