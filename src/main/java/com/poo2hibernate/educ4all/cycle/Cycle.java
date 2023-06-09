package com.poo2hibernate.educ4all.cycle;

import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysteme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cycle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cycle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long cycleId;
    private String nom;
    private Short duree;
    private String description;
    @ManyToOne
    @JoinColumn(name = "soussystemId")
    private SousSysteme sousSysteme;

    public Cycle(String nom, Short duree, String description, SousSysteme sousSysteme) {
        this.nom = nom;
        this.duree = duree;
        this.description = description;
        this.sousSysteme = sousSysteme;
    }
}
