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
    private final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();


    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tickets";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerEmail"),
                        resultSet.getInt("ticketType"),
                        resultSet.getInt("ticketPrice"),
                        resultSet.getString("qrCode"),
                        resultSet.getBoolean("used"),
                        resultSet.getInt("eventID")
                ));
            }
            return tickets;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTicket(Ticket ticket, int eventID) {
        try {
            String sql = "INSERT INTO Tickets ( customerName, customerEmail, ticketType, ticketPrice, qrCode, used, eventID ) VALUES ( ?,?,?,?,?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, ticket.getCustomerName());
            preparedStatement.setString(2, ticket.getCustomerEmail());
            preparedStatement.setInt(3, ticket.getTicketType());
            preparedStatement.setInt(4, ticket.getTicketPrice());
            preparedStatement.setString(5, ticket.getQrCode());
            preparedStatement.setBoolean(6, ticket.getUsed());
            preparedStatement.setInt(7, eventID);

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

            preparedStatement.execute();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicket(Ticket selectedTicket) {
        try{
            String sql = "UPDATE Tickets SET customerName = ?,customerEmail = ?, ticketType = ?, ticketPrice = ?, qrCode = ?, used = ? WHERE id = ? ";
            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, selectedTicket.getCustomerName());
            preparedStatement.setString(2, selectedTicket.getCustomerEmail());
            preparedStatement.setInt(3 ,selectedTicket.getTicketType());
            preparedStatement.setInt(4, selectedTicket.getTicketPrice());
            preparedStatement.setString(5, selectedTicket.getQrCode());
            preparedStatement.setBoolean(6,selectedTicket.getUsed());
            preparedStatement.setInt(7,selectedTicket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




