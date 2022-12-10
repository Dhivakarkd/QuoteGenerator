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
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CommonUtil {

    public static final String RECORD_COUNT = "RecordCount";

    private static final String IMAGE_PREFIX = "QT_IMAGE_";
    private static final LinkedHashMap<String, Integer> globalHashMap = new LinkedHashMap<>();

    private static final String EXTENSION = ".jpg";

    // Regular expression to match URLs
    private static final String URL_REGEX = "^(https?://)?(www\\.)?([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,}(/.*)?$";
    private final SecureRandom random = new SecureRandom();
    @Value("${image.base.filepath}")
    private String IMAGE_PATH;

    public void addValueToGlobalHash(String key, int value) {
        globalHashMap.put(key, value);
    }

    public String downloadImage(String url) throws FileNotFoundException {

        //TODO : Make a check for url

        String fileName = generateFileName().trim();

        AsyncHttpClient client = Dsl.asyncHttpClient();

        FileOutputStream stream = new FileOutputStream(IMAGE_PATH + fileName);

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
                .fileContent(Files.readAllBytes(Paths.get(IMAGE_PATH + fileName)))
                .fileName(fileName)
                .build();
    }

    public boolean isValidURL(String urlString) {
        // Compile the regular expression
        Pattern pattern = Pattern.compile(URL_REGEX);

        // Match the given string against the regular expression
        Matcher matcher = pattern.matcher(urlString);

        // Return the result of the match
        return matcher.matches();
    }


    public int getRandom() {

        return random.nextInt(globalHashMap.get(RECORD_COUNT));

    }

}
