package dao.Impl;

import dao.IUtilisateurDao;
import dao.SingletonDataBase;
import models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImpl implements IUtilisateurDao {
    private static String QUERY_GET_USER_BY_PSEUDO_AND_PASSWORD = "SELECT * FROM Utilisateur WHERE pseudo = ? AND motDePasse = ?";
    private static String QUERY_ADD_USER = "INSERT INTO Utilisateur (pseudo, motDePasse,dateCreation,id_personne) VALUES (?, ?,'2024-08-14',2)";
    private static String QUERY_UPDATE_USER = "UPDATE Utilisateur SET motDePasse = ? WHERE pseudo = ?";
    private static String QUERY_DELETE_USER = "DELETE FROM Utilisateur WHERE pseudo = ?";
    private static String QUERY_LIST_USERS = "SELECT * FROM Utilisateur";

    @Override
    public Utilisateur getUser(String identifiant, String motDePasse) throws SQLException {
        PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_GET_USER_BY_PSEUDO_AND_PASSWORD);
        statement.setString(1, identifiant);
        statement.setString(2, motDePasse);
        ResultSet resultSet = statement.executeQuery();
        Utilisateur utilisateur = null;

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pseudo = resultSet.getString("pseudo");
            String motDePass = resultSet.getString("motDePasse");
            utilisateur = new Utilisateur(id, pseudo, motDePass);
        }
        return utilisateur;
    }

    @Override
    public boolean addUser(String identifiant, String motDePasse) throws SQLException {
        PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_ADD_USER);
        statement.setString(1, identifiant);
        statement.setString(2, motDePasse);
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    @Override
    public boolean updateUser(String identifiant, String motDePasse) throws SQLException {
        PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_UPDATE_USER);
        statement.setString(1, motDePasse);
        statement.setString(2, identifiant);
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    @Override
    public void deleteUser(String identifiant, String motDePasse) {

    }

    @Override
    public boolean deleteUser(String identifiant) throws SQLException {
        PreparedStatement statement = SingletonDataBase.getInstance().prepareStatement(QUERY_DELETE_USER);
        statement.setString(1, identifiant);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }

    @Override
    public List<Utilisateur> listeUtilisateur() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Statement statement = SingletonDataBase.getInstance().createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_LIST_USERS);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pseudo = resultSet.getString("pseudo");
            String motDePasse = resultSet.getString("motDePasse");
            utilisateurs.add(new Utilisateur(id, pseudo, motDePasse));
        }

        return utilisateurs;

    }
}
