package com.jgohub.ms_notes.mid;

import com.jgohub.ms_notes.dto.Cours;
import com.jgohub.ms_notes.dto.Etudiant;

public interface IHttpClient {
    Etudiant getEtudiantByMatricule(String matricule);
    Cours getCoursById(Long id);
}
