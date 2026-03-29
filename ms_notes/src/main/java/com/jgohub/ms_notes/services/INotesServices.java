package com.jgohub.ms_notes.services;

import com.jgohub.ms_notes.dto.Notes;
import com.jgohub.ms_notes.entities.NotesEntity;

import java.util.List;

public interface INotesServices {
    Notes createNotes(NotesEntity notes);
    List<Notes> getNotesByCoursId(Long courseId);
    List<Notes> getNotesByMatricule(String matricule);
    Notes updateNotes(NotesEntity notes);
    void deleteNotes(Notes notes);
    List<Notes> getAllNotes();
}
