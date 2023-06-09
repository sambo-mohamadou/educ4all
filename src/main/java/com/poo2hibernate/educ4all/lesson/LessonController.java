package com.poo2hibernate.educ4all.lesson;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/educ4all/gestion/lecon")
public class LessonController {
    private final LessonService lessonService;
    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<Lesson> getLessons(){return lessonService.getLessons();}
    @PostMapping(path = "/add")
    public void  registerNewLesson(@RequestBody Lesson lesson){lessonService.addNewLesson(lesson);}
    @DeleteMapping(path = "/delete/{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") Long lessonId){lessonService.deleteLesson(lessonId);}
    @PutMapping(path = "/update/{lessonId}")
    public void updateLesson(
            @PathVariable("lessonId") Long lessonId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Chapitre chapitre){

        lessonService.updateLesson(lessonId,nom,description,chapitre);
    }

}
