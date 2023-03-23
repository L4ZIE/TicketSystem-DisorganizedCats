package be;

public class Event {

    private int id;
    private String startDateTime;
    private String endDateTime;
    private  String location;
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location){
        this.location = location;
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
    public void setTitle(String title){this.eventName = eventName;}
    //endregion

    public Event(int id,String startDateTime, String endDateTime, String location, String locationGuidance, String notes){
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.notes = notes;
    }
    @Override
    public String toString() {
        return id + " " + startDateTime + " " + endDateTime + " " + location + " " + locationGuidance + " " + notes;
    }
}
