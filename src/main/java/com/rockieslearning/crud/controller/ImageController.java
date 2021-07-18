package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.message.ResponseMessage;
import com.rockieslearning.crud.service.FirebaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Controller
@RequestMapping("/api/test2")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {

    @Autowired
    FirebaseImageService firebaseImageService;


    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestParam(name = "files") MultipartFile[] files) {

//        for (MultipartFile file : files) {
//
//            try {
//
//                String fileName = imageService.save(file);
//
//                String imageUrl = imageService.getImageUrl(fileName);
//
//                // do whatever you want with that
//
//            } catch (Exception e) {
//                //  throw internal error;
//            }
//        }





        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {

                try {

                    System.out.println(file.getContentType());

                    fileNames.add(firebaseImageService.save(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!" + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

}
