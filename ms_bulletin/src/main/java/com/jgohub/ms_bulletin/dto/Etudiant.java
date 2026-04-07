package com.jgohub.ms_bulletin.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Etudiant {
    @NotNull(message = "Le matricule ne peut pas etre nul")
    @NotBlank(message = "Le matricule ne peut pas etre vide")
    private String matricule;
    @NotNull(message = "Le nom ne peut pas etre nul")
    @NotBlank(message = "Le nom ne peut pas etre vide")
    private String nom;
    @NotNull(message = "Le prenom ne peut pas etre nul")
    @NotBlank(message = "Le prenom ne peut pas etre vide")
    private String prenom;
    @NotNull(message = "L'email ne peut pas etre nul")
    @NotBlank(message = "L'email ne peut pas etre vide")
    private String email;
}
