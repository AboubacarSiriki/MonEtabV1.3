package services;

import models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateurService {
    boolean authentification(String identifiant, String motDePasse) throws SQLException;
    boolean ajouterCompte(String identifiant, String motDePasse) throws SQLException;
    boolean modifierMotDepass(String identifiant, String motDePasse) throws SQLException;
    boolean supprimerCompte(String identifiant) throws SQLException;
    List<Utilisateur> listeUtilisateur() throws SQLException;

    void ajouterCompte(Utilisateur nouvelUtilisateur);
}