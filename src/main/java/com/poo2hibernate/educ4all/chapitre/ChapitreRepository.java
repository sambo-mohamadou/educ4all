package com.poo2hibernate.educ4all.chapitre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {

    @Query("SELECT s FROM Chapitre s WHERE s.nom = ?1")
    Optional<Chapitre> findChapterByName(String nom);
}
