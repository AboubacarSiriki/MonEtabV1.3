package dao;

import models.Eleve;

import java.sql.SQLException;
import java.util.List;

public interface IEleveDao {
    boolean addEleve(Eleve eleve) throws SQLException;
    boolean updateEleve(Eleve eleve) throws SQLException;
    boolean deleteEleve(int id) throws SQLException;
    Eleve getEleveById(int id) throws SQLException;
    List<Eleve> listeEleves() throws SQLException;

    Eleve ajouter(Eleve eleve);

    Eleve modifier(Eleve eleve);

    void supprimer(int identifiant);

    Eleve Obtenir(int identifiant);

    List<Eleve> lister();
}
