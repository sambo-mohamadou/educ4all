package com.poo2hibernate.educ4all.niveau;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    @Query("SELECT s FROM Niveau s WHERE s.nom = ?1")
    Optional<Niveau> findLevelByName(String nom);
}
