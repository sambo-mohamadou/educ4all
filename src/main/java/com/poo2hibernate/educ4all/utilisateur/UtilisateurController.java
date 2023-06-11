package com.poo2hibernate.educ4all.eleve;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import com.poo2hibernate.educ4all.chapitre.ChapitreService;
import com.poo2hibernate.educ4all.matiere.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/eleve")
public class EleveController {
    private final EleveService eleveService;
    @Autowired
    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping
    public List<Eleve> getEleves(){return eleveService.getEleves();}
    @GetMapping(path = "/{id}")
    public Eleve getEleveById(@PathVariable("id") Long id){return eleveService.findById(id).orElse(null);}
    @PostMapping(path = "/add")
    public void  registerNewEleve(@RequestBody Eleve eleve){eleveService.addNewEleve(eleve);}
    @DeleteMapping(path = "/delete/{eleveId}")
    public void deleteEleve(@PathVariable("eleveId") Long eleveId){eleveService.deleteEleve(eleveId);}

}
