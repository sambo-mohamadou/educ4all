package com.poo2hibernate.educ4all.sousSystemeEducatif;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SousSysRepository extends JpaRepository<SousSysteme, Long> {
    @Query("SELECT s FROM SousSysteme s WHERE s.nom = ?1")
    Optional<SousSysteme> findSubSystemByName(String nom);
}
