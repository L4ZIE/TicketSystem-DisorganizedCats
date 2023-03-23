package be;

public class Event {

    private int id;
    private String startDateTime;
    private String endDateTime;
    private  String eventLocation;
    private String locationGuidance;
    private String notes;
    private String eventName;
    //region getters and setter

    public int getId() {
        return id;
    }
    public String getStartDateTime(){
        return startDateTime;
    }
    public void setStartTimeDate(String startDateTime){this.startDateTime = startDateTime;}
    public String getEndDateTime(){
        return endDateTime;
    }
    public void setEndTimeDate(String endDateTime){
        this.endDateTime = endDateTime;
    }
    public String getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(String eventLocation){
        this.eventLocation = eventLocation;
    }
    public String getLocationGuidance(){
        return locationGuidance;
    }
    public void setLocationGuidance(String locationGuidance){this.locationGuidance = locationGuidance;}
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public String getEventName(){return eventName;}
    public void setTitle(String eventName){this.eventName = eventName;}
    //endregion

    public Event(int id, String startDateTime, String endDateTime, String eventLocation, String locationGuidance, String notes, String eventName){
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.eventLocation = eventLocation;
        this.locationGuidance = locationGuidance;
        this.notes = notes;
        this.eventName = eventName;
    }
    @Override
    public String toString() {
        return id + " " + startDateTime + " " + endDateTime + " " + eventLocation + " " + locationGuidance + " " + notes + " " + eventName;
    }
}
