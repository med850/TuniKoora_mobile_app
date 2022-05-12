/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author wassim
 */
public class Article {
    private int id;
    private String titre;
    private String description;

    public Article() {
    }

    public Article(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", description=" + description + '}';
    }


  
    
    
    
}
