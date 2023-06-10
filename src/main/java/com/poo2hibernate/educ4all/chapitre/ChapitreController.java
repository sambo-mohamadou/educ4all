package com.poo2hibernate.educ4all.chapitre;

import com.poo2hibernate.educ4all.matiere.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all")
public class ChapitreController {
    private final ChapitreService chapitreService;
    @Autowired
    public ChapitreController(ChapitreService chapitreService) {
        this.chapitreService = chapitreService;
    }

    @GetMapping(path = "/gestion/chapitre")
    public List<Chapitre> getChapters(){return chapitreService.getChapters();}

    @GetMapping(path = "/user/chapitre/{matiereId}")
    public List<Chapitre> getChaptersByMatiere(@PathVariable("matiereId") String matiereId){return chapitreService.getChaptersByMatiere(parseLong(matiereId));}

    @PostMapping(path = "/gestion/chapitre/add")
    public void  registerNewChapter(@RequestBody Chapitre chapitre){chapitreService.addNewChapter(chapitre);}
    @DeleteMapping(path = "/gestion/chapitre/delete/{chapitreId}")
    public void deleteChapter(@PathVariable("chapitreId") String chapitreId){chapitreService.deleteChapter(parseLong(chapitreId));}
    @PutMapping(path = "/gestion/chapitre/update/{chapitreId}")
    public void updateChapter(
            @PathVariable("chapitreId") String chapitreId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Matiere matiere){

        chapitreService.updateChapter(parseLong(chapitreId),nom,description,matiere);
    }
}
