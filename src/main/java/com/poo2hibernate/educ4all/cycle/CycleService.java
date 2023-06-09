package com.poo2hibernate.educ4all.cycle;

import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysteme;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CycleService {
    private final CycleRepository cycleRepository;

    @Autowired
    public CycleService(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }

    public List<Cycle> getCycles() {
        return cycleRepository.findAll();
    }

    public void addNewCycle(Cycle cycle) {
        Optional<Cycle> cycleByName = cycleRepository
                .findCycleByName(cycle.getNom());

        if (cycleByName.isPresent()) {
            throw new IllegalStateException("Name Of The Cycle already taken");
        }
        try {
            cycleRepository.save(cycle);
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("ERROR !! The SubSystem Where You Are Trying To Add A Cycle DOES NOT EXISTS");
            e.printStackTrace();
        }
    }

    public void deleteCycle(Long cycleId) {
        if (!cycleRepository.existsById(cycleId)) {
            throw new IllegalStateException("This Cycle doesn't exist");
        }

        cycleRepository.deleteById(cycleId);
    }

    @Transactional
    public void updateCycle(Long cycleId, String nom, Short duree, String description, SousSysteme sousSysteme) {
        Cycle cycle = cycleRepository.findById(cycleId)
                .orElseThrow(() -> new IllegalStateException("This Cycle doesn't exist"));

        if (nom != null && nom.length() > 0 && !Objects.equals(nom, cycle.getNom())) {
            cycle.setNom(nom);
        }

        if (duree != null && !Objects.equals(duree, cycle.getDuree())) {
            cycle.setDuree(duree);
        }

        if (description != null && description.length() > 0 && !Objects.equals(description, cycle.getDescription())) {
            cycle.setDescription(description);
        }

        if (sousSysteme != null && !Objects.equals(sousSysteme, cycle.getSousSysteme())) {
            try {
                cycle.setSousSysteme(sousSysteme);
            } catch (InvalidDataAccessApiUsageException e) {
                System.out.println("ERROR !! The SubSystem Where You Are Trying To Update To The Cycle DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }
}
