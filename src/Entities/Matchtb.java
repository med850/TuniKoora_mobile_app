/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author arfao
 */
public class Matchtb {
   public int id;
    public String localisation;
    public String arbitreprincipale;
    public String tour;

    public Matchtb(int id, String localisation, String arbitreprincipale, String tour) {
        this.id = id;
        this.localisation = localisation;
        this.arbitreprincipale = arbitreprincipale;
        this.tour = tour;
    }

    public Matchtb() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getArbitreprincipale() {
        return arbitreprincipale;
    }

    public void setArbitreprincipale(String arbitreprincipale) {
        this.arbitreprincipale = arbitreprincipale;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Matchtb{" + "id=" + id + ", localisation=" + localisation + ", arbitreprincipale=" + arbitreprincipale + ", tour=" + tour + '}';
    }
    
    
}
