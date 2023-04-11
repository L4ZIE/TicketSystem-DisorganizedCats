package dal;

import be.Account;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import dal.connector.DataBaseConnector;
import dal.interfaces.IAccountDAO;
import javafx.event.ActionEvent;


import javafx.scene.control.Alert;
import pl.controllers.LoginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO implements IAccountDAO {
    private PreparedStatement preparedStatement;
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    private Object root;
    private LoginController loginController;

    public AccountDAO() {
        loginController = new LoginController();
    }

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
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void creatAccount(Account account) {
        try {
            String sql = "INSERT INTO Accounts(uName,uPassword,accountType) VALUES (?,?,?)";
            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);


            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setBoolean(3, account.getAccountType());

            preparedStatement.execute();
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
            String sql = "UPDATE Accounts SET  uName = ? ,uPassword = ?, accountType = ?, WHERE id = ?";

            Connection conn = dataBaseConnector.createConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setBoolean(3, account.getAccountType());
            preparedStatement.setInt(4, account.getId());

            preparedStatement.executeUpdate();
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public void logInUser( String uName, String uPassword) {
        try {
            String sql = "SELECT uPassword, accountType FROM Accounts WHERE uName = ?";

            preparedStatement = dataBaseConnector.createConnection().prepareStatement(sql);
            preparedStatement.setString(1, uName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided information's are incorrect!");
                alert.show();
            } else {
                if(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("uPassword");
                    Boolean retrievedAccountType = resultSet.getBoolean("accountType");
                }
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
