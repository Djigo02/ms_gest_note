package com.jgohub.ms_scolarite.services.impl;

import com.jgohub.ms_scolarite.dao.ICoursRepository;
import com.jgohub.ms_scolarite.dto.Cours;
import com.jgohub.ms_scolarite.entities.CoursEntity;
import com.jgohub.ms_scolarite.exceptions.RequestException;
import com.jgohub.ms_scolarite.mapping.CoursMapper;
import com.jgohub.ms_scolarite.services.ICoursService;
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
public class CoursService implements ICoursService {

    private final ICoursRepository coursRepository;
    private final CoursMapper coursMapper;
    private final MessageSource messageSource;


    @Override
    @Transactional(readOnly = true)
    public Cours findCoursById(Long id) {
        return coursMapper.
                toCours(coursRepository.findById(id)
                        .orElseThrow(() -> new RequestException("Ce cours n'existe pas", HttpStatus.NOT_FOUND)
                        )
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cours> getAllCourses() {
        return StreamSupport.stream(coursRepository.findAll().spliterator(), false)
                .map(coursMapper::toCours)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public Cours createCours(CoursEntity cours) {
        if(cours.getCode() == null || cours.getCode().isEmpty()){
            throw new RequestException("Le code obligatoire", HttpStatus.BAD_REQUEST);
        }
        if (coursRepository.findByCode(cours.getCode()) != null) {
            throw new RequestException("Ce code est existent", HttpStatus.BAD_REQUEST);
        }
        return coursMapper.toCours(coursRepository.save(cours));
    }


    @Override
    @Transactional()
    public Cours updateCours(CoursEntity cours) {
        if (cours.getId() == null) {
            throw new RequestException("Cet utilisateur n'existe pas", HttpStatus.BAD_REQUEST);
        }
        CoursEntity existingCours = coursRepository.findById(cours.getId())
                .orElse(null);
        if (existingCours == null) {
            throw new RequestException("Cet utilisateur n'existe pas", HttpStatus.BAD_REQUEST);
        }
        // Mise à jour des champs
        existingCours.setCode(cours.getCode());
        existingCours.setLibelle(cours.getLibelle());
        existingCours.setCredit(cours.getCredit());
        CoursEntity updatedCours = coursRepository.save(existingCours);
        return coursMapper.toCours(updatedCours);
    }

    @Override
    @Transactional()
    public void deleteCoursById(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new RequestException("Ce cours n'existe pas", HttpStatus.BAD_REQUEST);
        }
        coursRepository.deleteById(id);

    }
}
