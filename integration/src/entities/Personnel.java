/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Aziz
 */
public class Personnel {
   protected int id;
    protected String nom,prenom,adresse,mail,role;
    protected int tel;
    protected Date datenaissance ; 
    protected float salaire ; 
    protected String sport;
    protected String categorie;
    protected String image;
    protected int cin;
    
    protected int idrespo;
    /*public enum sport {
        football,basketball,handball
    } 
    public enum categorie {
        cadet,junior,senior,espoir
    
    }
    private sport sport;
    private categorie categorie;
    public Personnel ()
    {
    
    }*/

    public Personnel() {
    }
public Personnel(String nom) {
        this.nom = nom;
    }
  public Personnel(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Personnel(int id, int cin, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, float salaire,String image, String sport, String categorie, String role,int idrespo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.role = role;
        this.tel = tel;
        this.image = image;
        this.datenaissance = datenaissance;
        this.salaire = salaire;
        this.sport = sport;
        this.categorie = categorie;
        this.cin = cin;
        this.idrespo=idrespo;
    }

    public Personnel(int cin, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, float salaire,String image, String sport, String categorie, String role,int idrespo) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
       this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.mail = mail;
        
        this.tel = tel;
        this.image=image;
        this.salaire = salaire;
        this.sport = sport;
        this.categorie = categorie;
        this.role = role;
        this.idrespo=idrespo;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdrespo() {
        return idrespo;
    }

    public void setIdrespo(int idrespo) {
        this.idrespo = idrespo;
    }

    @Override
    public String toString() {
        return "Personnel{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", mail=" + mail + ", role=" + role + ", tel=" + tel + ", datenaissance=" + datenaissance + ", salaire=" + salaire + ", sport=" + sport + ", categorie=" + categorie + ", image=" + image + ", cin=" + cin + ", idrespo=" + idrespo + '}';
    }

   

  

  

   
   
}
    



