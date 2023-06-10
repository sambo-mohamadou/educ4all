package com.poo2hibernate.educ4all.matiere;

import com.poo2hibernate.educ4all.niveau.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    @Query("SELECT s FROM Matiere s WHERE s.nom = ?1")
    Optional<Matiere> findMatiereByName(String nom);

    @Query("SELECT s FROM Matiere s WHERE s.niveau = ?1")
    List<Matiere> findByLevel(Niveau niveau);
}
