package services;

import models.Professeur;

import java.util.List;

public interface IProfesseurService {
    public Professeur save(Professeur professeur);
    public  Professeur update(Professeur professeur);
    public void delete(int identifiant);
    public List<Professeur> getAll();
    public void getOne(int identifiant);
}
