package com.poo2hibernate.educ4all.sousSystemeEducatif;

import com.poo2hibernate.educ4all.systemeEducatif.Systeme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "soussysteme")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SousSysteme {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long sousSystemId;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "systemId")
    private Systeme systeme;

    public SousSysteme(String nom, String description,Systeme systeme) {
        this.nom = nom;
        this.description = description;
        this.systeme = systeme;
    }
}
