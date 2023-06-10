package com.poo2hibernate.educ4all.matiere;

import com.poo2hibernate.educ4all.niveau.Niveau;
import com.poo2hibernate.educ4all.niveau.NiveauRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatiereService {
    private final MatiereRepository matiereRepository;
    private final NiveauRepository niveauRepository;
    @Autowired
    public MatiereService(MatiereRepository matiereRepository, NiveauRepository niveauRepository) {
        this.matiereRepository = matiereRepository;
        this.niveauRepository = niveauRepository;
    }

    public List<Matiere> getMatieres() {
        return matiereRepository.findAll();
    }
    public void addNewMatiere(Matiere matiere) {
        Optional<Matiere> matiereByName = matiereRepository
                .findMatiereByName(matiere.getNom());

        if (matiereByName.isPresent()) {
            throw new IllegalStateException("Name Of The Matiere already taken");
        }
        try {
            matiereRepository.save(matiere);
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("ERROR !! The Level Where You Are Trying To Add A Matiere DOES NOT EXISTS");
            e.printStackTrace();
        }
    }
    public void deleteMatiere(Long matiereId) {
        if (!matiereRepository.existsById(matiereId)) {
            throw new IllegalStateException("This Matiere doesn't exist");
        }

        matiereRepository.deleteById(matiereId);
    }

    @Transactional
    public void updateMatiere(Long matiereID, String nom, Short coef, String description, Niveau niveau) {
        Matiere matiere = matiereRepository.findById(matiereID)
                .orElseThrow(() -> new IllegalStateException("This Matiere doesn't exist"));

        if (nom != null && nom.length() > 0 && !Objects.equals(nom, matiere.getNom())) {
            matiere.setNom(nom);
        }

        if (coef != null && !Objects.equals(coef, matiere.getCoef())) {
            matiere.setCoef(coef);
        }

        if (description != null && description.length() > 0 && !Objects.equals(description, matiere.getDescription())) {
            matiere.setDescription(description);
        }

        if (niveau != null && !Objects.equals(niveau, matiere.getNiveau())) {
            try {
                matiere.setNiveau(niveau);
            } catch (InvalidDataAccessApiUsageException e) {
                System.out.println("ERROR !! The Level Where You Are Trying To Update To The Matiere DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }


    public List<Matiere> getMatieresByLevel(Long levelId) {
        Niveau niveau = niveauRepository.findById(levelId)
                .orElseThrow(() -> new IllegalStateException("This Level doesn't exist"));
        return matiereRepository.findByLevel(
                niveau
        );
    }
}
