package hexlet.code.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class FileReader {


    public static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        return filePath.substring(lastDotIndex + 1).toLowerCase();
    }


    public static String read(String filePath) {
        try {
            // Читаем все содержимое файла в строкуPath
            Path path = Paths.get(System.getProperty("user.dir"), filePath);
            String content = new String(Files.readAllBytes(path));
            return content;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }

    }

}


