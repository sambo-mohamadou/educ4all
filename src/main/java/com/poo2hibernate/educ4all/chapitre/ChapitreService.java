package com.poo2hibernate.educ4all.chapitre;

import com.poo2hibernate.educ4all.matiere.Matiere;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChapitreService {
    private final ChapitreRepository chapitreRepository;
    @Autowired
    public ChapitreService(ChapitreRepository chapitreRepository) {
        this.chapitreRepository = chapitreRepository;
    }

    public List<Chapitre> getChapters() {
        return chapitreRepository.findAll();
    }

    public void addNewChapter(Chapitre chapitre) {
        Optional<Chapitre> chapterByName = chapitreRepository
                .findChapterByName(chapitre.getNom());

        if (chapterByName.isPresent()) {
            throw new IllegalStateException("Name Of The Chapter already taken");
        }
        try {
            chapitreRepository.save(chapitre);
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("ERROR !! The Matiere Where You Are Trying To Add A Chapter DOES NOT EXISTS");
            e.printStackTrace();
        }
    }

    public void deleteChapter(Long chapitreId) {
        if (!chapitreRepository.existsById(chapitreId)) {
            throw new IllegalStateException("This Chapter doesn't exist");
        }

        chapitreRepository.deleteById(chapitreId);
    }

    @Transactional
    public void updateChapter(Long chapitreId, String nom, String description, Matiere matiere) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalStateException("This Chapter doesn't exist"));

        if (nom != null && nom.length() > 0 && !Objects.equals(nom, chapitre.getNom())) {
            chapitre.setNom(nom);
        }

        if (description != null && description.length() > 0 && !Objects.equals(description, chapitre.getDescription())) {
            chapitre.setDescription(description);
        }

        if (matiere != null && !Objects.equals(matiere, chapitre.getMatiere())) {
            try {
                chapitre.setMatiere(matiere);
            } catch (InvalidDataAccessApiUsageException e) {
                System.out.println("ERROR !! The Matiere Where You Are Trying To Update To The Chapter DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }
}
