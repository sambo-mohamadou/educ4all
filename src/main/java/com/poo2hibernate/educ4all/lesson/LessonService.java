package com.poo2hibernate.educ4all.lesson;

import com.poo2hibernate.educ4all.chapitre.Chapitre;
import com.poo2hibernate.educ4all.chapitre.ChapitreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ChapitreRepository chapitreRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, ChapitreRepository chapitreRepository) {
        this.lessonRepository = lessonRepository;
        this.chapitreRepository = chapitreRepository;
    }

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public void addNewLesson(Lesson lesson) {
        Optional<Lesson> lessonByName = lessonRepository
                .findLessonByName(lesson.getNom());

        if (lessonByName.isPresent()) {
            throw new IllegalStateException("Name Of The Lesson already taken");
        }
        try {
            lessonRepository.save(lesson);
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("ERROR !! The Chapter Where You Are Trying To Add A Lesson DOES NOT EXISTS");
            e.printStackTrace();
        }
    }

    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new IllegalStateException("This Lesson doesn't exist");
        }

        lessonRepository.deleteById(lessonId);
    }

    @Transactional
    public void updateLesson(Long lessonId, String nom, String description, Chapitre chapitre) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("This Lesson doesn't exist"));

        if (nom != null && nom.length() > 0 && !Objects.equals(nom, lesson.getNom())) {
            lesson.setNom(nom);
        }

        if (description != null && description.length() > 0 && !Objects.equals(description, lesson.getDescription())) {
            lesson.setDescription(description);
        }

        if (chapitre != null && !Objects.equals(chapitre, lesson.getChapitre())) {
            try {
                lesson.setChapitre(chapitre);
            } catch (InvalidDataAccessApiUsageException e) {
                System.out.println("ERROR !! The Chapter Where You Are Trying To Update To The Lesson DOES NOT EXISTS");
                e.printStackTrace();
            }
        }
    }

    public List<Lesson> getLessonsByChapter(Long chapitreId) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalStateException("This Lesson doesn't exist"));

        return lessonRepository.findByChapitre(chapitre);
    }
}
