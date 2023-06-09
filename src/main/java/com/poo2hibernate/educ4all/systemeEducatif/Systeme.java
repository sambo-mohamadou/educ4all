package com.poo2hibernate.educ4all.systemeEducatif;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "systeme")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Systeme {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long systemId;
    private String nom;
    private String description;
    public Systeme(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
}
