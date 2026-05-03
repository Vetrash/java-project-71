package hexlet.code.utils;

public class Diffs {
    public EventType event;
    public String key;
    public Object oldValue;
    public Object newValue;


    public enum EventType {
        ADDED,
        REMOVED,
        CHANGED,
        NOTCHANGED,

    }

    Diffs(String key, Object oldValue, Object newValue, EventType event) {
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
}
