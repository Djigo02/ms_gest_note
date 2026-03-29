package com.jgohub.ms_scolarite.services.impl;

import com.jgohub.ms_scolarite.dao.IEtudiantRepository;
import com.jgohub.ms_scolarite.dto.Etudiant;
import com.jgohub.ms_scolarite.entities.EtudiantEntity;
import com.jgohub.ms_scolarite.exceptions.RequestException;
import com.jgohub.ms_scolarite.mapping.EtudiantMapper;
import com.jgohub.ms_scolarite.services.IEtudiantService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@RequiredArgsConstructor
@Service
public class EtudiantService implements IEtudiantService {

    private final IEtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public Etudiant getEtudiantByMatricule(String matricule) {
        EtudiantEntity etudiant =  etudiantRepository.findByMatricule(matricule);
        if (etudiant == null) {
            throw new RequestException("Ce etudiant n'existe pas", HttpStatus.NOT_FOUND);
        }
        return etudiantMapper.toEtudiant(etudiant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Etudiant> getAllEtudiants() {
        return StreamSupport.stream(etudiantRepository.findAll().spliterator(), false)
                .map(etudiantMapper::toEtudiant)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Etudiant createEtudiant(EtudiantEntity etudiant) {

        if (etudiant.getMatricule() == null || etudiant.getMatricule().isEmpty()) {
            throw new RequestException("Le matricule est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (etudiantRepository.existsByMatricule(etudiant.getMatricule())) {
            throw new RequestException("Ce matricule existe déjà", HttpStatus.BAD_REQUEST);
        }

        return etudiantMapper.toEtudiant(etudiantRepository.save(etudiant));
    }

    @Override
    @Transactional
    public Etudiant updateEtudiant(EtudiantEntity etudiant) {

        if (etudiant.getMatricule() == null) {
            throw new RequestException("Cet étudiant n'existe pas", HttpStatus.BAD_REQUEST);
        }

        EtudiantEntity existingEtudiant = etudiantRepository.findByMatricule(etudiant.getMatricule());

        if (existingEtudiant == null) {
            throw new RequestException("Cet étudiant n'existe pas", HttpStatus.BAD_REQUEST);
        }

        // Mise à jour des champs
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setEmail(etudiant.getEmail());

        EtudiantEntity updated = etudiantRepository.save(existingEtudiant);

        return etudiantMapper.toEtudiant(updated);
    }

    @Override
    @Transactional
    public void deleteEtudiantByMatricule(String matricule) {

        EtudiantEntity etudiant = etudiantRepository.findByMatricule(matricule);

        if (etudiant == null) {
            throw new RequestException("Cet étudiant n'existe pas", HttpStatus.BAD_REQUEST);
        }

        etudiantRepository.delete(etudiant);
    }
}