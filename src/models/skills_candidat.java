/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author hp
 */
public class skills_candidat {
    private int skills_id;
    private int candidat_id;

    public skills_candidat(int skills_id, int candidat_id) {
        this.skills_id = skills_id;
        this.candidat_id = candidat_id;
    }
    public skills_candidat() {
     
    }

    public int getSkills_id() {
        return skills_id;
    }

    public void setSkills_id(int skills_id) {
        this.skills_id = skills_id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }
    
    
}
