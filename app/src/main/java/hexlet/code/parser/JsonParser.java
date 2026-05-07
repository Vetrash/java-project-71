package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonParser {

    public static Map<String, Object> paprse(String jsonContent) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonContent, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Ошибка парсинга JSON: " + e.getMessage());
        }
    }

}
