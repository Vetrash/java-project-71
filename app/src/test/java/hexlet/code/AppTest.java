package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testCallMethodWithError() throws Exception {
        // Несуществующие файлы
        App app = new App();
        app.filepath1 = "nonexistent1.json";
        app.filepath2 = "nonexistent2.json";
        app.format = "stylish";

        // Ожидаем исключение
        assertThrows(Exception.class, () -> {
            app.call();
        });
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

    @Test
    void testAppWithNonExistentFiles() {
        App app = new App();
        app.filepath1 = "non_existent_file1.json";
        app.filepath2 = "non_existent_file2.json";

        // Проверяем, что метод выбрасывает исключение при отсутствии файлов
        assertThrows(Exception.class, () -> {
            app.call();
        });
    }
}
