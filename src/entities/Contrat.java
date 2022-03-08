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
public class Contrat {
    private int id ;
    private String nom;
    private Date datecontrat;
    private String type; 
    private int idp;
   
    public Contrat() {
        }

    public Contrat(int id, String nom, Date datecontrat, String type, int idp) {
        this.id = id;
        this.nom = nom;
        this.datecontrat = datecontrat;
        this.type = type;
        this.idp = idp;
    }

    public Contrat(String nom, Date datecontrat, String type, int idp) {
        this.nom = nom;
        this.datecontrat = datecontrat;
        this.type = type;
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

    public Date getDatecontrat() {
        return datecontrat;
    }

    public void setDatecontrat(Date datecontrat) {
        this.datecontrat = datecontrat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", nom=" + nom + ", datecontrat=" + datecontrat + ", type=" + type + ", idp=" + idp + '}';
    }
    
    }
    
   