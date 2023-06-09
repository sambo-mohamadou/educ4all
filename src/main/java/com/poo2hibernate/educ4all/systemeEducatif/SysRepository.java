package com.poo2hibernate.educ4all.systemeEducatif;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysRepository extends JpaRepository<Systeme, Long> {
    @Query("SELECT s FROM Systeme s WHERE s.nom = ?1")
    Optional<Systeme> findSystemByName(String nom);
}
