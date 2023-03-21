package dal.interfaces;

import be.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents();

    void createEvent(Event event) throws SQLException;

    void deleteEvent(int id) throws SQLException;

    void updateEvent(Event event);
}
