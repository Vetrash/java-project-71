package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.utils.Diffs;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonFormat {
    public static String format(List<Diffs> diffData) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffData);

        return json;
    }


}
