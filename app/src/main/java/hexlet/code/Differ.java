package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.utils.Comparison;
import hexlet.code.utils.FileReader;
import hexlet.code.utils.Parser;

import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var formatFile1 = FileReader.getFileExtension((filePath1));
        var file1Context = FileReader.read(filePath1);
        Map<String, Object> file1 = Parser.parse(file1Context, formatFile1);

        var formatFile2 = FileReader.getFileExtension((filePath2));
        var file2Context = FileReader.read(filePath2);
        Map<String, Object> file2 = Parser.parse(file2Context, formatFile2);

        var diffsData = Comparison.getDiff(file1, file2);
        String formatingDiff = Formatter.formatting(diffsData, format);
        return formatingDiff;
    }
}
