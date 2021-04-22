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
public interface IFeedback <T> {
    
    public void addFeedback(T t);
     public void removeFeedback(T t);
     public void updateFeedback(T t);
     public List<T> FeedbacksList();
    
}
