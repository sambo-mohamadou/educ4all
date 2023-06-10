package com.poo2hibernate.educ4all.cycle;

import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysteme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all/gestion/cycle")
public class CycleController {
    private final CycleService cycleService;
    @Autowired
    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    @GetMapping
    public List<Cycle> getCycles(){return cycleService.getCycles();}
    @PostMapping(path = "/add")
    public void  registerNewCycle(@RequestBody Cycle cycle){cycleService.addNewCycle(cycle);}
    @DeleteMapping(path = "/delete/{cycleId}")
    public void deleteCycle(@PathVariable("cycleId") String cycleId){cycleService.deleteCycle(parseLong(cycleId));}
    @PutMapping(path = "/update/{cycleId}")
    public void updateCycle(
            @PathVariable("cycleId") String cycleId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Short duree,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) SousSysteme sousSysteme){

        cycleService.updateCycle(parseLong(cycleId),nom,duree,description,sousSysteme);
    }

}
