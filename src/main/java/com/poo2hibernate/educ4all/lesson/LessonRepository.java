package com.poo2hibernate.educ4all.lesson;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT s FROM Lesson s WHERE s.nom = ?1")
    Optional<Lesson> findLessonByName(String nom);

    @Query("SELECT s FROM Lesson s WHERE s.chapitre = ?1")
    List<Lesson> findByChapitre(Chapitre chapitre);
}
