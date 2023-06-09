package com.poo2hibernate.educ4all.lesson;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lecon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long leconId;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "chapitreId")
    private Chapitre chapitre;

    public Lesson(String nom, String description, Chapitre chapitre) {
        this.nom = nom;
        this.description = description;
        this.chapitre = chapitre;
    }
}
