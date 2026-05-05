package hexlet.code.formatters;

import hexlet.code.utils.Diffs;

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
            switch (diffs.event) {
                case ADDED:
                    solution.append("Property \'");
                    solution.append(diffs.key);
                    solution.append("\' was added with value: ");
                    solution.append(getValueString(diffs.newValue));
                    solution.append(System.lineSeparator());
                    break;
                case REMOVED:
                    solution.append("Property \'");
                    solution.append(diffs.key);
                    solution.append("\' was removed");
                    solution.append(System.lineSeparator());
                    break;
                case CHANGED:
                    solution.append("Property \'");
                    solution.append(diffs.key);
                    solution.append("\' was updated. From ");
                    solution.append(getValueString(diffs.oldValue));
                    solution.append(" to ");
                    solution.append(getValueString(diffs.newValue));

                    solution.append(System.lineSeparator());

                    break;
                case NOTCHANGED:
                    break;
                default:
                    throw new RuntimeException("Unavailable fevent" + diffs.event);
            }

        });
        return solution.toString().trim();
    }
}
