/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aroua
 */
public class Joueur {
     private int id, tel, nb_but;
       private String nom, prenom, poste,image;

    public Joueur(int id, int tel, int nb_but, String nom, String prenom, String poste, String image) {
        this.id = id;
        this.tel = tel;
        this.nb_but = nb_but;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.image = image;
    }


     public Joueur(){

    }


     
    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", tel=" + tel +  ", nom=" + nom + ", prenom=" + prenom + ", poste=" + poste + '}';
    }

    public int getNb_but() {
        return nb_but;
    }

    public String getImage() {
        return image;
    }


      
      
      
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setNb_but(int nb_but) {
        this.nb_but = nb_but;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    
}
