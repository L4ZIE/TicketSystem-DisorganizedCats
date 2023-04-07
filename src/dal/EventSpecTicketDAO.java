package dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connector.DataBaseConnector;
import dal.interfaces.IEventSpecTicketDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventSpecTicketDAO implements IEventSpecTicketDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<Integer> getSpecTicketsByEventID(int eventID) {
        List<Integer> result = new ArrayList<>();
        try {
            String sql = "SELECT ticketID FROM Event_SpecTickets WHERE eventID = ? ";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setInt(1, eventID);
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
    public List<Integer> getEventForSpecTickets(int ticketID) {
        List<Integer> result = new ArrayList<>();
        try{
            String sql = "SELECT eventID FROM Event_SpecTickets WHERE ticketID = ?";

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
    public void addSpecTicketToEvent(int ticketID, int eventID) {
        try{
            String sql = "INSERT INTO Event_SpecTickets (ticketID, eventID) VALUES (?,?)";

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
 @Override
    public void addEventsToSpecTicket(int ticketID, int eventID) {
        try{
            String sql = "INSERT INTO Event_SpecTickets (ticketID, eventID) VALUES (?,?)";

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
