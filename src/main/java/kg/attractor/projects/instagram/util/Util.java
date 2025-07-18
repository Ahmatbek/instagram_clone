package kg.attractor.projects.instagram.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@UtilityClass
public class Util {
    private static final String DIRECTORY = "data/pictures";

    @SneakyThrows
    public static String uploadResource(MultipartFile file) {
        if (file == null)
            throw new IllegalArgumentException("multipart file is null");

        String fileName = getUniqueGeneratedValue() + "_" + file.getOriginalFilename();
        Path directoryPath = Paths.get(DIRECTORY);

        if (!Files.isDirectory(directoryPath))
            Files.createDirectories(directoryPath);

        Path filePath = Paths.get(DIRECTORY, fileName);

        if (!Files.exists(filePath))
            Files.createFile(filePath);

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public static String getUniqueGeneratedValue() {
        return UUID.randomUUID().toString();
    }

    @SneakyThrows
    public static ResponseEntity<Object> getOutputResource(String filename, MediaType mediaType) {
        try {
            byte[] image = Files.readAllBytes(Paths.get(DIRECTORY + "/" + filename));
            Resource resource = new ByteArrayResource(image);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);
        } catch (NoSuchFileException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("File not found");
        }
    }

    public static MediaType defineFileType(String filePath) throws IOException {
        String fileType = Files.probeContentType(Paths.get(filePath));
        return MediaType.parseMediaType(fileType);
    }

    public static boolean isPasswordByCrypt(String password) {
        return Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}").matcher(password).matches();
    }
}
