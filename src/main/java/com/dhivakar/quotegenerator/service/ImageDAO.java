package com.dhivakar.quotegenerator.service;

import com.dhivakar.quotegenerator.model.ImageDO;
import com.dhivakar.quotegenerator.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageDAO {

    @Autowired
    ImageRepository imageRepository;


    public void insertImageMETAToTable(MultipartFile file) {


        ImageDO image = ImageDO.builder()
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .build();


        imageRepository.save(image);

        log.info("Saved Image Meta-Data to DB");
    }

    public void insertDownloadedData(String fileName) {


        ImageDO image = ImageDO.builder()
                .fileName(fileName)
                .build();


        imageRepository.save(image);

        log.info("Saved Image Meta-Data to DB");
    }
}
