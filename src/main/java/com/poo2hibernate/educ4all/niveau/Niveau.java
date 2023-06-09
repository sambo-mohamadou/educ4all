package com.poo2hibernate.educ4all.niveau;

import com.poo2hibernate.educ4all.cycle.Cycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "niveau")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Niveau {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long niveauId;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "cycleId")
    private Cycle cycle;

    public Niveau(String nom, String description, Cycle cycle) {
        this.nom = nom;
        this.description = description;
        this.cycle = cycle;
    }
}
