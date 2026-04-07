package com.jgohub.ms_scolarite.controllers;

import com.jgohub.ms_scolarite.dto.Cours;
import com.jgohub.ms_scolarite.entities.CoursEntity;
import com.jgohub.ms_scolarite.services.ICoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms_scolarite/cours")
@RequiredArgsConstructor
public class CoursController {

    private final ICoursService coursService;

    @GetMapping
    public ResponseEntity<List<Cours>> getCours() {
        return new ResponseEntity<>(coursService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable Long id) {
        return new ResponseEntity<>(coursService.findCoursById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cours> saveCours(@RequestBody CoursEntity cours) {
        return new ResponseEntity<>(coursService.createCours(cours), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> updateCours(@PathVariable Long id,
                                             @RequestBody CoursEntity cours) {
        cours.setId(id);
        return new ResponseEntity<>(coursService.updateCours(cours), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCours(@PathVariable Long id) {
        coursService.deleteCoursById(id);
        return new ResponseEntity<>("Cours supprimé avec succès : " + id, HttpStatus.OK);
    }
}