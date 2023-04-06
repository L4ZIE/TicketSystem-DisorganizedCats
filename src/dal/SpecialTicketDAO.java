package dal;

import be.SpecialTicket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connector.DataBaseConnector;
import dal.interfaces.ISpecialTicketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialTicketDAO implements ISpecialTicketDAO {

    private PreparedStatement preparedStatement;
    private final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<SpecialTicket> getAllSpecialTickets() {
        List<SpecialTicket> specialTickets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SpecialTickets";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                specialTickets.add(new SpecialTicket(
                        resultSet.getInt("id"),
                        resultSet.getString("ticketName"),
                        resultSet.getString("qrCode"),
                        resultSet.getBoolean("used")
                ));
            }
            return specialTickets;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSpecialTicket(SpecialTicket specialTicket) {
        try {
            String sql = "INSERT INTO SpecialTickets (ticketName, qrCode, used ) VALUES ( ?,?,?)";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setString(1, specialTicket.getTicketName());
            preparedStatement.setString(2, specialTicket.getQrCode());
            preparedStatement.setBoolean(3, specialTicket.getUsed());

            preparedStatement.execute();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSpecialTicket(int id) {
        try {
            String sql = "DELETE FROM SpecialTickets WHERE id = ?";
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
    public void updateSpecTicket(SpecialTicket selectedSpecTicket) {
        try {
            String sql = "UPDATE SpecialTickets SET ticketName = ?, qrCode = ?, used = ? WHERE id = ? ";

            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, selectedSpecTicket.getTicketName());
            preparedStatement.setString(2, selectedSpecTicket.getQrCode());
            preparedStatement.setBoolean(3, selectedSpecTicket.getUsed());
            preparedStatement.setInt(4, selectedSpecTicket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

