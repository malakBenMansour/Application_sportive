/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Aziz
 */
public interface IPersonnel<T> {
    
    public void ajouterp(T p) throws SQLException;
    public List<T> afficherpersonne() throws SQLException;
    public void supprimer(int t) throws SQLException;
    public void modifier(T t) throws SQLException;

}