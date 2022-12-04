package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.ImageDO;
import com.dhivakar.quotegenerator.model.ImageVO;
import com.dhivakar.quotegenerator.service.ImageDAO;
import com.dhivakar.quotegenerator.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = ImageController.IMAGE_BASE_PATH)
@Slf4j
public class ImageController {

    public static final String IMAGE_BASE_PATH = "/image";

    @Value("${image.base.filepath}")
    private String imagePath;

    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private CommonUtil util;


    @GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageVO> getController() throws IOException {


        Optional<ImageDO> image = imageDAO.getImageByID(1);

        if (image.isPresent()) {
            return ResponseEntity.ok(util.getFileArray(image.get().getFileName()));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/file")
    public ResponseEntity<String> saveFile(@RequestParam("imageFile") MultipartFile file) throws IOException {

        log.info("File Name is {}", file.getOriginalFilename());

        FileUtils.writeByteArrayToFile(new File(imagePath + file.getOriginalFilename()), file.getBytes());

        imageDAO.insertImageMETAToTable(file);
        return ResponseEntity.ok("Its okay " + imagePath);
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveImage(@RequestParam("url") String url) throws IOException {

        String fileName = util.downloadImage(url);

        imageDAO.insertDownloadedData(fileName);

        return ResponseEntity.ok("Generated File : " + fileName);

    }
}
