package com.poo2hibernate.educ4all.niveau;

import com.poo2hibernate.educ4all.cycle.Cycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all/gestion/niveau")
public class NiveauController {
    private final NiveauService niveauService;
    @Autowired
    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping
    public List<Niveau> getLevels(){return niveauService.getLevels();}
    @PostMapping(path = "/add")
    public void  registerNewLevel(@RequestBody Niveau niveau){niveauService.addNewLevel(niveau);}
    @DeleteMapping(path = "/delete/{niveauId}")
    public void deleteLevel(@PathVariable("niveauId") String niveauId){niveauService.deleteLevel(parseLong(niveauId));}
    @PutMapping(path = "/update/{niveauId}")
    public void updateLevel(
            @PathVariable("niveauId") String niveauId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Cycle cycle){

        niveauService.updateLevel(parseLong(niveauId),nom,description,cycle);
    }

}
