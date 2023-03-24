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
    private final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<Event> getAllEvents() {

        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";

        try {
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                events.add(new Event(
                        resultSet.getInt("id"),
                        resultSet.getString("startDateTime"),
                        resultSet.getString("endDateTime"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("locationGuidance"),
                        resultSet.getString("notes"),
                        resultSet.getString("eventName")
                ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public void createEvent(Event event) {
        try {
            String sql = "INSERT INTO Events (startDateTime, endDateTime, location, locationGuidance, notes, eventName) VALUES (?,?,?,?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, event.getStartDateTime());
            preparedStatement.setString(2, event.getEndDateTime());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getLocationGuidance());
            preparedStatement.setString(5, event.getNotes());
            preparedStatement.setString(6, event.getEventName());

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
                    "SET location = ?" +
                    "SET locationGuidance = ?" +
                    "SET notes = ?" +
                    "SET eventName" +
                    "WHERE id = ?";

            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, event.getStartDateTime());
            preparedStatement.setString(2, event.getEndDateTime());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getLocationGuidance());
            preparedStatement.setString(5, event.getNotes());
            preparedStatement.setString(6, event.getEventName());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
