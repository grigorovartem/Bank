package DAO;

import Entity.Currency;
import Entity.Exchanger;

public interface ExchangerDAO {

    Exchanger mergeExchanger(Exchanger exchanger);

    void deleteExchanger(Exchanger exchanger);

    double getMultiplier(Currency currencyFrom, Currency currencyTo);

    void build();
}
