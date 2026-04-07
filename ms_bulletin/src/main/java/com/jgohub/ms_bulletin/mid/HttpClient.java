package com.jgohub.ms_bulletin.mid;

import com.jgohub.ms_bulletin.dto.Etudiant;
import com.jgohub.ms_bulletin.dto.Notes;
import com.jgohub.ms_bulletin.exceptions.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HttpClient implements IHttpClient {
    private final RestTemplate restTemplate;

    @Override
    public Etudiant getEtudiantByMatricule(String matricule) {
        String baseUrlEtudiant = "http://localhost:8081/scolarite/etudiants";
        final ResponseEntity<Etudiant> response = this.restTemplate.exchange(baseUrlEtudiant +'/'+matricule, HttpMethod.GET,null, Etudiant.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw  new RequestException("Etudiant introuvable", HttpStatus.NOT_FOUND);
        }
//        System.out.println("*******************😁😁😁😁😁😁😁😁😁 " + response.getBody() + " 😁😁😁😁😁😁😁*************************************");
        return response.getBody();
    }

    @Override
    public List<Notes> getNotesByMatricule(String matricule) {

        String baseUrlNotes = "http://localhost:8082/ms_notes/etudiant";

        ResponseEntity<Notes[]> response = restTemplate.exchange(
                baseUrlNotes + "/" + matricule,
                HttpMethod.GET,
                null,
                Notes[].class
        );

        return List.of(response.getBody());
    }

}
