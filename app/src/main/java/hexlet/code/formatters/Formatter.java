package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.models.Diffs;

import java.util.List;

public final class Formatter {

    private Formatter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String formatting(List<Diffs> diffData, String format) throws JsonProcessingException {


        switch (format) {
            case "plain":
                return PlainFormat.format(diffData);
            case "json":
                return JsonFormat.format(diffData);
            case "stylish":
                return StylishFormat.format(diffData);
            default:
                throw new RuntimeException("Unavailable functionality is selected " + format);
        }
    }
}
