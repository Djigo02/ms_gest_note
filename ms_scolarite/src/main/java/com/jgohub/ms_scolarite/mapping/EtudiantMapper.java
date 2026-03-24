package com.jgohub.ms_scolarite.mapping;

import com.jgohub.ms_scolarite.dto.Etudiant;
import com.jgohub.ms_scolarite.entities.EtudiantEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EtudiantMapper {
    Etudiant toEtudiant(EtudiantEntity etudiantEntity);
    EtudiantEntity fromEtudiant(Etudiant etudiant);
}
