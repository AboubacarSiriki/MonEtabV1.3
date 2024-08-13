package models;

import dao.SingletonDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String motDePass;

    public Utilisateur() {
    }

    public Utilisateur(int id, String identifiant, String motDePass) {
        this.id = id;
        this.identifiant = identifiant;
        this.motDePass = motDePass;
    }

    public void ajouterUtilisateurDefaut() {

        try {
            String query = "INSERT INTO models.Utilisateur (pseudo, motDePasse, dateCreation, id_personne) VALUES (?, ?, CURDATE(), 1)";
            PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(query);
            statement.setString(1, "admin");
            statement.setString(2, "admin");
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getters and setters can be added if needed

    public int getId() {
        return id;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    @Override
    public String toString() {
        return "models.Utilisateur{" +
                "id=" + id +
                ", identifiant='" + identifiant + '\'' +
                ", motDePass='" + motDePass + '\'' +
                '}';
    }
}