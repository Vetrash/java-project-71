package hexlet.code.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class Comparison {

    public static String flat(JsonNode file1, JsonNode file2) {

        List<String> keys1 = new ArrayList<>();
        List<String> keys2 = new ArrayList<>();
        file1.fieldNames().forEachRemaining(keys1::add);
        file2.fieldNames().forEachRemaining(keys2::add);

        Set<String> set = new LinkedHashSet<>();
        set.addAll(keys1);
        set.addAll(keys2);
        ArrayList<String> allKeys = new ArrayList<>(set);
        Collections.sort(allKeys);


        StringBuilder solution = new StringBuilder();

        solution.append("{");
        solution.append(System.lineSeparator());
        for (String key : allKeys) {
            var value1 = file1.get(key);
            var value2 = file2.get(key);
            //Добавление значения
            if (value1 == null && value2 != null) {
                solution.append("+ ");
                solution.append(key);
                solution.append(": ");
                solution.append(value2);
                solution.append(System.lineSeparator());
            }
            //Удаление значения
            if (value1 != null && value2 == null) {
                solution.append("- ");
                solution.append(key);
                solution.append(": ");
                solution.append(value1);
                solution.append(System.lineSeparator());
            }

            if (value1 != null && value2 != null) {

                if (value1.equals(value2)) {
                    solution.append("  ");
                    solution.append(key);
                    solution.append(": ");
                    solution.append(value1);
                    solution.append(System.lineSeparator());

                } else {
                    solution.append("- ");
                    solution.append(key);
                    solution.append(": ");
                    solution.append(value1);
                    solution.append(System.lineSeparator());

                    solution.append("+ ");
                    solution.append(key);
                    solution.append(": ");
                    solution.append(value2);
                    solution.append(System.lineSeparator());
                }

            }
        }
        ;
        solution.append("}");
        return solution.toString();
    }
}
