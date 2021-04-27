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
 * @author hp
 */
public class User {
   
    private int id;
    private String password;
    private int phone;
    private String address;
    private String town;
    private String fb;
    private String linkdin;
    private String description;
    private String img;
    private ImageView image1;
     private int nbr_follow;
    private String roles;
    private String company;
    private String categorie;
    private String discr;
     private String nom;
    private String prenom;
    private Date date_naissance;
    private String niv_etude;
    private String type_candidat;
    private int etat;
    private String block;

    public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }

    public User(int id, String password, int phone, String address, String town, String fb, String linkdin, String description, String img, ImageView image1, int nbr_follow, String roles, String company, String categorie, String nom, String prenom, Date date_naissance, String niv_etude, String type_candidat) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.img = img;
        this.image1 = image1;
        this.nbr_follow = nbr_follow;
        this.roles = roles;
        this.company = company;
        this.categorie = categorie;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.niv_etude = niv_etude;
        this.type_candidat = type_candidat;
    }
  
    public User() {
        
    }
    
  //search
     public User(int id, String company, String categorie, String address, int phone , String fb, String linkdin ,  ImageView image1) {
          this.id=id;
         this.fb=fb;
       this.linkdin=linkdin;
       this.address=address;
       this.phone=phone;
       this.company=company;
       this.categorie=categorie;
       this.image1=image1;
    }

    public User(int id) {
        this.id = id;
    }
    public User(String nom) {
        this.nom = nom;
    }
    // affichage candidat
 public User(int id,String nom, String prenom, String address , int phone , String fb, String linkdin , String type , String level, Date birthday,String town, String description, String password ) {
     this.id=id;
       this.nom=nom;
       this.prenom=prenom;
       this.phone = phone;
       this.address=address;
       this.fb=fb;
       this.linkdin=linkdin;
       this.type_candidat=type;
       this.niv_etude=level;
       this.date_naissance=birthday;
    
       this.description=description;
       this.town=town;
        this.password=password;
     
    }
 // affichage candidat
 public User(int id,String nom, String prenom, String address , int phone , String fb, String linkdin , String type , String level, Date birthday,String town, String description, String password, ImageView image1, int nbr_follow ) {
     this.id=id;
       this.nom=nom;
       this.prenom=prenom;
       this.phone = phone;
       this.address=address;
       this.fb=fb;
       this.linkdin=linkdin;
       this.type_candidat=type;
       this.niv_etude=level;
       this.date_naissance=birthday;
    
       this.description=description;
       this.town=town;
        this.password=password;
        this.image1=image1;
        this.nbr_follow=nbr_follow;
    }
 // affichage candidat back
 public User(int id,String nom, String prenom, String address , int phone , String fb, String linkdin , String type , String level, Date birthday,String town, String description, String password, ImageView image1, int nbr_follow , String block) {
     this.id=id;
       this.nom=nom;
       this.prenom=prenom;
       this.phone = phone;
       this.address=address;
       this.fb=fb;
       this.linkdin=linkdin;
       this.type_candidat=type;
       this.niv_etude=level;
       this.date_naissance=birthday;
    
       this.description=description;
       this.town=town;
        this.password=password;
        this.image1=image1;
        this.nbr_follow=nbr_follow;
         this.block=block;
    }
 //pour ledit candidat
 public User(int id,String nom,String prenom, String address , String password,Date birthday,
         String town,String fb, String linkdin , String type ,String img ,String level, int phone ,String description , String discr , String company, String categorie, String role, int nbr_follow ) {
     this.id=id;
       this.nom=nom;
       this.town=town;
       this.prenom=prenom;
       this.phone = phone;
       this.address=address;
       this.fb=fb;
       this.linkdin=linkdin;
       this.type_candidat=type;
       this.niv_etude=level;
       this.date_naissance=birthday;
       this.img=img;
       this.password=password;
       this.description=description;
        this.discr=discr;
         this.company=company;
          this.categorie=categorie;
          this.roles=role;
          this.nbr_follow=nbr_follow;
      
    }
 //affichage employer
 public User(int id, String company , String categorie , String address , int phone , String fb , String linkdin, String town, String description, String password, ImageView image1) {
     this.id=id;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.image1 = image1;
        this.company=company;
        this.categorie=categorie;
    }
    

    public User(String password, int phone, String address, String town, String fb, String linkdin, String description, String img, int nbr_folow) {
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.img = img;
        this.nbr_follow = nbr_folow;
    }
   //login session
    public User(int id, String password, int phone, String address, String town, String fb, String linkdin, String description, String img, String discr,   String company,   String categorie, String nom, String prenom, Date date_naissance, String niv_etude, String type_candidat,  String roles, int nbr_follow, int etat, String block) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.img = img;
        this.nbr_follow = nbr_follow;
        this.roles = roles;
        this.company = company;
        this.categorie = categorie;
        this.discr = discr;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.niv_etude = niv_etude;
        this.type_candidat = type_candidat;
        this.etat = etat;
        this.block = block;
    }
    
    


    public int getId() {
        return id;
    }
     public String getBlock() {
        return block;
    }
 public String getDiscr() {
        return discr;
    }
 public void setDiscr(String discr) {
        this.discr=discr;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getTown() {
        return town;
    }

    public String getFb() {
        return fb;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }
     public String getRole() {
        return roles;
    }

    public int getNbr_follow() {
        return nbr_follow;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }
     public void setRole(String role) {
        this.roles = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }
     public void setBlock(String block) {
        this.block = block;
    }

    public void setNbr_follow(int nbr_folow) {
        this.nbr_follow = nbr_folow;
    }

    public User(int id, String password, int phone, String address, String town, String fb, String linkdin, String description, String img, ImageView image1) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.img = img;
        this.image1 = image1;
    }

    public User(int id, String password, int phone, String address, String town, String fb, String linkdin, String description, String img, ImageView image1, String nom, String prenom, Date date_naissance, String niv_etude) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.town = town;
        this.fb = fb;
        this.linkdin = linkdin;
        this.description = description;
        this.img = img;
        this.image1 = image1;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.niv_etude = niv_etude;
    }
      public String getCompany() {
        return company;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
 public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
     public int getEtat() {
        return etat;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getNiv_etude() {
        return niv_etude;
    }

    public String getType_candidat() {
        return type_candidat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
      public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }

    public void setType_candidat(String type_candidat) {
        this.type_candidat = type_candidat;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", phone=" + phone + ", address=" + address + ", town=" + town + ", fb=" + fb + ", linkdin=" + linkdin + ", description=" + description + ", img=" + img + ", nbr_folow=" + nbr_follow + '}';
    }

    
    
}
