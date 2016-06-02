package ir.pegahtech.backtorysample.models;

import java.util.List;

/**
 * Created by SirGozal on 6/1/2016.
 */
public class EventRequest {
    private String eventName;
    private List<EventField> fieldsAndValues;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<EventField> getFieldsAndValues() {
        return fieldsAndValues;
    }

    public void setFieldsAndValues(List<EventField> fieldsAndValues) {
        this.fieldsAndValues = fieldsAndValues;
    }
}
