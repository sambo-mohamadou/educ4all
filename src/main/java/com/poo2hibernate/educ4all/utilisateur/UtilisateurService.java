package com.poo2hibernate.educ4all.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getUtilisateurs() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }
    
    public Optional<Utilisateur> getUtilisateur(Long id) {
        return utilisateurRepository.findById(id);
    }

    public void addNewUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long utilisateurId) {
        if (!utilisateurRepository.existsById(utilisateurId)) {
            throw new IllegalStateException("Cet eleve n'existe pas");
        }

        utilisateurRepository.deleteById(utilisateurId);
    }

    public Optional<Utilisateur> findById(Long id) {
    }
}
