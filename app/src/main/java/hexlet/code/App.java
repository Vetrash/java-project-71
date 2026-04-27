package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

import hexlet.code.utils.FileReader;
import hexlet.code.utils.Parser;
import hexlet.code.utils.Comparison;

import com.fasterxml.jackson.databind.JsonNode;

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
        // Создаем экземпляр App и передаем аргументы командной строки
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        var file1Context = FileReader.read(filepath1);
        JsonNode file1 = Parser.json(file1Context);
        var file2Context = FileReader.read(filepath2);
        JsonNode file2 = Parser.json(file2Context);

//        file2.fields().forEachRemaining(field -> {
//            System.out.println(field.getKey() + ": " + field.getValue());
//        });

        String ComparisonString = Comparison.flat(file1, file2);
        System.out.println("");
        System.out.println(ComparisonString);

        return 0;
    }
}
