/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author CHAOUCH KHALIL
 */
public class Produit {
     private int id;
    private String nom;
    private int prix;
    private int qte;
    private String description;
    private String image;

    public Produit() {
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit(int id, String nom, int prix, int qte, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", qte=" + qte + ", description=" + description + ", image=" + image + '}';
    }
    
}
