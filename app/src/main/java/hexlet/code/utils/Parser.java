package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {


    public static Map<String, Object> json(String jsonContent) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonContent, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Ошибка парсинга JSON: " + e.getMessage());
        }
    }


    public static Map<String, Object> yaml(String yamlContent) throws IOException {
        var mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(yamlContent, new TypeReference<Map<String, Object>>() {
        });
    }

    public static Map<String, Object> parse(String content, String format) throws IOException {

        switch (format) {
            case "json":
                return json(content);
            case "yaml":
                return yaml(content);
            default:
                throw new RuntimeException("Unavailable format to parse" + format);

        }
    }

}
