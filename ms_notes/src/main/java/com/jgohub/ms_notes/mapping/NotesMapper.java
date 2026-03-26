package com.jgohub.ms_notes.mapping;

import com.jgohub.ms_notes.dto.Notes;
import com.jgohub.ms_notes.entities.NotesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface NotesMapper {
    NotesEntity fromNotes(Notes notes);
    Notes toNotes(NotesEntity notes);
}
