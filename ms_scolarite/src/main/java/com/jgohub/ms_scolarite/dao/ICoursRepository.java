package com.jgohub.ms_scolarite.dao;

import com.jgohub.ms_scolarite.entities.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoursRepository extends JpaRepository<CoursEntity, Long> {

}
