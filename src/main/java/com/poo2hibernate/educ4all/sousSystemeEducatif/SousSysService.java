package com.poo2hibernate.educ4all.sousSystemeEducatif;

import com.poo2hibernate.educ4all.systemeEducatif.Systeme;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SousSysService {
    private final SousSysRepository sousSysRepository;
    @Autowired
    public SousSysService(SousSysRepository sousSysRepository) {
        this.sousSysRepository = sousSysRepository;
    }
    public List<SousSysteme> getSubSystems(){return sousSysRepository.findAll();}

    public void addNewSubSystem(SousSysteme sousSysteme){
        Optional<SousSysteme> subSystemByName = sousSysRepository
                .findSubSystemByName(sousSysteme.getNom());

        if(subSystemByName.isPresent()){
            throw new IllegalStateException("Name Of The SubSystem already taken");
        }
        try {
            sousSysRepository.save(sousSysteme);
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println("ERROR !! The System Where You Are Trying To Add A Subsystem DOES NOT EXISTS");
            e.printStackTrace();
        }
    }

    public void deleteSubSystem(Long sousSystemId) {
        if(!sousSysRepository.existsById(sousSystemId)){
            throw new IllegalStateException("This SubSystem doesn't exist");
        }

        sousSysRepository.deleteById(sousSystemId);
    }

    @Transactional
    public void updateSubSystem(Long sousSystemId, String nom, String description, Systeme systeme) {
        SousSysteme sousSysteme = sousSysRepository.findById(sousSystemId)
                .orElseThrow(() -> new IllegalStateException("This SubSystem doesn't exist"));

        if(nom != null && nom.length() > 0 && !Objects.equals(nom, sousSysteme.getNom())){
            sousSysteme.setNom(nom);
        }

        if(description != null && description.length() > 0 && !Objects.equals(description, sousSysteme.getDescription())){
            sousSysteme.setDescription(description);
        }

        if(systeme != null &&  !Objects.equals(systeme, sousSysteme.getSysteme())){
            try {
                sousSysteme.setSysteme(systeme);
            }catch (InvalidDataAccessApiUsageException e){
                System.out.println("ERROR !! The System Where You Are Trying To Update To The Subsystem DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }

}
