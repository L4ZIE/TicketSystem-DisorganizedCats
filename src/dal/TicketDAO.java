package dal;

import be.Ticket;
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
    private DataBaseConnector dataBaseConnector= DataBaseConnector.getInstance();


    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets;

        String sql = "SELECT * FROM Tickets";
        preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        tickets = fillTickets(resultSet);

        return tickets;
    }

    private List<Ticket> fillTickets(ResultSet resultSet) throws SQLException {
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
    }

    @Override
    public void createTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Tickets ( customerName, customerEmail, ticketType, ticketPrice, qrCode, used ) VALUES ( ?,?,?,?,?,?)";

        preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

        preparedStatement.setString(1, ticket.getCustomerName());
        preparedStatement.setString(2, ticket.getCustomerEmail());
        preparedStatement.setInt(3, ticket.getTicketType());
        preparedStatement.setInt(4, ticket.getTicketPrice());
        preparedStatement.setString(5, ticket.getQrCode());
        preparedStatement.setByte(6, ticket.getUsed());

        preparedStatement.execute();
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        String sql = "DELETE FROM Tickets WHERE id = ?";

        Connection conn = dataBaseConnector.createConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();
    }
    @Override
    public Ticket getTicketByID(int id) throws SQLException {
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
    }





    public static void main(String[] args) throws SQLException {

        TicketDAO ticketDAO = new TicketDAO();
        //
        // ticketDAO.createTicket(new Ticket( 2,"Anna", " anna.easv.ds@.dk",1,200,"gbhlsvlc", (byte) 1));
        //System.out.println(ticketDAO.getAllTickets());
        //ticketDAO.deleteTicket(2);
        //System.out.println(ticketDAO.getTicketByID(3));
    }
}




