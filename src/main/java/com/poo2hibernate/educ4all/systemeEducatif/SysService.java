package com.poo2hibernate.educ4all.systemeEducatif;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SysService {
    private final SysRepository sysRepository;
    @Autowired
    public SysService(SysRepository sysRepository) {
        this.sysRepository = sysRepository;
    }
    public List<Systeme> getSystems(){return sysRepository.findAll();}
    public void addNewSystem(Systeme systeme) {
        Optional<Systeme> systemByName = sysRepository
                .findSystemByName(systeme.getNom());

        if(systemByName.isPresent()){
            throw new IllegalStateException("Name Of The System already taken");
        }

        sysRepository.save(systeme);
    }
    public void deleteSystem(Long systemId) {
        if(!sysRepository.existsById(systemId)){
            throw new IllegalStateException("This System doesn't exist");
        }

        sysRepository.deleteById(systemId);
    }
    @Transactional
    public void updateSystem(Long systemId, String nom, String description) {
        Systeme systeme = sysRepository.findById(systemId)
                .orElseThrow(() -> new IllegalStateException("This System doesn't exist"));

        if(nom != null && nom.length() > 0 && !Objects.equals(nom, systeme.getNom())) {
            systeme.setNom(nom);
        }

        if(description != null && description.length() > 0 && !Objects.equals(description, systeme.getDescription())){
            systeme.setDescription(description);
        }
    }
}
