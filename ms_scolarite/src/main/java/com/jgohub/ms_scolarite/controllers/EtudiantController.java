package com.jgohub.ms_scolarite.controllers;

import com.jgohub.ms_scolarite.dto.Etudiant;
import com.jgohub.ms_scolarite.entities.EtudiantEntity;
import com.jgohub.ms_scolarite.services.IEtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scolarite/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final IEtudiantService etudiantService;

    @GetMapping
    public ResponseEntity<List<Etudiant>> getEtudiants() {
        return new ResponseEntity<>(etudiantService.getAllEtudiants(), HttpStatus.OK);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable String matricule) {
        return new ResponseEntity<>(etudiantService.getEtudiantByMatricule(matricule), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody EtudiantEntity etudiant) {
        return new ResponseEntity<>(etudiantService.createEtudiant(etudiant), HttpStatus.CREATED);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable String matricule,
                                                   @RequestBody EtudiantEntity etudiant) {
        etudiant.setMatricule(matricule);
        return new ResponseEntity<>(etudiantService.updateEtudiant(etudiant), HttpStatus.OK);
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable String matricule) {
        etudiantService.deleteEtudiantByMatricule(matricule);
        return new ResponseEntity<>("Etudiant supprimé avec succès : " + matricule, HttpStatus.OK);
    }
}
