package com.jgohub.ms_bulletin.services.impl;

import com.jgohub.ms_bulletin.dao.IBulletinRepository;
import com.jgohub.ms_bulletin.dao.IEtudiantRepository;
import com.jgohub.ms_bulletin.dto.Bulletin;
import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.dto.Notes;
import com.jgohub.ms_bulletin.entities.BulletinEntity;
import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;
import com.jgohub.ms_bulletin.exceptions.RequestException;
import com.jgohub.ms_bulletin.mapping.BulletinMapper;
import com.jgohub.ms_bulletin.mid.IHttpClient;
import com.jgohub.ms_bulletin.services.IBulletinServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletinService implements IBulletinServices {

    private final IBulletinRepository bulletinRepository;
    private final BulletinMapper bulletinMapper;
    private final IEtudiantRepository iEtudiantRepository;
    private final IHttpClient httpClient;

    @Override
    public Bulletin createBulletin(String matricule) {
        // Vérifier si étudiant existe (local ou distant)
        EtudiantRefEntity etudiant = iEtudiantRepository.findByMatricule(matricule);

        if (etudiant == null) {
            throw new RequestException("Etudiant {"+ matricule+"} introuvable", HttpStatus.BAD_REQUEST);
        }

        // Récupérer les notes depuis ms_notes
        List<Notes> notesList = httpClient.getNotesByMatricule(matricule);

        if (notesList == null || notesList.isEmpty()) {
            throw new RequestException("Aucune note trouvée pour cet étudiant", HttpStatus.BAD_REQUEST);
        }

        // Calcul de la moyenne
        double moyenne = notesList.stream()
                .mapToDouble(Notes::getNote)
                .average()
                .orElse(0.0);

        // Création du bulletin
        BulletinEntity bulletinEntity = new BulletinEntity();
        bulletinEntity.setEtudiantMatricule(matricule);
        bulletinEntity.setMoyenne(moyenne);

        // mention
        if (moyenne >= 16) bulletinEntity.setMention("Très Bien");
        else if (moyenne >= 14) bulletinEntity.setMention("Bien");
        else if (moyenne >= 12) bulletinEntity.setMention("Assez Bien");
        else if (moyenne >= 10) bulletinEntity.setMention("Passable");
        else bulletinEntity.setMention("Insuffisant");

        // Sauvegarde
        return bulletinMapper.toBulletin(
                bulletinRepository.save(bulletinEntity)
        );
    }

    @Override
    public Bulletin getBulletinById(Long id) {

        BulletinEntity bulletin = bulletinRepository.findById(id)
                .orElseThrow(() -> new RequestException("Bulletin introuvable", HttpStatus.BAD_REQUEST));

        return bulletinMapper.toBulletin(bulletin);
    }

    @Override
    public Bulletin getBulletinByMatricule(String matricule) {

        // Vérifier si étudiant existe
//        Etudiant etudiant = httpClient.getEtudiantByMatricule(matricule);
        EtudiantRefEntity etudiant = iEtudiantRepository.findByMatricule(matricule);

        if (etudiant == null) {
            throw new RequestException("Etudiant introuvable", HttpStatus.BAD_REQUEST);
        }

        BulletinEntity bulletin = bulletinRepository.findByEtudiantMatricule(matricule);

        if (bulletin == null) {
            throw new RequestException("Bulletin introuvable", HttpStatus.BAD_REQUEST);
        }

        return bulletinMapper.toBulletin(bulletin);
    }
}