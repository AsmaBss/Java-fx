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
    private int idoffer;

    public Feedback() {
    }

    public Feedback(int id, String description, Date date, int iduser, int idoffer) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.iduser = iduser;
        this.idoffer = idoffer;
    }

    public Feedback(String description, Date date, int iduser, int idoffer) {
        this.description = description;
        this.date = date;
        this.iduser = iduser;
        this.idoffer = idoffer;
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

    public int getIdoffer() {
        return idoffer;
    }

    public void setIdoffer(int idoffer) {
        this.idoffer = idoffer;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", description=" + description + ", date=" + date + ", iduser=" + iduser + ", idoffer=" + idoffer + '}';
    }
    
    
    
}
