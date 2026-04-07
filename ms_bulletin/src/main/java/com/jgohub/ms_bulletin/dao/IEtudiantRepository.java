package com.jgohub.ms_bulletin.dao;

import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEtudiantRepository extends JpaRepository<EtudiantRefEntity, String> {
    EtudiantRefEntity findByMatricule(String matricule);
    boolean existsByMatricule(String matricule);

    List<EtudiantRefEntity> getEtudiantRefEntitiesByMatricule(String matricule);
}
