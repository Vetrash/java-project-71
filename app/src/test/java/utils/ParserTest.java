package utils;

import hexlet.code.utils.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {


    @Test
    @DisplayName("Парсинг JSON")
    void testJsonParsing() throws IOException {
        String format = "json";
        String content = """
                {
                  "setting1": "Some value",
                  "setting2": 200,
                  "setting3": true,
                  "numbers1": [
                    1,
                    2,
                    3,
                    4
                  ],
                  "default": null
                }
                """;
        Map<String, Object> result = Parser.parse(content, format);
        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("Some value", result.get("setting1"));
        assertEquals(200, result.get("setting2"));
        assertEquals(true, result.get("setting3"));

        Object numbersObj = result.get("numbers1");
        assertInstanceOf(List.class, numbersObj);
        List<Integer> numbersJson = (List<Integer>) numbersObj;
        assertEquals(List.of(1, 2, 3, 4), numbersJson);
        assertEquals(null, result.get("default"));

    }

    @Test
    @DisplayName("Парсинг YAML")
    void testYamlParsing() throws IOException {


        String format = "yaml";
        String content = """
                setting1: Some value
                setting2: 200
                setting3: true
                numbers1:
                  - 1
                  - 2
                  - 3
                  - 4
                default: null
                """;

        Map<String, Object> result = Parser.parse(content, format);
        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("Some value", result.get("setting1"));
        assertEquals(200, result.get("setting2"));
        assertEquals(true, result.get("setting3"));
        Object numbersObj = result.get("numbers1");
        assertInstanceOf(List.class, numbersObj);
        List<Integer> numbersJson = (List<Integer>) numbersObj;
        assertEquals(List.of(1, 2, 3, 4), numbersJson);
        assertEquals(null, result.get("default"));
    }

    @Test
    @DisplayName("Парсинг неизвестного формата")
    void testWrongParsing() throws IOException {
        String content = "";
        String format = "xml";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Parser.parse(content, format);
        });
        assertTrue(exception.getMessage().contains("Unavailable format to parse"));
        assertTrue(exception.getMessage().contains(format));
    }
}
