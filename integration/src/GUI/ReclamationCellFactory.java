/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 *
 * @author dell
 */
public class ReclamationCellFactory implements Callback<ListView<Reclamation>, ListCell<Reclamation>>{

    @Override
    public ListCell<Reclamation> call(ListView<Reclamation> param) {
        return new ListCell<Reclamation>(){
            
            // private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Reclamation rec, boolean empty) {
                //Image img = null;
                super.updateItem(rec, empty);
                if (empty || rec == null) {
                    //  setGraphic(null);
                    setText(null);
                } else {
                    setText(rec.getTitre() + " | " + rec.getDate_ajout().toString() + " | " + rec.getDescription()+ " \nEtat :  " + rec.getEtat());
                   // String imgPath = "C:/Users/dell/Desktop/Java-2021/netbeans/connect_sport/group.png";
            // img = new Image(getClass().getResourceAsStream("\"" + imgPath + "\""));
             // imageView.setImage(img);
             //   setGraphic(imageView);
                }
            }
        };
    }
    
}


