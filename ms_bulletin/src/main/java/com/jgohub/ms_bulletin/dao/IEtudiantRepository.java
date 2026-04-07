package com.jgohub.ms_bulletin.dao;

import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEtudiantRepository extends JpaRepository<EtudiantRefEntity, String> {
    EtudiantRefEntity findByMatricule(String matricule);
    boolean existsByMatricule(String matricule);
}
