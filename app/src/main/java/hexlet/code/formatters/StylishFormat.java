package hexlet.code.formatters;

import hexlet.code.models.Diffs;

import java.util.List;

public class StylishFormat {
    public static String format(List<Diffs> diffData) {
        StringBuilder solution = new StringBuilder();

        solution.append("{");
        solution.append(System.lineSeparator());

        diffData.forEach(diffs -> {
            switch (diffs.getEvent()) {
                case ADDED:
                    solution.append("  + ");
                    solution.append(diffs.getKey());
                    solution.append(": ");
                    solution.append(diffs.getNewValue());
                    solution.append(System.lineSeparator());
                    break;
                case REMOVED:
                    solution.append("  - ");
                    solution.append(diffs.getKey());
                    solution.append(": ");
                    solution.append(diffs.getOldValue());
                    solution.append(System.lineSeparator());
                    break;
                case CHANGED:
                    solution.append("  - ");
                    solution.append(diffs.getKey());
                    solution.append(": ");
                    solution.append(diffs.getOldValue());
                    solution.append(System.lineSeparator());

                    solution.append("  + ");
                    solution.append(diffs.getKey());
                    solution.append(": ");
                    solution.append(diffs.getNewValue());
                    solution.append(System.lineSeparator());
                    break;
                case NOTCHANGED:
                    solution.append("    ");
                    solution.append(diffs.getKey());
                    solution.append(": ");
                    solution.append(diffs.getOldValue());
                    solution.append(System.lineSeparator());
                    break;
                default:
                    throw new RuntimeException("Unavailable fevent" + diffs.getEvent());
            }

        });
        solution.append("}");
        return solution.toString();
    }
}
