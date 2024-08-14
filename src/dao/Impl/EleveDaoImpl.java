package dao.Impl;

import dao.IEleveDao;
import dao.SingletonDataBase;
import models.Eleve;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EleveDaoImpl implements IEleveDao {
    private static final String QUERY_ADD_ELEVE = "INSERT INTO Eleve (nom, prenom, dateNaissance, classe, dateInscription) VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE_ELEVE = "UPDATE Eleve SET nom = ?, prenom = ?, dateNaissance = ?, classe = ? WHERE id = ?";
    private static final String QUERY_DELETE_ELEVE = "DELETE FROM Eleve WHERE id = ?";
    private static final String QUERY_GET_ELEVE_BY_ID = "SELECT * FROM Eleve WHERE id = ?";
    private static final String QUERY_LIST_ELEVES = "SELECT * FROM Eleve";

    @Override
    public boolean addEleve(Eleve eleve) throws SQLException {
        try (PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_ADD_ELEVE)) {
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());
            statement.setDate(3, Date.valueOf(eleve.getDateNaissance()));
            statement.setString(4, eleve.getClasse());
            statement.setDate(5, Date.valueOf(LocalDate.now())); // Utilisation de la date actuelle pour l'inscription

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    @Override
    public boolean updateEleve(Eleve eleve) throws SQLException {
        try (PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_UPDATE_ELEVE)) {
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());
            statement.setDate(3, Date.valueOf(eleve.getDateNaissance()));
            statement.setString(4, eleve.getClasse());
            statement.setInt(5, eleve.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public boolean deleteEleve(int id) throws SQLException {
        try (PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_DELETE_ELEVE)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public Eleve getEleveById(int id) throws SQLException {
        try (PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_GET_ELEVE_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    Date dateNaissance = resultSet.getDate("dateNaissance");
                    String classe = resultSet.getString("classe");
                    return new Eleve(id, nom, prenom, dateNaissance.toLocalDate(), classe);
                }
            }
        }
        return null;
    }

    @Override
    public List<Eleve> listeEleves() throws SQLException {
        List<Eleve> eleves = new ArrayList<>();

        try (Statement statement = SingletonDataBase.getInstance().createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_LIST_ELEVES)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Date dateNaissance = resultSet.getDate("dateNaissance");
                String classe = resultSet.getString("classe");
                eleves.add(new Eleve(id, nom, prenom, dateNaissance.toLocalDate(), classe));
            }
        }
        return eleves;
    }

    @Override
    public Eleve ajouter(Eleve eleve) {
        return null;
    }

    @Override
    public Eleve modifier(Eleve eleve) {
        return null;
    }

    @Override
    public void supprimer(int identifiant) {

    }

    @Override
    public Eleve Obtenir(int identifiant) {
        return null;
    }

    @Override
    public List<Eleve> lister() {
        return List.of();
    }
}
