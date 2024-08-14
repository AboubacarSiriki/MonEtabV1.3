package dao;

import models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateurDao {
    public Utilisateur getUser(String identifiant, String motDePasse) throws SQLException;

    boolean addUser(String identifiant, String motDePasse) throws SQLException;

    public boolean updateUser(String identifiant, String motDePasse) throws SQLException;
    public void deleteUser(String identifiant, String motDePasse);

    boolean deleteUser(String identifiant) throws SQLException;

    public List<Utilisateur> listeUtilisateur() throws SQLException;

}
