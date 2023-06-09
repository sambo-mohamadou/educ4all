package com.poo2hibernate.educ4all.multimedia.video;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class VideoFileSystemRepository {
    private String RESOURCES_DIR = System.getProperty("user.dir") + "/src/main/resources/videoFiles/";

    public String saveVideo(byte[] content, String videoName, Long lessonId) throws IOException {
        Path newFile = Paths.get(RESOURCES_DIR  + "lesson"+lessonId +"-" +  new Date().getTime() + "-"+ videoName);

        Files.createDirectories(newFile.getParent());

        Files.write(newFile,content);

        return newFile.toAbsolutePath().toString();
    }

    public FileSystemResource findVideoInFileSystem(String location){
        try {
            return new FileSystemResource(Paths.get(location));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
