package dal.interfaces;

import be.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts();

    void creatAccount(Account account);

    void deleteAccount(int id);

    void updateAccount(Account account);
}
