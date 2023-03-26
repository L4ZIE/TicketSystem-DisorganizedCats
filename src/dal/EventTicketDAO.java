package dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connector.DataBaseConnector;
import dal.interfaces.IEventTicketDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventTicketDAO implements IEventTicketDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<Integer> getTicketsByEventID(int eventID) {
        List<Integer> result = new ArrayList<>();
        try{
            String sql = "SELECT ticketID FROM Event_Tickets WHERE eventID = ? ";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setInt(1,eventID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt("ticketID"));
            }
            return result;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Integer> getEventForTickets(int ticketID) {
        List<Integer> result = new ArrayList<>();
        try{
            String sql = "SELECT eventID FROM Event_Tickets WHERE ticketID = ?";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setInt(1,ticketID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt("eventID"));
            }
            return result;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addTicketToEvent(int ticketID, int eventID) {
        try{
            String sql = "INSERT INTO Event_Tickets (ticketID, eventID) VALUES (?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setInt(1,ticketID);
            preparedStatement.setInt(2,eventID);
            preparedStatement.execute();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

