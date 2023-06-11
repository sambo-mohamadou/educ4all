package com.poo2hibernate.educ4all.utilisateur;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

}
