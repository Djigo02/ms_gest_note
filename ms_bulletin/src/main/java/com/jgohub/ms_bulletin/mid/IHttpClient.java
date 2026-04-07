package com.jgohub.ms_bulletin.mid;


import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.dto.Notes;

import java.util.List;

public interface IHttpClient {
    Etudiant getEtudiantByMatricule(String matricule);
    List<Notes> getNotesByMatricule(String matricule);
}
