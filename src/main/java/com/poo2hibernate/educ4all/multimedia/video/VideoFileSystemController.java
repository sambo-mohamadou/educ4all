package com.poo2hibernate.educ4all.multimedia.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "/educ4all")
public class VideoFileSystemController {
    VideoFileLocationService videoFileLocationService;
    @Autowired
    public VideoFileSystemController(VideoFileLocationService videoFileLocationService) {
        this.videoFileLocationService = videoFileLocationService;
    }
    @PostMapping("/gestion/video/upload/{lessonId}")
    public Long uploadVideo(@RequestParam("file") MultipartFile video,@PathVariable("lessonId") String lessonId) throws IOException {
        return videoFileLocationService.saveVideo(video.getBytes(), video.getOriginalFilename(), parseLong(lessonId));
    }
    @GetMapping(path = "user/stream/{name}")
    public ResponseEntity<Resource> getVideo(@PathVariable("name") String name) throws IOException {
        return ResponseEntity
                .ok(new ByteArrayResource(videoFileLocationService.findVideo(name).getContentAsByteArray()));
    }
    @GetMapping(path = "user/download/{name}")
    public FileSystemResource downloadVideo(@PathVariable("name") String name){
        return videoFileLocationService.findVideo(name);
    }

    @GetMapping("/gestion/video/all")
    public List<String> getAllVideoNames(){
        return (videoFileLocationService.getAllVideoNames());
    }

    @GetMapping("/user/video/{lessonId}")
    public List<String> getAllVideoNamesByLesson(@PathVariable("lessonId") String lessonId){
        return videoFileLocationService.getAllVideoNamesByLesson(parseLong(lessonId));
    }
}
