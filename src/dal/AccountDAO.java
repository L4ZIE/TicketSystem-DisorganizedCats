package dal;

import be.Account;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connector.DataBaseConnector;
import dal.interfaces.IAccountDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    public List<Account> getAllAccounts() {
        List<Account> Accounts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Accounts";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Accounts.add(new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("uName"),
                        resultSet.getString("uPassword"),
                        resultSet.getBoolean("accountType")
                ));
            }
            return Accounts;
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public void creatAccount(Account account) {
        try {
        String sql = "INSERT INTO Accounts(id,uName,uPassword,accountType) VALUES (?,?,?,?)";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);

            preparedStatement.setInt(1,account.getId());
            preparedStatement.setString(2,account.getUName());
            preparedStatement.setString(3,account.getUPassword());
            preparedStatement.setBoolean(4,account.getAccountType());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteAccount(int id) {
        try {
            String sql = "DELETE FROM Accounts WHERE id = ?";
            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateAccount(Account account) {
        try {
            String sql = "UPDATE Accounts" +
                    "SET uName = ?" +
                    "SET uPassword = ?" +
                    "SET accountType = ?" +
                    "WHERE id = ?";
            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, account.getUName());
            preparedStatement.setString(2, account.getUPassword());
            preparedStatement.setBoolean(3, account.getAccountType());
            preparedStatement.setInt(4, account.getId());

            preparedStatement.executeQuery();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
