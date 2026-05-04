package hexlet.code.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderTest {

    @Test
    @DisplayName("чтение файла json")
    void readJson() throws JsonProcessingException {
        String filePath = "src/test/resources/fixtures/file1.json";
        String expectedJson = """
                {
                  "setting1": "Some value",
                  "setting2": 200,
                  "setting3": true,
                  "key1": "value1",
                  "numbers1": [
                    1,
                    2,
                    3,
                    4
                  ],
                  "numbers2": [
                    2,
                    3,
                    4,
                    5
                  ],
                  "id": 45,
                  "default": null,
                  "checked": false,
                  "numbers3": [
                    3,
                    4,
                    5
                  ],
                  "chars1": [
                    "a",
                    "b",
                    "c"
                  ],
                  "chars2": [
                    "d",
                    "e",
                    "f"
                  ]
                }
                """;
        String actualJson = FileReader.read(filePath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.readTree(expectedJson);
        JsonNode actualNode = mapper.readTree(actualJson);

        assertEquals(expectedNode, actualNode);


    }

    @Test
    @DisplayName("чтение файла yaml")
    void readYaml() throws Exception {
        String filePath = "src/test/resources/fixtures/file1.yaml";
        String expectedYaml = """
                host: hexlet.io
                timeout: 50
                proxy: 123.234.53.22
                follow: false
                """;

        String actualYaml = FileReader.read(filePath);

        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

        Object expectedNode = yamlMapper.readValue(expectedYaml, Object.class);
        Object actualNode = yamlMapper.readValue(actualYaml, Object.class);

        assertEquals(expectedNode, actualNode);
    }


    @Test
    @DisplayName("Отсуствие файла")
    void testWrongParsing() throws IOException {
        String filePath = "src/test/resources/fixtures/file1.xml";


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            FileReader.read(filePath);
        });
        assertTrue(exception.getMessage().contains("Ошибка при чтении файла"));
    }
}
