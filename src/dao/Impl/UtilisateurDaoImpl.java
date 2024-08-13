package dao.Impl;

import dao.IUtilisateurDao;
import dao.SingletonDataBase;
import models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDaoImpl implements IUtilisateurDao {
    private static String QUERY_GET_USER_BY_PSEUDO_AND_PASSWORD = "SELECT * FROM Utilisateur WHERE pseudo = ? AND motDePasse = ?";

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
            utilisateur = new Utilisateur(id,pseudo,motDePass);
        }
        return utilisateur;
    }

    @Override
    public Utilisateur updateUser(String identifiant, String motDePasse) {
        return null;
    }

    @Override
    public void deleteUser(String identifiant, String motDePasse) {

    }

    @Override
    public List<Utilisateur> listeUtilisateur() {
        return List.of();
    }
}
