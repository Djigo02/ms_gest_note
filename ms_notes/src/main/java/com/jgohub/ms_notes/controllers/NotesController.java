package com.jgohub.ms_notes.controllers;


import com.jgohub.ms_notes.dto.Notes;
import com.jgohub.ms_notes.entities.NotesEntity;
import com.jgohub.ms_notes.services.INotesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms_notes")
@RequiredArgsConstructor
public class NotesController {

    private final INotesServices notesService;

    @GetMapping
    public ResponseEntity<List<Notes>> getCours() {
        return new ResponseEntity<>(notesService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<Notes>> getNotesByCours(@PathVariable Long coursId) {
        return new ResponseEntity<>(notesService.getNotesByCoursId(coursId), HttpStatus.OK);
    }

    @GetMapping("/etudiant/{matricule}")
    public ResponseEntity<List<Notes>> getNotesByEtudiant(@PathVariable String matricule) {
        return new ResponseEntity<>(notesService.getNotesByMatricule(matricule), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notes> saveNotes(@RequestBody NotesEntity notes) {
        return new ResponseEntity<>(notesService.createNotes(notes), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNotes(@PathVariable Long id,
                                             @RequestBody NotesEntity notes) {
        notes.setId(id);
        return new ResponseEntity<>(notesService.updateNotes(notes), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotes(@PathVariable Long id) {

        Notes notes = new Notes();
        notes.setId(id);

        notesService.deleteNotes(notes);

        return new ResponseEntity<>("Note supprimée avec succès : " + id, HttpStatus.OK);
    }
}