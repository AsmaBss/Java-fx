/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.interfaces;

import java.util.List;

/**
 *
 * @author Ela
 * @param <T>
 */
public interface IComplaint <T> {
    
    public void addComplaint(T t);
     public void removeComplaint(T t);
     public void updateComplaint(T t);
     public List<T> complaintsList();

}
