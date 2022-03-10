/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dell
 */
public class Actualite {
    private int id;
    private String titre, description,image;
    private Date date_ajout;
    private ImageView img;
    private int idres;
    public Actualite() {
    }

    public Actualite(String titre, String description, Date date_ajout,int idres) {
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.idres=idres;
    }

    public Actualite(int id, String titre, String description, Date date_ajout,int idres) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
            this.idres=idres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Actualite(String titre, String description, String image, Date date_ajout,int idres) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.date_ajout = date_ajout;
             this.idres=idres;
    }

    public Actualite(int id, String titre, String description, String image, Date date_ajout,int idres) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.date_ajout = date_ajout;
             this.idres=idres;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getIdres() {
        return idres;
    }

    public void setIdres(int idres) {
        this.idres = idres;
    }

    @Override
    public String toString() {
        return "Actualite{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + ", date_ajout=" + date_ajout + ", img=" + img + ", idres=" + idres + '}';
    }

    

    

    

    

    

    
}
