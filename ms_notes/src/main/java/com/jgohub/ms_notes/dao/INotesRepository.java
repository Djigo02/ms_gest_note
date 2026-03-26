package com.jgohub.ms_notes.dao;

import com.jgohub.ms_notes.entities.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotesRepository extends JpaRepository<NotesEntity,Long> {
}
