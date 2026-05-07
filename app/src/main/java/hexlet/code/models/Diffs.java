package hexlet.code.models;

import java.util.Objects;

public final class Diffs {
    private EventType event;
    private String key;
    private Object oldValue;
    private Object newValue;


    public enum EventType {
        ADDED,
        REMOVED,
        CHANGED,
        NOTCHANGED,

    }

    public Diffs(String key, Object oldValue, Object newValue, EventType event) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.event = event;
    }

    public EventType getEvent() {
        return this.event;
    }

    public String getKey() {
        return this.key;
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        return this.newValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diffs diffs = (Diffs) o;
        return event == diffs.event
               && Objects.equals(key, diffs.key)
               && Objects.equals(oldValue, diffs.oldValue)
               && Objects.equals(newValue, diffs.newValue);
    }


    @Override
    public int hashCode() {
        return Objects.hash(event, key, oldValue, newValue);
    }


    @Override
    public String toString() {
        return String.format("Diffs{key='%s', old=%s, new=%s, event=%s}",
                key, oldValue, newValue, event);
    }

}
