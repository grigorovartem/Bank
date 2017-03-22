package Services;

import DAO.AccountDAOImpl;
import DAO.ExchangerDAOImpl;
import DAO.TransferDAOImpl;
import DAO.UserDAOImpl;

public class DAOService {

    private AccountDAOImpl accountDAO;
    private TransferDAOImpl transferDAO;
    private UserDAOImpl userDAO;
    private ExchangerDAOImpl exchangerDAO;

    public DAOService() {
        this.accountDAO = new AccountDAOImpl();
        this.transferDAO = new TransferDAOImpl();
        this.userDAO = new UserDAOImpl();
        this.exchangerDAO = new ExchangerDAOImpl();
    }

    public AccountDAOImpl getAccountDAO() {
        return accountDAO;
    }

    public TransferDAOImpl getTransferDAO() {
        return transferDAO;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public ExchangerDAOImpl getExchangerDAO() {
        return exchangerDAO;
    }
}
