package com.poo2hibernate.educ4all.multimedia.video;

import com.poo2hibernate.educ4all.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByName(String name);

    @Query("SELECT name FROM Video")
    List<String> getAllEntryNames();

    @Query("SELECT s FROM Video s WHERE s.lesson = ?1")
    List<Video> findByLesson(Lesson lesson);
}
