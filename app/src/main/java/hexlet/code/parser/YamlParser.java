package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class YamlParser {
    public static Map<String, Object> parse(String yamlContent) throws IOException {
        var mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(yamlContent, new TypeReference<Map<String, Object>>() {
        });
    }

}
