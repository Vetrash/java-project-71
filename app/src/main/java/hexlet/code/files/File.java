package hexlet.code.files;

public class File {

    public static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        return filePath.substring(lastDotIndex + 1).toLowerCase();
    }

    public static String read(String filePath) {
        return FileReader.read(filePath);
    }

}
