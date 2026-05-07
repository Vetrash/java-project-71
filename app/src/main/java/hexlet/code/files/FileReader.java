package hexlet.code.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class FileReader {

    public static String read(String filePath) {
        try {
            Path path = Paths.get(filePath);
            // Если путь относительный, то резолвим относительно текущей директории
            if (!path.isAbsolute()) {
                path = Paths.get(System.getProperty("user.dir")).resolve(filePath);
            }
            String content = new String(Files.readAllBytes(path));
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
        }

    }


}


