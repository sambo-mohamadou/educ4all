package com.poo2hibernate.educ4all.matiere;

import com.poo2hibernate.educ4all.niveau.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all")
public class MatiereController {
    private final MatiereService matiereService;
    @Autowired
    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping(path = "/gestion/matiere")
    public List<Matiere> getMatieres(){return matiereService.getMatieres();}

    @GetMapping(path = "/user/matiere/{levelId}")
    public List<Matiere> getMatieresByLevel(@PathVariable("levelId") String levelId){
        return  matiereService.getMatieresByLevel(parseLong(levelId));
    }

    @PostMapping(path = "/gestion/matiere/add")
    public void  registerNewMatiere(@RequestBody Matiere matiere){matiereService.addNewMatiere(matiere);}
    @DeleteMapping(path = "/gestion/matiere/delete/{matiereId}")
    public void deleteMatiere(@PathVariable("matiereId") String matiereId){matiereService.deleteMatiere(parseLong(matiereId));}
    @PutMapping(path = "/gestion/matiere/update/{matiereId}")
    public void updateMatiere(
            @PathVariable("matiereId") String matiereId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Short coef,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Niveau niveau){

        matiereService.updateMatiere(parseLong(matiereId),nom,coef,description,niveau);
    }
}
