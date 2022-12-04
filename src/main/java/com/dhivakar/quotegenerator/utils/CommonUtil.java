package com.dhivakar.quotegenerator.utils;

import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class CommonUtil {

    private static final String IMAGE_PREFIX = "QT_IMAGE_";
    private static String EXTENSION = ".jpg";
    @Value("${image.base.filepath}")
    private String imagePath;

/*    public String downloadImage(String url) throws IOException {

        String fileName = generateFileName();

        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        connection.connect();
        //TODO : Make a check for url
        try (InputStream in = connection.getInputStream()) {

            Files.copy(in, Paths.get(imagePath+fileName));

        }
        log.info("Image : {} downloaded", fileName);

        return fileName;
    }*/

    public String downloadImage(String url) throws FileNotFoundException {

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

}
