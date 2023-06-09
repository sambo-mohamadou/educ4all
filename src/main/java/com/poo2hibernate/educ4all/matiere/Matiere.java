package com.poo2hibernate.educ4all.matiere;

import com.poo2hibernate.educ4all.niveau.Niveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matiere")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Matiere {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long matiereId;
    private String nom;
    private Short coef;
    private String description;
    @ManyToOne
    @JoinColumn(name = "niveauId")
    private Niveau niveau;

    public Matiere(String nom, Short coef, String description, Niveau niveau) {
        this.nom = nom;
        this.coef = coef;
        this.description = description;
        this.niveau = niveau;
    }
}
