package com.jgohub.ms_scolarite.services;

import com.jgohub.ms_scolarite.dto.Cours;
import com.jgohub.ms_scolarite.entities.CoursEntity;

import java.util.List;

public interface ICoursService {
    Cours findCoursById(Long id);
    List<Cours> getAllCourses();
    Cours createCours(CoursEntity cours);
    Cours updateCours(CoursEntity cours);
    void deleteCoursByMatricule(String matricule);
}
