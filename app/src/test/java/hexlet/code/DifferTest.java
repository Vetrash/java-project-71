package hexlet.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private final String fileJson1 = "src/test/resources/fixtures/files/file1.json";
    private final String fileJson2 = "src/test/resources/fixtures/files/file2.json";

    private final String fileYaml1 = "src/test/resources/fixtures/files/file1.yaml";
    private final String fileYaml2 = "src/test/resources/fixtures/files/file2.yaml";

    private final String fileWrong = "src/test/resources/fixtures/files/wrong.yaml";

    private String readExpectedResult(String filename) throws IOException {
        Path path = Path.of("src/test/resources/fixtures/expected/" + filename);
        return Files.readString(path).trim();
    }

    //JSON start
    @Test
    @DisplayName("Проверка JSON format default")
    void testJSONFormatDefault() throws IOException {
        String actual = Differ.generate(fileJson1, fileJson2);
        String expected = readExpectedResult("stylish_json.txt");
        System.out.println(expected);
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверка JSON format plain")
    void testJSONFormatPlain() throws IOException {
        String actual = Differ.generate(fileJson1, fileJson2, "plain");
        String expected = readExpectedResult("plain_json.txt");
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверка JSON format  stylish")
    void testJSONFormatStylish() throws IOException {
        String actual = Differ.generate(fileJson1, fileJson2, "stylish");
        String expected = readExpectedResult("stylish_json.txt");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка JSON format  JSON")
    void testJSONFormatJson() throws IOException {
        String actual = Differ.generate(fileJson1, fileJson2, "json");
        String expected = readExpectedResult("json_json.txt");
        assertEquals(expected, actual);
    }
    //JSON end


    //YAML start
    @Test
    @DisplayName("Проверка YAML format default")
    void testYAMLFormatDefault() throws IOException {
        String actual = Differ.generate(fileYaml1, fileYaml2);
        String expected = readExpectedResult("stylish_yaml.txt");
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверка YAML format plain")
    void testYAMLFormatPlain() throws IOException {
        String actual = Differ.generate(fileYaml1, fileYaml2, "plain");
        String expected = readExpectedResult("plain_yaml.txt");

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверка YAML format  stylish")
    void testYAMLFormatStylish() throws IOException {
        String actual = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expected = readExpectedResult("stylish_yaml.txt");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка YAML format  JSON")
    void testYAMLFormatJson() throws IOException {
        String actual = Differ.generate(fileYaml1, fileYaml2, "json");
        String expected = readExpectedResult("json_yaml.txt");
        assertEquals(expected, actual);
    }
    //YAML end

    //ERROR star
    @Test
    @DisplayName("Првоерка работы при отсуствии файла")
    void testWrongFile() throws IOException {


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(fileWrong, fileYaml2);
        });
        assertTrue(exception.getMessage().contains("Ошибка при чтении файла"));
    }

    @Test
    @DisplayName("Парсинг неизвестного формата")
    void testWrongParsing() throws IOException {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(fileYaml1, fileYaml2, "wrong");
        });
        assertTrue(exception.getMessage().contains("Unavailable functionality is selected"));
    }
    //ERROR end

}
