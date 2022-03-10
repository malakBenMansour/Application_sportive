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
public class Joueur extends Personnel {

    public Joueur() {
    }

    public Joueur(int id, int cin, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, float salaire, String image, String sport, String categorie, String role,int idrespo) {
        super(id, cin, nom, prenom, datenaissance, adresse, mail, tel, salaire, image, sport, categorie, role,idrespo);
    }

    public Joueur(int cin, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, float salaire, String image, String sport, String categorie, String role,int idrespo) {
        super(cin, nom, prenom, datenaissance, adresse, mail, tel, salaire, image, sport, categorie, role,idrespo);
    }

    
}
