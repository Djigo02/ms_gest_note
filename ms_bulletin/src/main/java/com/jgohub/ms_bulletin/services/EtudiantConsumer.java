package com.jgohub.ms_bulletin.services;

import com.jgohub.ms_bulletin.dao.IEtudiantRepository;
import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.entities.EtudiantRefEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtudiantConsumer {

    private final IEtudiantRepository repository;

    @KafkaListener(
            topics = "etudiant-creer-topic",
            groupId = "bulletin-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(Etudiant event) {

        EtudiantRefEntity ref = new EtudiantRefEntity();
        ref.setMatricule(event.getMatricule());
        ref.setNom(event.getNom());
        ref.setPrenom(event.getPrenom());
        ref.setEmail(event.getEmail());

        repository.save(ref);

        System.out.println(" 🧨🧨📍Etudiant reçu via Kafka : " + event.getMatricule());
//        System.out.println(" 🧨🧨📍Etudiant reçu via Kafka : " + event);
    }
}