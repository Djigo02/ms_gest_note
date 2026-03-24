package com.jgohub.ms_scolarite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantEntity {
    @Id
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
}
