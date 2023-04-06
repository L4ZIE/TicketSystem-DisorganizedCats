package pl.models;

import be.Event;
import be.SpecialTicket;
import bll.EventManager;
import bll.interfaces.IEventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {

    IEventManager eventManager;
    public EventModel(){
        eventManager = new EventManager();
    }
    private ObservableList<Event> events;

    public ObservableList<Event> getAllEvents(){
        return events = FXCollections.observableArrayList(eventManager.getAllEvents());
    }
    public void createEvent(Event event) {
        eventManager.createEvent(event);
        events.add(event);
    }
    public void deleteEvent(Event event){
        eventManager.deleteEvent(event.getId());
        events.remove(events.indexOf(event));
    }

    public void updateEvent(Event event){
        eventManager.updateEvent(event);
        events = getAllEvents();
    }
    public void searchForEvent(String query) {
        List<Event> filtered = eventManager.searchForEvent(query,eventManager.getAllEvents());
        events.clear();
        events.addAll(filtered);
    }

    public Event getEventByID(int id){
        return eventManager.getEventByID(id);
    }

    public ObservableList<Event> getEventByAccount(){
        //sprint 2
        return null;
    }
    public ObservableList<Event> getEventsByStartDate(String date){
        return FXCollections.observableArrayList(eventManager.getEventsByStartDate(date));
    }
    public ObservableList<Event> getEventsByEndDate(String date){
        return FXCollections.observableArrayList(eventManager.getEventsByEndDate(date));
    }
    public ObservableList<Event> getEventsByName(String name){
        return FXCollections.observableArrayList(eventManager.getEventsByName(name));
    }
    public int getMaxID(){
        return eventManager.getMaxID();
    }

    public ObservableList<Event> getEventsBySpecTicketID(int id) {
        return FXCollections.observableArrayList(eventManager.getEventsBySpecTicketID(id));
    }
}
