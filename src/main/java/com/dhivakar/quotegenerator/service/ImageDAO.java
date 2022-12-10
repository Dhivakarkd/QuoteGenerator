package com.dhivakar.quotegenerator.service;

import com.dhivakar.quotegenerator.model.ImageDO;
import com.dhivakar.quotegenerator.repository.ImageRepository;
import com.dhivakar.quotegenerator.utils.CommonUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class ImageDAO {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Getter
    private int count = 0;

    @PostConstruct
    public void initLoad() {
        count = (int) imageRepository.count();
        log.info("Init Load : {} Images found in DB", count);
        commonUtil.addValueToGlobalHash(CommonUtil.RECORD_COUNT, count);
    }


    public void insertImageMETAToTable(MultipartFile file) {


        ImageDO image = ImageDO.builder()
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .build();


        imageRepository.save(image);

        log.info("Saved Image Meta-Data to DB");
    }

    public void insertDownloadedData(String fileName, String url) {


        ImageDO image = ImageDO.builder()
                .fileName(fileName)
                .imageUrl(url)
                .build();


        imageRepository.save(image);

        log.info("Saved Image Meta-Data to DB");
    }

    public Optional<ImageDO> getImageByID(int id) {

        log.info("Fetching DB for id : {}", id);

        return imageRepository.findById(id);
    }


}
