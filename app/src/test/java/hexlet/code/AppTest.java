package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testExecuteMethodWithFixtures() {
        String filepath1 = "src/test/resources/fixtures/file1.yaml";
        String filepath2 = "src/test/resources/fixtures/file2.yaml";

        // Создаем экземпляр App
        App app = new App();
        String[] args = {filepath1, filepath2, "-f", "stylish"};

        // Выполняем команду
        int exitCode = new CommandLine(app).execute(args);

        // Проверяем результат
        assertEquals(0, exitCode, "Exit code should be 0 for successful execution");
    }

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
