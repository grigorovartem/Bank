package Services;

import DAO.AccountDAO;
import DAO.ExchangerDAO;
import Entity.*;
import Entity.Currency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.math.BigDecimal;
import java.util.*;

public class Service {

    private DAOService daoService = new DAOService();
    private AccountDAO accountDAO = daoService.getAccountDAO();
    private ExchangerDAO exchangerDAO = daoService.getExchangerDAO();

    public Service() {
    }

    public void replenishAccount(Long accountId, double sum) {
        Account currentAccount = accountDAO.getByID(accountId);
        BigDecimal currentBalance = currentAccount.getBalance();
        currentAccount.setBalance(currentBalance.add(new BigDecimal(sum)));
        accountDAO.mergeAccount(currentAccount);
    }

    public void moneyTransaction(Long account1, Long account2, double sum) {
        Account curAccount1 = accountDAO.getByID(account1);
        Account curAccount2 = accountDAO.getByID(account2);
        Double multiplier = exchangerDAO.getMultiplier(curAccount1.getCurrency(), curAccount2.getCurrency());
        if (checkCurrency(curAccount1, curAccount2)) return;
        if (checkBalance(sum, curAccount1)) return;
        Transfer transfer = new Transfer(curAccount1, curAccount2);
        curAccount1.setBalance(curAccount1.getBalance().subtract(new BigDecimal(sum)));
        accountDAO.mergeAccount(curAccount1);
        curAccount2.setBalance(curAccount2.getBalance().add(new BigDecimal(sum * multiplier)));
        accountDAO.mergeAccount(curAccount2);
        daoService.getTransferDAO().mergeTransfer(transfer);
    }

    private boolean checkCurrency(Account curAccount1, Account curAccount2) {
        if (!curAccount1.getCurrency().equals(curAccount2.getCurrency()) &&
                !curAccount1.getUser().equals(curAccount2.getUser())) {
            System.out.println("Accounts in different currencies");
            return true;
        }
        return false;
    }

    private boolean checkBalance(double sum, Account curAccount1) {
        if (curAccount1.getBalance().doubleValue() < sum) {
            System.out.println("Not enough funds in the account");
            return true;
        }
        return false;
    }

    public BigDecimal allFundsOfUser(User user, Currency currency) {
        List<Account> accounts = daoService.getAccountDAO().accountsByUser(user.getUserId());
        BigDecimal result = BigDecimal.ZERO;
        for (Account account : accounts) {
            Double multiplier = exchangerDAO.getMultiplier(account.getCurrency(), currency);
            result = result.add(account.getBalance().multiply(new BigDecimal(multiplier)));
        }
        System.out.println("-------------" + result.doubleValue() + "-------------------");
        return result;
    }
}
