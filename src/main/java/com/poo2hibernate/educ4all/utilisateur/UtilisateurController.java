package com.poo2hibernate.educ4all.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/eleve")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getUtilisateurs(){return utilisateurService.getUtilisateurs();}
    @GetMapping(path = "/{id}")
    public Utilisateur getUtilisateurById(@PathVariable("id") Long id){return utilisateurService.findById(id).orElse(null);}
    @PostMapping(path = "/add")
    public void  registerNewUtilisateur(@RequestBody Utilisateur utilisateur){
        utilisateurService.addNewUtilisateur(utilisateur);}
    @DeleteMapping(path = "/delete/{utilisateurId}")
    public void deleteUtilisateur(@PathVariable("utilisateurId") Long utilisateurId){
        utilisateurService.deleteUtilisateur(utilisateurId);}

}
