package dal;

import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connector.DataBaseConnector;
import dal.interfaces.ITicketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicketDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();


    @Override
    public List<Ticket> getAllTickets() {
        try {
            List<Ticket> tickets;

            String sql = "SELECT * FROM Tickets";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            tickets = fillTickets(resultSet);

            return tickets;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> fillTickets(ResultSet resultSet) {
        try {
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerEmail"),
                        resultSet.getInt("ticketType"),
                        resultSet.getInt("ticketPrice"),
                        resultSet.getString("qrCode"),
                        resultSet.getByte("used")
                ));
            }
            return tickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTicket(Ticket ticket) {
        try {
            String sql = "INSERT INTO Tickets ( customerName, customerEmail, ticketType, ticketPrice, qrCode, used ) VALUES ( ?,?,?,?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, ticket.getCustomerName());
            preparedStatement.setString(2, ticket.getCustomerEmail());
            preparedStatement.setInt(3, ticket.getTicketType());
            preparedStatement.setInt(4, ticket.getTicketPrice());
            preparedStatement.setString(5, ticket.getQrCode());
            preparedStatement.setByte(6, ticket.getUsed());

            preparedStatement.execute();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTicket(int id) {
        try {
            String sql = "DELETE FROM Tickets WHERE id = ?";
            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket getTicketByID(int id) {
        try {
            Ticket result = null;

            String sql = "SELECT * FROM Tickets WHERE id = ?";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerEmail"),
                        resultSet.getInt("ticketType"),
                        resultSet.getInt("ticketPrice"),
                        resultSet.getString("qrCode"),
                        resultSet.getByte("used")
                );
            return result;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}





