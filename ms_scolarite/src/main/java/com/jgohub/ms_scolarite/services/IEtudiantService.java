package com.jgohub.ms_scolarite.services;

import com.jgohub.ms_scolarite.dto.Etudiant;
import com.jgohub.ms_scolarite.entities.EtudiantEntity;

import java.util.List;

public interface IEtudiantService {
    Etudiant getEtudiantByMatricule(String matricule);
    List<Etudiant> getAllEtudiants();
    Etudiant createEtudiant(EtudiantEntity etudiant);
    Etudiant updateEtudiant(EtudiantEntity etudiant);
    void deleteEtudiantByMatricule(String matricule);
}
