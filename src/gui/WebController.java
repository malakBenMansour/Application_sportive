/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class WebController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private Button load;
    @FXML
    private TextField txtfield;
 private WebEngine engine;
 private double webzoom;
 private WebHistory history;
 private String homepage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine=webview.getEngine();
        homepage="www.google.com";
        txtfield.setText(homepage);
        webzoom=1;
        load(); // TODO
    }    

    @FXML
    private void load() {
         engine.load("http://"+txtfield.getText());
    }
    @FXML
    private void refresh()
    {
    engine.reload();
    
    }
    @FXML
    public void zoomin(){
        webzoom+=0.25;
        webview.setZoom(webzoom);
    }
    @FXML
    public void zoomout(){
        webzoom-=0.25;
          webview.setZoom(webzoom);
    }
    @FXML
    public void dishystory(){
         history=engine.getHistory();
         ObservableList<WebHistory.Entry> entries= history.getEntries();
         for (WebHistory.Entry entry : entries){
             System.out.println(entry.getUrl()+""+entry.getLastVisitedDate());
         }
    }

    @FXML
    private void back() {
           history=engine.getHistory();
         ObservableList<WebHistory.Entry> entries= history.getEntries();
         history.go(-1);
         txtfield.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    @FXML
    private void forward() {
           history=engine.getHistory();
         ObservableList<WebHistory.Entry> entries= history.getEntries();
         history.go(1);
          txtfield.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    @FXML
    private void execute() {
        engine.executeScript("window.location=\"http://www.youtube.com\";");
        
    }
}
