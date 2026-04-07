package com.jgohub.ms_scolarite.services;

import com.jgohub.ms_scolarite.dto.Etudiant;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtudiantProducer {

    private final KafkaTemplate<String, Etudiant> kafkaTemplate;

    public void sendEtudiantCreated(Etudiant event) {
        kafkaTemplate.send("etudiant-creer-topic", event);
    }
}