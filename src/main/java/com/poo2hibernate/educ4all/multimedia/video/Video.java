package com.poo2hibernate.educ4all.multimedia.video;

import com.poo2hibernate.educ4all.lesson.Lesson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long videoId;
    private String name;
    private String location;
    @ManyToOne
    @JoinColumn(name = "leconId")
    private Lesson lesson;

    public Video(String name, String location, Lesson lesson) {
        this.name = name;
        this.location = location;
        this.lesson = lesson;
    }
}
