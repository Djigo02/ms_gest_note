package com.jgohub.ms_notes.services.impl;

import com.jgohub.ms_notes.dao.INotesRepository;
import com.jgohub.ms_notes.dto.Cours;
import com.jgohub.ms_notes.dto.Etudiant;
import com.jgohub.ms_notes.dto.Notes;
import com.jgohub.ms_notes.entities.NotesEntity;
import com.jgohub.ms_notes.exceptions.RequestException;
import com.jgohub.ms_notes.mapping.NotesMapper;
import com.jgohub.ms_notes.mid.IHttpClient;
import com.jgohub.ms_notes.services.INotesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NotesService implements INotesServices {

    private final INotesRepository notesRepository;
    private final NotesMapper notesMapper;
    private final IHttpClient httpClient;

    @Override
    public Notes createNotes(NotesEntity notes) {

        // Vérifier si étudiant existe
        Etudiant etudiant = httpClient.getEtudiantByMatricule(notes.getEtudiantMatricule());
        if (etudiant == null) {
            throw new RequestException("Etudiant introuvable", HttpStatus.NOT_FOUND);
        }

        // Vérifier si cours existe
        Cours cours = httpClient.getCoursById(notes.getCoursId());
        if (cours == null) {
            throw new RequestException("Cours introuvable", HttpStatus.NOT_FOUND);
        }

        return notesMapper.toNotes(notesRepository.save(notes));
    }

    @Override
    public List<Notes> getNotesByCoursId(Long courseId) {

        // Vérifier existence du cours
        Cours cours = httpClient.getCoursById(courseId);
        if (cours == null) {
            throw new RequestException("Cours introuvable", HttpStatus.BAD_REQUEST);
        }

        return StreamSupport.stream(notesRepository.findByCoursId(courseId).spliterator(), false)
                .map( notesMapper::toNotes)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notes> getNotesByMatricule(String matricule) {

        // Vérifier existence étudiant
        Etudiant etudiant = httpClient.getEtudiantByMatricule(matricule);
        if (etudiant == null) {
            throw new RequestException("Etudiant introuvable", HttpStatus.BAD_REQUEST);
        }

        return StreamSupport.stream(notesRepository.findAllByEtudiantMatricule(matricule).spliterator(), false)
                .map( notesMapper::toNotes)
                .collect(Collectors.toList());
    }

    @Override
    public Notes updateNotes(NotesEntity notes) {

        if (notes.getId() == null) {
            throw new RequestException("Note introuvable", HttpStatus.BAD_REQUEST);
        }

        NotesEntity existing = notesRepository.findById(notes.getId()).orElse(null);

        if (existing==null) {
            throw new RequestException("Note introuvable", HttpStatus.BAD_REQUEST);
        }
        // Mise à jour
        existing.setNote(notes.getNote());
        existing.setCoursId(notes.getCoursId());
        existing.setEtudiantMatricule(notes.getEtudiantMatricule());

        return notesMapper.toNotes(notesRepository.save(notes));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Notes> getAllNotes() {
        return StreamSupport.stream(notesRepository.findAll().spliterator(), false)
                .map(notesMapper::toNotes)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotes(Notes notes) {

        if (notes.getId() == null) {
            throw new RequestException("Note introuvable", HttpStatus.BAD_REQUEST);
        }

        NotesEntity existing = notesRepository.findById(notes.getId()).orElse(null);
        if (existing==null) {
            throw new RequestException("Note introuvable", HttpStatus.BAD_REQUEST);
        }
        notesRepository.deleteById(existing.getId());
    }
}