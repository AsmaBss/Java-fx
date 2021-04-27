/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author bensa
 */
public class Evenements {
    
    private int id,nbr_participants,nbr_max,id_emp;
    private String nom,adresse,titre;
    private Date date;
    private String img;
    private ImageView image1;

    
     private ImageView image2;
      private String address;
  
     public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }

    public Evenements() {
        this.nbr_participants=0;
    }

    public Evenements(int id,Date date,String adresse,String titre, String nom,int nbr_participants,int nbr_max,ImageView image1) {
        this.id = id;
        this.nbr_max=nbr_max;
        this.nbr_participants=nbr_participants;
        this.nom = nom;
        this.titre = titre;
        this.adresse=adresse;
        this.date=date;
        this.image1 = image1;

        
    }
    
    public Evenements(int id,Date date,String adresse,String titre, String nom,int nbr_participants,int nbr_max,ImageView image1,int id_emp) {
        this.id = id;
        this.nbr_max=nbr_max;
        this.nbr_participants=nbr_participants;
        this.nom = nom;
        this.titre = titre;
        this.adresse=adresse;
        this.date=date;
        this.image1 = image1;
         this.id_emp = id_emp;


        
    }
    //affichage with user
     public Evenements(int id,Date date,String adresse,String titre, String nom,int nbr_participants,int nbr_max,ImageView image1,int id_emp, ImageView image2, String address) {
        this.id = id;
        this.nbr_max=nbr_max;
        this.nbr_participants=nbr_participants;
        this.nom = nom;
        this.titre = titre;
        this.adresse=adresse;
        this.date=date;
        this.image1 = image1;
        this.address=address;
         this.id_emp = id_emp;


        
    }
     public Evenements(int id,Date date,String adresse,String titre, String nom,int nbr_participants,int nbr_max,String img) {
        this.id = id;
        this.nbr_max=nbr_max;
        this.nbr_participants=nbr_participants;
        this.nom = nom;
        this.titre = titre;
        this.adresse=adresse;
        this.date=date;
        this.img = img;

        
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }
     
      public Evenements(int id,Date date,String adresse,String titre, String nom,int nbr_participants,int nbr_max,String img,int id_emp) {
        this.id = id;
        this.nbr_max=nbr_max;
        this.nbr_participants=nbr_participants;
        this.nom = nom;
        this.titre = titre;
        this.adresse=adresse;
        this.date=date;
        this.img = img;
        this.id_emp = id_emp;

        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTitre() {
        return titre;
    }
    
    
    public void setTitre(String titre) {
        this.titre = titre;
    }


    public void setAdresse (String adresse) {
        this.adresse = adresse;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    
    public void setDate (Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setNbr_participants (int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }
    
    public int getNbr_participants () {
       return nbr_participants;
    }
   
     
    public void setNbr_max (int nbr_max) {
        this.nbr_max = nbr_max;
    }
   
    
    public int getNbr_max () {
       return nbr_max;
    }
    
    
   
public void setImg (String img) {
        this.img = img;
    }
   
    
    public String getImg () {
       return this.img;
    }
    
    
    
   

    
}
