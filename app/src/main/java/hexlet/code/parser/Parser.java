package hexlet.code.parser;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String format) throws IOException {

        switch (format) {
            case "json":
                return JsonParser.paprse(content);
            case "yaml":
            case "yml":
                return YamlParser.parse(content);
            default:
                throw new RuntimeException("Unavailable format to parse " + format);

        }
    }

}
