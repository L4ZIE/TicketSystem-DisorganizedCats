package unitTest;

import be.Account;
import org.junit.Test;
import pl.models.AccountModel;

import static junit.framework.TestCase.assertEquals;

public class AccountModelTest {

    @Test
    public void createAccountTest() {
        //this one should fail
        AccountModel accountModel = new AccountModel();
        accountModel.createAccount(new Account(0,null,null,false));
    }

    @Test
    public void deleteAccountTest(){
        Account expected = null;
        Account result = null;
        //id is mock data, it is generated inside the DB
        Account account = new Account(5,"Bobby", "123", false);
        AccountModel accountModel = new AccountModel();
        accountModel.createAccount(account);
        account = accountModel.getAccountByName("Bobby"); //to get a real id from the DB
        accountModel.deleteAccount(account.getId());

        result = accountModel.getAccountByID(account.getId());
        assertEquals(expected, result);
    }
    @Test
    public void updateAccountTest(){
        Account expected = null;
        Account result = null;

        //id is mock data, it is generated inside the DB
        Account account = new Account(5,"Bobby", "123", false);
        AccountModel accountModel = new AccountModel();
        accountModel.createAccount(account);
        account = accountModel.getAccountByName("Bobby"); //to get a real id from the DB

        expected = new Account(account.getId(), "newBobby", "123", false);

        account.setUsername("newBobby");
        accountModel.updateAccount(account);
        result = accountModel.getAccountByID(expected.getId());

        assertEquals(expected, result);

        //to clear the DB
        accountModel.deleteAccount(account.getId());
    }

}
