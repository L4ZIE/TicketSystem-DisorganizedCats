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
        //done
        return null;
    }
    public Event getEventByID(int id){
        //done
        return null;
    }
    public ObservableList<Event> getEventByAccount(){
        //sprint 2
        return null;
    }
    public ObservableList<Event> getEventsByStartDate(String date){
        //done
        return null;
    }
    public ObservableList<Event> getEventsByEndDate(String date){
        //done
        return null;
    }
    public ObservableList<Event> getEventsByName(String name){
        //done
        return null;
    }


}
