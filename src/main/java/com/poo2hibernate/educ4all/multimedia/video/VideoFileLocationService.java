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

}
