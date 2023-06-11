package com.poo2hibernate.educ4all.eleve;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eleve")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eleveId;
    private String eleveNom;
    private String eleveMail;
    private String eleveMdp;

    public Eleve(String eleveNom, String eleveMail, String eleveMdp) {
        this.eleveNom = eleveNom;
        this.eleveMail = eleveMail;
        this.eleveMdp = eleveMdp;
    }
}