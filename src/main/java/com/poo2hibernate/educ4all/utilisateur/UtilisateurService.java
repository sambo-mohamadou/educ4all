package com.poo2hibernate.educ4all.eleve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveService {
    private final EleveRepository eleveRepository;
    @Autowired
    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public List<Eleve> getEleves() {
        return (List<Eleve>) eleveRepository.findAll();
    }
    
    public Optional<Eleve> getEleve(Long id) {
        return eleveRepository.findById(id);
    }

    public void addNewEleve(Eleve eleve) {
        eleveRepository.save(eleve);
    }

    public void deleteEleve(Long eleveId) {
        if (!eleveRepository.existsById(eleveId)) {
            throw new IllegalStateException("Cet eleve n'existe pas");
        }

        eleveRepository.deleteById(eleveId);
    }

    public Optional<Eleve> findById(Long id) {
    }
}
