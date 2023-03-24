package pl.models;

import be.Event;
import bll.EventManager;
import bll.interfaces.IEventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventModel {

    IEventManager eventManager;
    public EventModel(){
        eventManager = new EventManager();
    }

    public ObservableList<Event> getAllEvents(){

        return FXCollections.observableArrayList(eventManager.getAllEvents());
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


}
