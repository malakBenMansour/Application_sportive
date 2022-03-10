/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;



import entities.Categorie;
import entities.Reclamation;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import services.CategorieService;
import services.ReclamationService;



/**
 * FXML Controller class
 *
 * @author farah
 */
public class ChartController implements Initializable {

    private PieChart piechart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis xAxis;
 private ObservableList<String> monthNames = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieService rs= new CategorieService();
        List<String> le= rs.afficher_cat();
        List<Categorie> lee= rs.afficher();
        String[] cat = new String[le.size()];
        for (int i = 0; i < le.size(); i++) {
           cat[i]=le.get(i);
            
        }
        
        monthNames.addAll(Arrays.asList(cat));
          // Get an array with the FRENCH month names.
        System.out.println(le);
        // Convert it to a list and add it to our ObservableList of months.
      ReclamationService r= new ReclamationService();
        System.out.println(r.afficher());
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        xAxis.setLabel("Catégorie");
        numberAxis.setLabel("Nombre de réclamations");
    }  
       public void setReclamationData(List<Reclamation> t,int a) {
    	// Count the number of reviews in a specific month.
        ReclamationService r= new ReclamationService();
        List<Reclamation> lr=r.afficher();
        CategorieService rs= new CategorieService();
        List<String> le= rs.afficher_cat();
        List<Categorie> lee= rs.afficher();
           System.out.println(lee);
         String[] cat = new String[le.size()];
        for (int i = 0; i < le.size(); i++) {
           cat[i]=le.get(i);
            
        }
        int[] count = new int[le.size()];
           for (int i = 0; i < le.size(); i++) {
               count[i]=0;
               for (int j = 0; j < lr.size(); j++) {
                   if (lee.get(i).getId() == lr.get(j).getId_cat())
                      count[i]++;
               }
               
           }
           System.out.println(count);
        

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < le.size(); i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), count[i]));
        }
        
        barChart.getData().add(series);
    }
    }