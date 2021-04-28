package Entities;

import java.sql.Date;
import javafx.scene.control.Button;

public class Internship extends Offre{
    
    private int duration;
    private String discr;
    private int category ;
    private String cat ;
    Button update;

    public Internship() {
    }
    
    public Internship(int id,String libelle, String post, Date dateExpiration, String description, String level, int duration, int category) {
        super(id,libelle, post, dateExpiration,description,level);
        this.duration = duration;
        this.category = category;
    }

    public Internship(int id, String libelle, String post, Date dateExpiration, String level, int duration, String cat) {
        super(id, libelle,post,dateExpiration,level);
        this.duration = duration;
        this.cat = cat;
    }
    
    public Internship(int id, String libelle, String post, Date dateExpiration, String description, String level, int duration, String cat) {
        super(id, libelle,post,dateExpiration,description,level);
        this.duration = duration;
        this.cat = cat;
    }
    
    //<editor-fold defaultstate="collapsed" desc="GETTERS & SETTERS">
    public String getDiscr(){
        return discr;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }
    

    //</editor-fold>
    
    @Override
    public String toString() {
        return "Internship{ " + super.toString() + ", duration=" + duration + '}';
    }
    
}
