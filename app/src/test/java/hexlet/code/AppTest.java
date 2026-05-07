package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testAppHelpOption() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);

        // Тестируем опцию помощи
        int exitCode = cmd.execute("--help");

        assertEquals(0, exitCode);
    }

    @Test
    void testAppVersionOption() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);

        // Тестируем опцию версии
        int exitCode = cmd.execute("--version");

        assertEquals(0, exitCode);
    }
}
