/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author malak_6
 */
public interface PersonneInterface <T>{
     void ajouter(T t) throws SQLException;
    List<T> afficher();
    public void modifier(T t) throws SQLException;
    public void supprimer(int t) throws SQLException;
}
