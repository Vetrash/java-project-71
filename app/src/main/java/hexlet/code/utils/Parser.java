package hexlet.code.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class Parser{

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static JsonNode json(String jsonContent) {
        try {
            return objectMapper.readTree(jsonContent);
        } catch (Exception e) {
            System.err.println("Ошибка парсинга JSON: " + e.getMessage());
            return null;
        }
    }


}
