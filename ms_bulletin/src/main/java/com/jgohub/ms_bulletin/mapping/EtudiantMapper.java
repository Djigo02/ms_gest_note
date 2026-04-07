package com.jgohub.ms_bulletin.mapping;

import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;

import org.mapstruct.Mapper;

@Mapper
public interface EtudiantMapper {
    Etudiant toEtudiant(EtudiantRefEntity etudiantEntity);
    EtudiantRefEntity fromEtudiant(Etudiant etudiant);
}
