package com.poo2hibernate.educ4all.lesson;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all")
public class LessonController {
    private final LessonService lessonService;
    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(path = "/gestion/lecon")
    public List<Lesson> getLessons(){return lessonService.getLessons();}

    @GetMapping(path = "/user/lecon/{chapitreId}")
    public List<Lesson> getLessonsByChapter(@PathVariable("chapitreId") String chapitreId){return lessonService.getLessonsByChapter(parseLong(chapitreId));}

    @PostMapping(path = "/gestion/lecon/add")
    public void  registerNewLesson(@RequestBody Lesson lesson){lessonService.addNewLesson(lesson);}
    @DeleteMapping(path = "/gestion/lecon/delete/{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") String lessonId){lessonService.deleteLesson(parseLong(lessonId));}
    @PutMapping(path = "/gestion/lecon/update/{lessonId}")
    public void updateLesson(
            @PathVariable("lessonId") String lessonId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Chapitre chapitre){

        lessonService.updateLesson(parseLong(lessonId),nom,description,chapitre);
    }

}
