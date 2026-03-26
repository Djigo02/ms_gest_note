package com.jgohub.ms_notes.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notes {
    @NotNull(message = "L'identifiant ne doit pas etre nul")
    private Long id;
    @NotNull(message = "Le matricule ne peut pas etre nul")
    @NotBlank(message = "Le matricule ne peut pas etre vide")
    private String etudiantMatricule;
    @NotNull(message = "La session ne peut pas etre nul")
    @NotBlank(message = "La session ne peut pas etre vide")
    private String session;
    @NotNull(message = "L'id ne peut pas etre nul")
    @NotBlank(message = "L'id ne peut pas etre vide")
    private Long coursId;
    @NotNull(message = "La note ne peut pas etre nulle")
    @Positive(message = "La note doit etre positive")
    @Max(value = 20, message = "La note doit etre comprise entre 0 et 20")
    private Double note;
}
