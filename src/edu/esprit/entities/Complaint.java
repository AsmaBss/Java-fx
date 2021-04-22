/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.logging.Logger;

/**
 *
 * @author Ela
 */
public class Complaint {
    
    private int id;
    private String title;
    private String content;
    private int iduser;
    private String status = "Not treated";

    public Complaint() {
    }

    public Complaint(int id, String title, String content, int iduser, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.iduser = iduser;
        this.status = status;
    }

    public Complaint(String title, String content, int iduser, String status) {
        this.title = title;
        this.content = content;
        this.iduser = iduser;
        this.status = status;
    }

    public Complaint(String title, String content, int iduser) {
        this.title = title;
        this.content = content;
        this.iduser = iduser;
    }

    public Complaint(String title, String content) {
        this.title = title;
        this.content = content;
    }
   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", title=" + title + ", content=" + content + ", iduser=" + iduser + ", status=" + status + '}';
    }

    
    
    
    
    
}
