package models;

import java.util.Date;

public class Personne {
    private int id;
    private Date dateNaissance;
    private String ville;
    private String nom;
    private String prenom;

    public Personne() {
    }

    public Personne(int id, Date dateNaissance, String ville, String nom, String prenom) {
        this.id = id;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void supprimer(int id){

    }

    public void lister(){

    }

    public int obternirAge(Date age){

        return 0;
    }
}
