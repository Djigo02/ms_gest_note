package com.jgohub.ms_bulletin.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bulletin {
    @NotNull(message = "L'id ne peut pas etre nul")
    @NotBlank(message = "L'id ne peut pas etre vide")
    private Long id;
    @NotNull(message = "La mention ne peut pas etre nul")
    @NotBlank(message = "La mention ne peut pas etre vide")
    private String mention;
    @NotNull(message = "La moyenne ne peut pas etre nulle")
    @Positive(message = "La moyenne doit etre positive")
    @Max(value = 20, message = "La moyenne doit etre comprise entre 0 et 20")
    private Double moyenne;
    @NotNull(message = "Le matricule ne peut pas etre nul")
    @NotBlank(message = "Le matricule ne peut pas etre vide")
    private String etudiantMatricule;
    private boolean valide;
}
