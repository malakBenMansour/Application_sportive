/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Espace Sboui
 */
public class Reservation {
    private int id;
    private String nom;
    private int idr;
    private String prenom;
    private int age;
    private int idf;
    public Reservation() {
    }

    public Reservation(String nom, int idr) {
        this.nom = nom;
        this.idr=idr;
    }

    public Reservation(int id, String nom, int age,String prenom,int idr,int idf) {
        this.id = id;
        this.nom = nom;
        this.prenom=prenom;
        this.age=age;
        this.idr=idr;
        this.idf=idf;
    }
     public Reservation( String nom, int age,String prenom,int idr,int idf) {
       
        this.nom = nom;
        this.prenom=prenom;
        this.age=age;
        this.idr=idr;
                this.idf=idf;

    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nom=" + nom + ", idr=" + idr + ", prenom=" + prenom + ", age=" + age + ", idf=" + idf + '}';
    }

    
  
   
    
}
