package com.poo2hibernate.educ4all.multimedia.video;

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
}
