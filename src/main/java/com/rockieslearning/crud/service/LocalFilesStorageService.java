package com.rockieslearning.crud.service;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
/**
 * Created by TanVOD on Jul, 2021
 */
public interface LocalFilesStorageService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
