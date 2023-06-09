package com.poo2hibernate.educ4all.niveau;

import com.poo2hibernate.educ4all.cycle.Cycle;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NiveauService {
    private final NiveauRepository niveauRepository;
    @Autowired
    public NiveauService(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public List<Niveau> getLevels() {
        return niveauRepository.findAll();
    }

    public void addNewLevel(Niveau niveau) {
        Optional<Niveau> levelByName = niveauRepository
                .findLevelByName(niveau.getNom());

        if (levelByName.isPresent()) {
            throw new IllegalStateException("Name Of The Level already taken");
        }
        try {
            niveauRepository.save(niveau);
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("ERROR !! The Cycle Where You Are Trying To Add A Level DOES NOT EXISTS");
            e.printStackTrace();
        }
    }

    public void deleteLevel(Long niveauId) {
        if (!niveauRepository.existsById(niveauId)) {
            throw new IllegalStateException("This Level doesn't exist");
        }

        niveauRepository.deleteById(niveauId);
    }

    @Transactional
    public void updateLevel(Long niveauId, String nom, String description, Cycle cycle) {
        Niveau niveau = niveauRepository.findById(niveauId)
                .orElseThrow(() -> new IllegalStateException("This Level doesn't exist"));

        if (nom != null && nom.length() > 0 && !Objects.equals(nom, niveau.getNom())) {
            niveau.setNom(nom);
        }

        if (description != null && description.length() > 0 && !Objects.equals(description, niveau.getDescription())) {
            niveau.setDescription(description);
        }

        if (cycle != null && !Objects.equals(cycle, niveau.getCycle())) {
            try {
                niveau.setCycle(cycle);
            } catch (InvalidDataAccessApiUsageException e) {
                System.out.println("ERROR !! The Cycle Where You Are Trying To Update To The Level DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }
}
