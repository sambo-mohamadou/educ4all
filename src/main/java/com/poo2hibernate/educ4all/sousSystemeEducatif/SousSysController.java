package com.poo2hibernate.educ4all.sousSystemeEducatif;

import com.poo2hibernate.educ4all.systemeEducatif.Systeme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/sousSysteme")
public class SousSysController {
    private final SousSysService sousSysService;
    @Autowired
    public SousSysController(SousSysService sousSysService) {
        this.sousSysService = sousSysService;
    }
    @GetMapping
    public List<SousSysteme> getSubSystems(){return sousSysService.getSubSystems();}
    @PostMapping(path = "/add")
    public void  registerNewSubSystem(@RequestBody SousSysteme sousSysteme){sousSysService.addNewSubSystem(sousSysteme);}
    @DeleteMapping(path = "/delete/{sousSystemId}")
    public void deleteSubSystem(@PathVariable("sousSystemId") Long sousSystemId){sousSysService.deleteSubSystem(sousSystemId);}
    @PutMapping(path = "/update/{sousSystemId}")
    public void updateSubSystem(
            @PathVariable("souSystemId") Long sousSystemId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false)  Systeme systeme){

        sousSysService.updateSubSystem(sousSystemId, nom, description,systeme);
    }
}
