package com.rockieslearning.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rockieslearning.crud.entity.FileInfo;
import com.rockieslearning.crud.message.ResponseMessage;
import com.rockieslearning.crud.service.LocalFilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


/**
 * Created by TanVOD on Jul, 2021
 */

@Controller
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FilesController {

    @Autowired
    LocalFilesStorageService storageService;



//    @PostMapping("/profile/pic")
//    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
//        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
//        return fileService.upload(multipartFile);
//    }
//
//    @PostMapping("/profile/pic/{fileName}")
//    public Object download(@PathVariable String fileName) throws IOException {
//        logger.info("HIT -/download | File Name : {}", fileName);
//        return fileService.download(fileName);
//    }



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                storageService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
