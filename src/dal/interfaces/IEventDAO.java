package dal.interfaces;

import be.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents();

    void createEvent(Event event);

    void deleteEvent(int id);

    void updateEvent(Event event);
}
