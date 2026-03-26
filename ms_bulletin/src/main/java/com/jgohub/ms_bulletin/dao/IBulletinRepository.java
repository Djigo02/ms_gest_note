package com.jgohub.ms_bulletin.dao;

import com.jgohub.ms_bulletin.entities.BulletinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBulletinRepository extends JpaRepository<BulletinEntity, Long> {
    BulletinEntity findByEtudiantId(Long id);
}
