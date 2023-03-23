package dal;

import be.Event;
import dal.connector.DataBaseConnector;
import dal.interfaces.IEventDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EventDAO implements IEventDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<Event> getAllEvents() {

        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";

        try {
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) ;

            events.add(new Event(
                    resultSet.getInt("id"),
                    resultSet.getString("starDateTime"),
                    resultSet.getString("endDateTime"),
                    resultSet.getString("eventLocation"),
                    resultSet.getString("locationGuide"),
                    resultSet.getString("notes"),
                    resultSet.getString("eventName")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
    @Override
    public void createEvent(Event event) {
        try {
            String sql = "INSERT INTO Events (eventName, startDateTime, endDateTime, eventLocation, locationGuidance, notes) VALUES (?,?,?,?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, event.getStartDateTime());
            preparedStatement.setString(2, event.getEndDateTime());
            preparedStatement.setString(3, event.getEventLocation());
            preparedStatement.setString(4, event.getLocationGuidance());
            preparedStatement.setString(5, event.getNotes());
            preparedStatement.setString(6,event.getEventName());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteEvent(int id) {
        try {
            String sql = "DELETE FROM Events WHERE id= ?";
            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateEvent(Event event) {
        try {
            String sql = "UPDATE Events" +
                    "SET startDateTime = ?" +
                    "SET endDateTime = ?" +
                    "SET eventLocation = ?" +
                    "SET locationGuidance = ?" +
                    "SET notes = ?" +
                    "SET eventName" +
                    "WHERE id = ?";

            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, event.getStartDateTime());
            preparedStatement.setString(2, event.getEndDateTime());
            preparedStatement.setString(3, event.getEventLocation());
            preparedStatement.setString(4, event.getLocationGuidance());
            preparedStatement.setString(5, event.getNotes());
            preparedStatement.setString(6, event.getEventName());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Event getEventByEventName(String eventName) {
        Event result = null;
        try {
        String sql = "SELECT * FROM Event WHERE eventName = ?";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

        preparedStatement.setString(1, eventName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            result = new Event(
                    resultSet.getInt("id"),
                    resultSet.getString("startDateTime"),
                    resultSet.getString("endDateTime"),
                    resultSet.getString("eventLocation"),
                    resultSet.getString("locationGuidance"),
                    resultSet.getString("notes"),
                    resultSet.getString("eventName")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
