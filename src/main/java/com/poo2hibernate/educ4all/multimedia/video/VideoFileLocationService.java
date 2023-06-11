package com.poo2hibernate.educ4all.multimedia.video;

import com.poo2hibernate.educ4all.lesson.Lesson;
import com.poo2hibernate.educ4all.lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class VideoFileLocationService {
    private final VideoFileSystemRepository videoFileSystemRepository;
    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public VideoFileLocationService(VideoFileSystemRepository videoFileSystemRepository, VideoRepository videoRepository, LessonRepository lessonRepository) {
        this.videoFileSystemRepository = videoFileSystemRepository;
        this.videoRepository = videoRepository;
        this.lessonRepository = lessonRepository;
    }

    public Long saveVideo(byte[] content, String videoName, Long lessonId) throws IOException {
        Lesson lesson = lessonRepository.getReferenceById(lessonId);
        String location = videoFileSystemRepository.saveVideo(content,videoName,lessonId);

        return videoRepository.save(new Video(videoName, location, lesson)).getVideoId();
    }

    public FileSystemResource findVideo(String name){
        Video video = videoRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return videoFileSystemRepository.findVideoInFileSystem(video.getLocation());
    }


    public List<String> getAllVideoNames() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(p -> p.getName())
                              .toList() ;
    }

    public List<String> getAllVideoNamesByLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return videoRepository.findByLesson(lesson).stream().map(p -> p.getName())
                                                            .toList();
    }

    public void updateVideo(long videoId, String name, Lesson lesson) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(name != null && name.length() > 0 && !Objects.equals(name, video.getName())) {
            video.setName(name);
        }

        if(lesson != null && !Objects.equals(lesson, video.getLesson())) {
            video.setLesson(lesson);
        }
    }
}
