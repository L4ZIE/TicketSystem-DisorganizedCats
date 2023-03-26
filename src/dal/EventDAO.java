package dal;

import be.Event;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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
        try {
            String sql = "SELECT * FROM Events";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                events.add(new Event(
                        resultSet.getInt("id"),
                        resultSet.getString("startDateTime"),
                        resultSet.getString("endDateTime"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("locationGuidance"),
                        resultSet.getString("notes"),
                        resultSet.getString("eventName")
                ));
            }
            return events;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createEvent(Event event) {
        try {
            String sql = "INSERT INTO Events (startDateTime,endDateTime,eventLocation,eventGuidance,locationGuidance,notes,eventName) VALUES (?,?,?,?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, event.getStartDateTime());
            preparedStatement.setString(2, event.getEndDateTime());
            preparedStatement.setString(3, event.getLocation());
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
