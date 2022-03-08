/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import entities.Personnel;

/**
 *
 * @author Aziz
 */
public interface IContrat<T> {
    
    public void ajouterc(T p) throws SQLException;
    public List<T> afficherc() throws SQLException;
    public void supprimerc(int t) throws SQLException;
    public void modifierc(T t) throws SQLException;

}
