package edu.esprit.entities;

public class Category {

    private int id;
    private String title;

    public Category(){
    }
    
    public Category(int id, String title){
        this.id = id;
        this.title = title;
    }
   
    public Category(String title){
        this.title = title;
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS & SETTERS">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return title;
    }

    public void setLibelle(String libelle) {
        this.title = libelle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    //</editor-fold>

    @Override
    public String toString() {
        return title;
    }
    
    
}
