/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class Skills {
    private int id;
    private String libelle;
    private String img;
    private ImageView image1;

    public Skills() {
    }

    public Skills(int id,ImageView image1) {
        this.id=id;
        this.image1=image1;
    }

    public Skills(int id, String libelle, String img, ImageView image1) {
        this.id = id;
        this.libelle = libelle;
        this.img = img;
        this.image1 = image1;
    }

    
     public Skills(int id, String libelle, ImageView image1) {
        this.id = id;
        this.libelle = libelle;
        this.image1= image1;
       
    }
       public Skills(int id, String libelle, String image1) {
        this.id = id;
        this.libelle = libelle;
        this.img= image1;
       
    }
         public Skills( String libelle) {
       
        this.libelle = libelle;
      
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }
    
}
