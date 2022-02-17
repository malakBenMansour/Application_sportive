/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author dell
 */
public interface IService <T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(int id_amodifier,T modifier);
    public List<T> afficher();
    public T afficher_ById(int id);
}
