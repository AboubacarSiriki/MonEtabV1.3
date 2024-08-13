package dao;

import models.Eleve;

import java.util.List;

/**
 * Interface définissant les opérations CRUD pour la gestion des élèves.
 */
public interface IEleveDao {

    /**
     * Ajoute un élève.
     *
     * @param eleve Élève à ajouter.
     * @return Élève ajouté.
     */
    Eleve Ajouter(Eleve eleve);

    /**
     * Modifie un élève.
     *
     * @param eleve Élève à modifier.
     * @return Élève modifié.
     */
    Eleve modifier(Eleve eleve);

    /**
     * Supprime un élève par son identifiant.
     *
     * @param identifiant Identifiant de l'élève à supprimer.
     */
    void supprimer(int identifiant);

    /**
     * Obtient un élève par son identifiant.
     *
     * @param identifiant Identifiant de l'élève.
     * @return Élève trouvé.
     */
    Eleve Obtenir(int identifiant);

    /**
     * Obtient la liste de tous les élèves.
     *
     * @return Liste d'élèves.
     */
    List<Eleve> obtenirProfesseur();
}
