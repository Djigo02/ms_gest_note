package com.jgohub.ms_notes.dao;

import com.jgohub.ms_notes.dto.Notes;
import com.jgohub.ms_notes.entities.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotesRepository extends JpaRepository<NotesEntity,Long> {
    List<NotesEntity> findByCoursId(Long coursId);

    List<NotesEntity> findAllByEtudiantMatricule(String matricule);
}
