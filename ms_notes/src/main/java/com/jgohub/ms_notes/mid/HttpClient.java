package com.jgohub.ms_notes.mid;

import com.jgohub.ms_notes.dto.Cours;
import com.jgohub.ms_notes.dto.Etudiant;
import com.jgohub.ms_notes.exceptions.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HttpClient implements IHttpClient {
    private final RestTemplate restTemplate;

    @Override
    public Etudiant getEtudiantByMatricule(String matricule) {
        String baseUrlEtudiant = "http://localhost:8081/ms_scolarite/etudiants";
        final ResponseEntity<Etudiant> response = this.restTemplate.exchange(baseUrlEtudiant +'/'+matricule, HttpMethod.GET,null, Etudiant.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw  new RequestException("Etudiant introuvable", HttpStatus.NOT_FOUND);
        }
//        System.out.println("*******************😁😁😁😁😁😁😁😁😁 " + response.getBody() + " 😁😁😁😁😁😁😁*************************************");
        return response.getBody();
    }
    @Override
    public Cours getCoursById(Long id) {
        String baseUrlCours = "http://localhost:8081/ms_scolarite/cours";
        final ResponseEntity<Cours> response = this.restTemplate.exchange(baseUrlCours +'/'+id, HttpMethod.GET,null, Cours.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw  new RequestException("Etudiant introuvable", HttpStatus.NOT_FOUND);
        }
        return response.getBody();
    }
}
