package hexlet.code.formatters;

import hexlet.code.utils.Comparison;
import hexlet.code.utils.Diffs;
import hexlet.code.utils.FileReader;
import hexlet.code.utils.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FormatterTest {
    private List<Diffs> diffsData;

    @BeforeEach
    void setUp() throws IOException {
        // Получаем формат и содержимое file1
        var formatFile1 = FileReader.getFileExtension("src/test/resources/fixtures/file1.yaml");
        var file1Context = FileReader.read("src/test/resources/fixtures/file1.yaml");
        var file1 = Parser.parse(file1Context, formatFile1);

        // Получаем формат и содержимое file2
        var formatFile2 = FileReader.getFileExtension("src/test/resources/fixtures/file2.yaml");
        var file2Context = FileReader.read("src/test/resources/fixtures/file2.yaml");
        var file2 = Parser.parse(file2Context, formatFile2);

        // Получаем diff между файлами
        diffsData = Comparison.getDiff(file1, file2);
    }


    @Test
    @DisplayName("Проверка форматера plain")
    void testPlain() throws IOException {
        String format = "plain";
        String actual = Formatter.formatting(diffsData, format);
        String expected = """
                Property 'follow' was removed
                Property 'proxy' was removed
                Property 'timeout' was updated. From 50 to 20
                Property 'verbose' was added with value: true
                """.trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка форматера JSON")
    void testJson() throws IOException {
        String format = "json";
        String actual = Formatter.formatting(diffsData, format);
        String expected = """
                    [ {
                      "event" : "REMOVED",
                      "key" : "follow",
                      "oldValue" : false,
                      "newValue" : "empty"
                    }, {
                      "event" : "NOTCHANGED",
                      "key" : "host",
                      "oldValue" : "hexlet.io",
                      "newValue" : "hexlet.io"
                    }, {
                      "event" : "REMOVED",
                      "key" : "proxy",
                      "oldValue" : "123.234.53.22",
                      "newValue" : "empty"
                    }, {
                      "event" : "CHANGED",
                      "key" : "timeout",
                      "oldValue" : 50,
                      "newValue" : 20
                    }, {
                      "event" : "ADDED",
                      "key" : "verbose",
                      "oldValue" : "empty",
                      "newValue" : true
                    } ]
                """.trim();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }


    @Test
    @DisplayName("Проверка форматера stylish")
    void testStylish() throws IOException {
        String format = "stylish";
        String actual = Formatter.formatting(diffsData, format);
        String expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }
                """.trim();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    @DisplayName("Проверка форматера на неизвестный формат")
    void testUnknown() throws IOException {
        String format = "unknown";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Formatter.formatting(diffsData, format);
        });
        assertTrue(exception.getMessage().contains("Unavailable functionality is selected"));
        assertTrue(exception.getMessage().contains(format));
    }
}
