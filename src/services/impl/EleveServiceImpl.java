package services.impl;

import dao.IEleveDao;
import models.Eleve;
import services.IEleveService;

import java.util.List;

public class EleveServiceImpl implements IEleveService {

    private IEleveDao eleveDao;

    public EleveServiceImpl() {
        this.eleveDao = eleveDao;
    }

    @Override
    public Eleve save(Eleve eleve) {
        return eleveDao.ajouter(eleve);
    }

    @Override
    public Eleve update(Eleve eleve) {
        return eleveDao.modifier(eleve);
    }

    @Override
    public void delete(int identifiant) {
        eleveDao.supprimer(identifiant);
    }

    @Override
    public Eleve getOne(int identifiant) {
        return eleveDao.Obtenir(identifiant);
    }

    @Override
    public List<Eleve> getAll() {
        return eleveDao.lister();
    }
}
