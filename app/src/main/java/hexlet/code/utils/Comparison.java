package hexlet.code.utils;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

public class Comparison {

    public static List<Diffs> getDiff(Map<String, Object> file1, Map<String, Object> file2) {
        var diffData = new ArrayList<Diffs>();
        List<String> keys1 = new ArrayList<>(file1.keySet());
        List<String> keys2 = new ArrayList<>(file2.keySet());

        Set<String> set = new LinkedHashSet<>();
        set.addAll(keys1);
        set.addAll(keys2);
        ArrayList<String> allKeys = new ArrayList<>(set);
        Collections.sort(allKeys);

        for (String key : allKeys) {
            Object value1 = file1.get(key);
            Object value2 = file2.get(key);

            var event = Diffs.EventType.NOTCHANGED;

            if (value1 == null && value2 != null) {
                event = Diffs.EventType.ADDED; // Добавление значения (было только во втором файле)
            } else if (value1 != null && value2 == null) {
                event = Diffs.EventType.REMOVED;  // Удаление значения (было только в первом файле)
            } else if (value1 != null && value2 != null) {
                if (value1.equals(value2)) {
                    event = Diffs.EventType.NOTCHANGED;
                } else {
                    event = Diffs.EventType.CHANGED;
                }
            }
            var diff = new Diffs(key, value1, value2, event);
            diffData.add(diff);
        }
        return diffData;
    }
}
