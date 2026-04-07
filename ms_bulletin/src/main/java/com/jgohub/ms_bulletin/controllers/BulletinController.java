package com.jgohub.ms_bulletin.controllers;

import com.jgohub.ms_bulletin.dao.IBulletinRepository;
import com.jgohub.ms_bulletin.dao.IEtudiantRepository;
import com.jgohub.ms_bulletin.dto.Bulletin;
import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.entities.BulletinEntity;
import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;
import com.jgohub.ms_bulletin.services.IBulletinServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms_bulletin")
@RequiredArgsConstructor
public class BulletinController {

    private final IBulletinServices bulletinService;
    private final IEtudiantRepository etudiantRepository;

    @GetMapping("/etudiants")
    public ResponseEntity<List<EtudiantRefEntity>> getAllEtudiantsInThisService(){
        return new ResponseEntity<>(etudiantRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bulletin> createBulletin(@RequestBody String matricule) {
        return new ResponseEntity<>(bulletinService.createBulletin(matricule), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bulletin> getBulletinById(@PathVariable Long id) {
        return new ResponseEntity<>(bulletinService.getBulletinById(id), HttpStatus.OK);
    }

    @GetMapping("/etudiant/{matricule}")
    public ResponseEntity<Bulletin> getBulletinByMatricule(@PathVariable String matricule) {
        return new ResponseEntity<>(bulletinService.getBulletinByMatricule(matricule), HttpStatus.OK);
    }
}