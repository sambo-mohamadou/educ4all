package com.poo2hibernate.educ4all.eleve;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepository extends CrudRepository<Eleve, Long> {

}
