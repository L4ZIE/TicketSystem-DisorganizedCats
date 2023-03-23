package pl.models;

import be.Event;
import bll.EventManager;
import javafx.collections.ObservableList;

public class EventModel {

    EventManager eventManager;
    public EventModel(){
        eventManager = new EventManager();
    }

    public ObservableList<Event> getAllEvents(){
        //waiting for backend
        return null;
    }
    public Event getEventByID(int id){
        //waiting for backend
        return null;
    }
    public ObservableList<Event> getEventByAccount(){
        //sprint 2
        return null;
    }
    public ObservableList<Event> getEventsByStartDate(String date){
        //waiting for backend
        return null;
    }
    public ObservableList<Event> getEventsByEndDate(String date){
        //waiting for backend
        return null;
    }
    public ObservableList<Event> getEventsByName(String name){
        //waiting for backend
        return null;
    }


}
