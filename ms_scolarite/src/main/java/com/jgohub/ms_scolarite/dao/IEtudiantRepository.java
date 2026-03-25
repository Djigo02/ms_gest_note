package com.jgohub.ms_scolarite.dao;

import com.jgohub.ms_scolarite.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IEtudiantRepository extends JpaRepository<EtudiantEntity, String> {
    EtudiantEntity findByMatricule(String matricule);
    boolean existsByMatricule(String matricule);
}
