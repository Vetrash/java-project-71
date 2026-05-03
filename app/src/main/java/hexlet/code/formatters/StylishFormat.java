package hexlet.code.formatters;

import hexlet.code.utils.Diffs;

import java.util.List;

public class StylishFormat {
    public static String format(List<Diffs> diffData) {
        StringBuilder solution = new StringBuilder();

        solution.append("{");
        solution.append(System.lineSeparator());

        diffData.forEach(diffs -> {
            switch (diffs.event) {
                case ADDED:
                    solution.append("+ ");
                    solution.append(diffs.key);
                    solution.append(": ");
                    solution.append(diffs.newValue);
                    solution.append(System.lineSeparator());
                    break;
                case REMOVED:
                    solution.append("- ");
                    solution.append(diffs.key);
                    solution.append(": ");
                    solution.append(diffs.oldValue);
                    solution.append(System.lineSeparator());
                    break;
                case CHANGED:
                    solution.append("- ");
                    solution.append(diffs.key);
                    solution.append(": ");
                    solution.append(diffs.oldValue);
                    solution.append(System.lineSeparator());

                    solution.append("+ ");
                    solution.append(diffs.key);
                    solution.append(": ");
                    solution.append(diffs.newValue);
                    solution.append(System.lineSeparator());
                    break;
                case NOTCHANGED:
                    solution.append("  ");
                    solution.append(diffs.key);
                    solution.append(": ");
                    solution.append(diffs.oldValue);
                    solution.append(System.lineSeparator());
                    break;
                default:
                    throw new RuntimeException("Unavailable fevent" + diffs.event);
            }

        });
        solution.append("}");
        return solution.toString();
    }
}
