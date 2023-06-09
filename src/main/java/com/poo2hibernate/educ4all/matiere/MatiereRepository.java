package com.poo2hibernate.educ4all.matiere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    @Query("SELECT s FROM Matiere s WHERE s.nom = ?1")
    Optional<Matiere> findMatiereByName(String nom);
}
