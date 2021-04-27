/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Ela
 */
public class Feedback {
    
    
    private int id;
    private String description;
    private Date date;
    private int iduser;
    private String offer;

    public Feedback() {
    }

    public Feedback(int id, String description, Date date, int iduser, String offer) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.iduser = iduser;
        this.offer = offer;
    }

    public Feedback(String description, Date date, int iduser, String offer) {
        this.description = description;
        this.date = date;
        this.iduser = iduser;
        this.offer = offer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", description=" + description + ", date=" + date + ", iduser=" + iduser + ",offer=" + offer + '}';
    }
    
    
    
}
