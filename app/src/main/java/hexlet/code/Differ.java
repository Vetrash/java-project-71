package hexlet.code;

import hexlet.code.files.File;
import hexlet.code.formatters.Formatter;
import hexlet.code.comparison.Comparison;
import hexlet.code.files.FileReader;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var formatFile1 = File.getFileExtension((filePath1));
        var file1Context = File.read(filePath1);
        Map<String, Object> file1 = Parser.parse(file1Context, formatFile1);

        var formatFile2 = File.getFileExtension((filePath2));
        var file2Context = FileReader.read(filePath2);
        Map<String, Object> file2 = Parser.parse(file2Context, formatFile2);

        var diffsData = Comparison.getDiff(file1, file2);
        String formatingDiff = Formatter.formatting(diffsData, format);
        return formatingDiff;
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
}
