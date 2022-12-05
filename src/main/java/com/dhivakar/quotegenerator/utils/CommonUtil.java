package com.dhivakar.quotegenerator.utils;

import com.dhivakar.quotegenerator.model.ImageVO;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class CommonUtil {

    private static final String IMAGE_PREFIX = "QT_IMAGE_";
    private static String EXTENSION = ".jpg";
    @Value("${image.base.filepath}")
    private String imagePath;

    //TODO : Make a Init loader to dynamically update range
    private static final int RAND_RANGE = 51;

    private SecureRandom random = new SecureRandom();

    public String downloadImage(String url) throws FileNotFoundException {

        //TODO : Make a check for url

        String fileName = generateFileName().trim();

        AsyncHttpClient client = Dsl.asyncHttpClient();

        FileOutputStream stream = new FileOutputStream(imagePath + fileName);

        client.prepareGet(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .execute(new AsyncCompletionHandler<FileOutputStream>() {

                    @Override
                    public State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                            throws Exception {
                        stream.getChannel().write(bodyPart.getBodyByteBuffer());
                        return State.CONTINUE;
                    }

                    @Override
                    public FileOutputStream onCompleted(Response response)
                            throws Exception {
                        return stream;
                    }
                });

        return fileName;
    }


    private String generateFileName() {

        return IMAGE_PREFIX + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + EXTENSION;
    }

    public ImageVO getFileArray(String fileName) throws IOException {

        return ImageVO.builder()
                .fileContent(Files.readAllBytes(Paths.get(imagePath + fileName)))
                .fileName(fileName)
                .build();
    }

    public int getRandom() {

        return random.nextInt(RAND_RANGE);


    }

}
