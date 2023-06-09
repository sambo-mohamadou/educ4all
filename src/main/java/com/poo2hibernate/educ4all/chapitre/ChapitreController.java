package com.poo2hibernate.educ4all.chapitre;

import com.poo2hibernate.educ4all.matiere.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/chapitre")
public class ChapitreController {
    private final ChapitreService chapitreService;
    @Autowired
    public ChapitreController(ChapitreService chapitreService) {
        this.chapitreService = chapitreService;
    }

    @GetMapping
    public List<Chapitre> getChapters(){return chapitreService.getChapters();}
    @PostMapping(path = "/add")
    public void  registerNewChapter(@RequestBody Chapitre chapitre){chapitreService.addNewChapter(chapitre);}
    @DeleteMapping(path = "/delete/{chapitreId}")
    public void deleteChapter(@PathVariable("chapitreId") Long chapitreId){chapitreService.deleteChapter(chapitreId);}
    @PutMapping(path = "/update/{chapitreId}")
    public void updateChapter(
            @PathVariable("chapitreId") Long chapitreId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Matiere matiere){

        chapitreService.updateChapter(chapitreId,nom,description,matiere);
    }
}
