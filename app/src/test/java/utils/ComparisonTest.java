package utils;

import hexlet.code.utils.Comparison;
import hexlet.code.utils.Diffs;
import hexlet.code.utils.FileReader;
import hexlet.code.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonTest {
    @Test
    @DisplayName("Поиск отличий")
    void diffJson() throws IOException {
        var formatFile1 = FileReader.getFileExtension("src/test/resources/fixtures/file1.yaml");
        var file1Context = FileReader.read("src/test/resources/fixtures/file1.yaml");

        Map<String, Object> file1 = Parser.parse(file1Context, formatFile1);


        var formatFile2 = FileReader.getFileExtension("src/test/resources/fixtures/file2.yaml");
        var file2Context = FileReader.read("src/test/resources/fixtures/file2.yaml");
        Map<String, Object> file2 = Parser.parse(file2Context, formatFile2);

        var actual = Comparison.getDiff(file1, file2);

        List<Diffs> expected = List.of(
                new Diffs("follow", false, null, Diffs.EventType.REMOVED),
                new Diffs("host", "hexlet.io", "hexlet.io", Diffs.EventType.NOTCHANGED),
                new Diffs("proxy", "123.234.53.22", null, Diffs.EventType.REMOVED),
                new Diffs("timeout", 50, 20, Diffs.EventType.CHANGED),
                new Diffs("verbose", null, true, Diffs.EventType.ADDED)
        );

        assertIterableEquals(expected, actual);
    }
}
