package hexlet.code.formatters;

import hexlet.code.models.Diffs;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormat {

    private static <T> String getValueString(T value) {
        if (value == null) {
            return "null";
        }

        var classObj = value.getClass();

        // Составные типы
        if (classObj.isArray() || value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        }

        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        }

        return "\'" + value + "\'";
    }

    public static String format(List<Diffs> diffData) {
        StringBuilder solution = new StringBuilder();

        diffData.forEach(diffs -> {
            switch (diffs.getEvent()) {
                case ADDED:
                    solution.append("Property \'");
                    solution.append(diffs.getKey());
                    solution.append("\' was added with value: ");
                    solution.append(getValueString(diffs.getNewValue()));
                    solution.append(System.lineSeparator());
                    break;
                case REMOVED:
                    solution.append("Property \'");
                    solution.append(diffs.getKey());
                    solution.append("\' was removed");
                    solution.append(System.lineSeparator());
                    break;
                case CHANGED:
                    solution.append("Property \'");
                    solution.append(diffs.getKey());
                    solution.append("\' was updated. From ");
                    solution.append(getValueString(diffs.getOldValue()));
                    solution.append(" to ");
                    solution.append(getValueString(diffs.getNewValue()));

                    solution.append(System.lineSeparator());

                    break;
                case NOTCHANGED:
                    break;
                default:
                    throw new RuntimeException("Unavailable fevent" + diffs.getEvent());
            }

        });
        return solution.toString().trim();
    }
}
