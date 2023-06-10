package com.poo2hibernate.educ4all.cycle;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import com.poo2hibernate.educ4all.chapitre.ChapitreController;
import com.poo2hibernate.educ4all.chapitre.ChapitreService;
import com.poo2hibernate.educ4all.lesson.Lesson;
import com.poo2hibernate.educ4all.lesson.LessonController;
import com.poo2hibernate.educ4all.lesson.LessonService;
import com.poo2hibernate.educ4all.matiere.Matiere;
import com.poo2hibernate.educ4all.matiere.MatiereController;
import com.poo2hibernate.educ4all.matiere.MatiereService;
import com.poo2hibernate.educ4all.multimedia.video.VideoFileLocationService;
import com.poo2hibernate.educ4all.niveau.Niveau;
import com.poo2hibernate.educ4all.niveau.NiveauController;
import com.poo2hibernate.educ4all.niveau.NiveauService;
import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysController;
import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysService;
import com.poo2hibernate.educ4all.sousSystemeEducatif.SousSysteme;
import com.poo2hibernate.educ4all.systemeEducatif.SysController;
import com.poo2hibernate.educ4all.systemeEducatif.SysRepository;
import com.poo2hibernate.educ4all.systemeEducatif.Systeme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CycleConfig {
    @Bean
    CommandLineRunner commandLineRunner(SousSysService service, SysRepository repo, CycleService cser, NiveauService n, MatiereService m, ChapitreService c, LessonService l,
                                        SysController sys, SousSysController ssys, CycleController csery, NiveauController ny, MatiereController my, ChapitreController cy, LessonController ly,
                                        VideoFileLocationService vid) {
        return args -> {
            Systeme sambo = new Systeme(
                    "Sambo",
                    "b4chel0rd@gmail.com"
            );

            SousSysteme ssambo = new SousSysteme(
                    "ssambo",
                    "descriptio",
                    sambo
            );

            Cycle cycle = new Cycle("scdaff", (short) 3, "sfgeeg",ssambo);

            Niveau niveau = new Niveau("niveau", "fwffff", cycle);

            Matiere matiere = new Matiere("matier",(short) 4, "deafaf", niveau);

            Chapitre chapitre = new Chapitre("chap","adjd", matiere);

            Lesson lesson = new Lesson("jsn","sdj",chapitre);

            byte[] content = "fdsjfjs".getBytes();

            repo.save(sambo);
            sys.getSystems();

            service.addNewSubSystem(ssambo);
            ssys.getSubSystems();

            cser.addNewCycle(cycle);
            csery.getCycles();

            n.addNewLevel(niveau);
            ny.getLevels();

            m.addNewMatiere(matiere);
            my.getMatieres();

            c.addNewChapter(chapitre);
            cy.getChapters();

            l.addNewLesson(lesson);
            ly.getLessons();


            vid.saveVideo(content, "video.mp4", 1L);

            vid.findVideo("video.mp4");

            List<Matiere> das = m.getMatieresByLevel(1L);


        };
    }
}