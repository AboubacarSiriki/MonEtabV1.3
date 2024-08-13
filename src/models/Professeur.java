package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe représentant un professeur.
 * Elle hérite de la classe models.Personne et implémente les interfaces IEducation et dao.ICRUDProfesseur.
 */
public class Professeur extends Personne {
    // Attributs de la classe models.Professeur
    private Boolean vacant;
    private String matiereEnseigne;
    private String prochainCours;
    private String sujetProchaineReunion;

    /**
     * Constructeur par défaut de la classe models.Professeur.
     */
    public Professeur() {
    }

    /**
     * Constructeur avec paramètres.
     *
     * @param id                      Identifiant du professeur.
     * @param dateNaissance           Date de naissance du professeur.
     * @param ville                   Ville de résidence.
     * @param nom                     Nom du professeur.
     * @param prenom                  Prénom du professeur.
     * @param vacant                  Statut de disponibilité.
     * @param matiereEnseigne         Matière enseignée par le professeur.
     * @param prochainCours           Prochain cours que le professeur doit préparer.
     * @param sujetProchaineReunion   Sujet de la prochaine réunion.
     */

    public Professeur(int id, Date dateNaissance, String ville, String nom, String prenom, String vacant, String matiereEnseigne
    , String prochainCours, String sujetProchaineReunion) {
        super(id, dateNaissance, ville, nom, prenom);
        this.vacant = Boolean.valueOf(vacant);
        this.matiereEnseigne = matiereEnseigne;
        this.prochainCours = prochainCours;
        this.sujetProchaineReunion = sujetProchaineReunion;
    }

    public Boolean getVacant() {
        return vacant;
    }

    public String getMatiereEnseigne() {
        return matiereEnseigne;
    }

    public String getProchainCours() {
        return prochainCours;
    }

    public String getSujetProchaineReunion() {
        return sujetProchaineReunion;
    }

    public void setVacant(Boolean vacant) {
        this.vacant = vacant;
    }

    public void setMatiereEnseigne(String matiereEnseigne) {
        this.matiereEnseigne = matiereEnseigne;
    }

    public void setProchainCours(String prochainCours) {
        this.prochainCours = prochainCours;
    }

    public void setSujetProchaineReunion(String sujetProchaineReunion) {
        this.sujetProchaineReunion = sujetProchaineReunion;
    }

    public void ajouter(Professeur professeur){

    }

    public void mettreAjour(Professeur professeur){

    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "models.Professeur ID: " + getId() +
                ", Nom: " + getNom() +
                ", Prénom: " + getPrenom() +
                ", Ville: " + getVille() +
                ", Date de Naissance: " + sdf.format(getDateNaissance()) +
                ", Vacant: " + getVacant()+
                ", Matiere: " + getMatiereEnseigne()+
                ", Prochain cours: " + getProchainCours()+
                ", Prochaine Reunion: " + getSujetProchaineReunion();

    }

    /**
     * Méthode pour enseigner une matière.
     *
     * @param matiere Matière à enseigner.
     * @return Message indiquant la matière enseignée.
     */

    }
