/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.sql.SQLException;
import javafx.scene.image.ImageView;
import models.User;
import services.CandidatServices;

/**
 *
 * @author hp
 */
public class UserSession {
    private static UserSession instance;
    private User loggedUser;
    
     private int id;
    private String password;
    private int phone;
    private String address;
    private String town;
    private String fb;
    private String linkdin;
    private String description;
    private String img;
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

    public UserSession(String email) {
       CandidatServices cn = new CandidatServices();
       User u = cn.getUserById(email);
        this.id = u.getId();
        this.password = u.getPassword();
        this.phone = u.getPhone();
        this.address = u.getAddress();
        this.town = u.getTown();
        this.fb = u.getFb();
        this.linkdin = u.getLinkdin();
        this.description = u.getDescription();
        this.img = u.getImg();
        this.nbr_follow = u.getNbr_follow();
        this.roles = u.getRole();
        this.company = u.getCompany();
        this.categorie = u.getCategorie();
        this.discr = u.getDiscr();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.date_naissance = u.getDate_naissance();
        this.niv_etude = u.getNiv_etude();
        this.type_candidat = u.getType_candidat();
        this.etat = u.getEtat();
        this.block = u.getBlock();
    }
    
    
     public static UserSession setInstance(String email) throws SQLException {
        if (instance == null) {
            instance = new UserSession(email);
        }
        return instance;
    }
    public static UserSession setInstance(int id) throws SQLException {
        if (instance == null) {
            instance = new UserSession(id);
        }
        return instance;
    }
public void cleanUserSession() {
        address = "";// or null
    }
    public UserSession(int id) {
        this.id = id;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    @Override
    public String toString() {
        return "UserSession{" + "loggedUser=" + loggedUser + ", id=" + id + ", password=" + password + ", phone=" + phone + ", address=" + address + ", town=" + town + ", fb=" + fb + ", linkdin=" + linkdin + ", description=" + description + ", img=" + img + ", nbr_follow=" + nbr_follow + ", roles=" + roles + ", company=" + company + ", categorie=" + categorie + ", discr=" + discr + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", niv_etude=" + niv_etude + ", type_candidat=" + type_candidat + ", etat=" + etat + ", block=" + block + '}';
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNbr_follow() {
        return nbr_follow;
    }

    public void setNbr_follow(int nbr_follow) {
        this.nbr_follow = nbr_follow;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getNiv_etude() {
        return niv_etude;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }

    public String getType_candidat() {
        return type_candidat;
    }

    public void setType_candidat(String type_candidat) {
        this.type_candidat = type_candidat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    
    
    
    
}
