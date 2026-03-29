package com.jgohub.ms_notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cours {
    @NotNull(message = "L'id ne peut pas etre nul")
    @NotBlank(message = "L'id ne peut pas etre vide")
    private Long id;
    @NotNull(message = "Le code ne peut pas etre nul")
    @NotBlank(message = "Le code ne peut pas etre vide")
    private String code;
    @NotNull(message = "Le libelle ne peut pas etre nul")
    @NotBlank(message = "Le libelle ne peut pas etre vide")
    private String libelle;
    @NotNull(message = "Le nombre de credit ne peut pas etre nul")
    @Positive(message = "Le nombre de credit doit etre positif")
    private int credit;
}
