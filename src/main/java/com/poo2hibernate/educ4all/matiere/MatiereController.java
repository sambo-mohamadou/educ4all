package com.poo2hibernate.educ4all.matiere;

import com.poo2hibernate.educ4all.niveau.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/matiere")
public class MatiereController {
    private final MatiereService matiereService;
    @Autowired
    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping
    public List<Matiere> getMatieres(){return matiereService.getMatieres();}
    @PostMapping(path = "/add")
    public void  registerNewMatiere(@RequestBody Matiere matiere){matiereService.addNewMatiere(matiere);}
    @DeleteMapping(path = "/delete/{matiereId}")
    public void deleteMatiere(@PathVariable("matiereId") Long matiereId){matiereService.deleteMatiere(matiereId);}
    @PutMapping(path = "/update/{matiereId}")
    public void updateMatiere(
            @PathVariable("matiereId") Long matiereId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Short coef,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Niveau niveau){

        matiereService.updateMatiere(matiereId,nom,coef,description,niveau);
    }
}
