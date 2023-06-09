package com.poo2hibernate.educ4all.cycle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CycleRepository extends JpaRepository<Cycle, Long> {
    @Query("SELECT s FROM Cycle s WHERE s.nom = ?1")
    Optional<Cycle> findCycleByName(String nom);
}
