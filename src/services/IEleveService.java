package services;

import models.Eleve;

import java.util.List;

public interface IEleveService {

    Eleve save(Eleve eleve);
    Eleve update(Eleve eleve);
    void delete(int identifiant);
    Eleve getOne(int identifiant);
    List<Eleve> getAll();
}
