package hexlet.code;

import hexlet.code.formatters.Formatter;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.Map;
import java.util.concurrent.Callable;

import hexlet.code.utils.FileReader;
import hexlet.code.utils.Parser;
import hexlet.code.utils.Comparison;


@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean help;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    String format;


    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;


    public static void main(String[] args) {
        System.exit(execute(args));
    }

    public static int execute(String[] args) {
        return new CommandLine(new App()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        var formatFile1 = FileReader.getFileExtension((filepath1));
        var file1Context = FileReader.read(filepath1);
        Map<String, Object> file1 = Parser.parse(file1Context, formatFile1);

        var formatFile2 = FileReader.getFileExtension((filepath2));
        var file2Context = FileReader.read(filepath2);
        Map<String, Object> file2 = Parser.parse(file2Context, formatFile2);

        var diffsData = Comparison.getDiff(file1, file2);
        String formatingDiff = Formatter.formatting(diffsData, format);
        return 0;
    }
}
