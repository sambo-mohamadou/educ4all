package com.poo2hibernate.educ4all.chapitre;

import com.poo2hibernate.educ4all.matiere.Matiere;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chapitre")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chapitre {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long chapitreId;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "matiereId")
    private Matiere matiere;

    public Chapitre(String nom, String description, Matiere matiere) {
        this.nom = nom;
        this.description = description;
        this.matiere = matiere;
    }
}
