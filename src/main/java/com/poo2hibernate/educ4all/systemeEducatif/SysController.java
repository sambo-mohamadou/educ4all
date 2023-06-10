package com.poo2hibernate.educ4all.systemeEducatif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all/gestion/systeme")
public class SysController {
    private final SysService sysService;
    @Autowired
    public SysController(SysService sysService) {
        this.sysService = sysService;
    }
    @GetMapping
    public List<Systeme> getSystems(){return sysService.getSystems();}
    @PostMapping(path = "/add")
    public void  registerNewSystem(@RequestBody Systeme systeme){sysService.addNewSystem(systeme);}
    @DeleteMapping(path = "/delete/{systemId}")
    public void deleteSystem(@PathVariable("systemId") String systemId){sysService.deleteSystem(parseLong(systemId));}
    @PutMapping(path = "/update/{systemId}")
    public void updateSystem(
            @PathVariable("systemId") String systemId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description){

        sysService.updateSystem(parseLong(systemId), nom, description);
    }
}
