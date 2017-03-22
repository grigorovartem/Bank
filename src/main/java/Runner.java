import Entity.Account;
import Entity.Currency;
import Entity.User;
import Services.CommonService;
import Services.DAOService;
import Services.Service;

import java.math.BigDecimal;

public class Runner {

    public static void main(String[] args) {
        DAOService daoService = new DAOService();
        User user1 = daoService.getUserDAO().mergeUser(new User("John", "123"));
        User user2 = daoService.getUserDAO().mergeUser(new User("Jack", "456"));

        Account account1 = new Account(Currency.UAH, user1);
        account1.setBalance(new BigDecimal(100));
        account1 = daoService.getAccountDAO().mergeAccount(account1);

        Account account2 = new Account(Currency.UAH, user2);
        account2.setBalance(new BigDecimal(200));
        account2 = daoService.getAccountDAO().mergeAccount(account2);

        Account account3 = new Account(Currency.USD, user1);
        account3.setBalance(new BigDecimal(300));
        account3 = daoService.getAccountDAO().mergeAccount(account3);

        daoService.getExchangerDAO().build();

        Service operation = new Service();
        operation.replenishAccount(account1.getAccountId(), 400);
        operation.moneyTransaction(account1.getAccountId(), account2.getAccountId(), 250);
        operation.moneyTransaction(account3.getAccountId(), account1.getAccountId(), 100.);
        //operation.moneyTransaction(account3.getAccountId(), account1.getAccountId(), 400.);
        //operation.moneyTransaction(account3.getAccountId(), account2.getAccountId(), 100.);
        operation.allFundsOfUser(user1, Currency.UAH);

        CommonService.close();
    }
}
