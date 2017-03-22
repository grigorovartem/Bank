package DAO;

import Entity.Currency;
import Entity.Exchanger;
import Services.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ExchangerDAOImpl extends CommonDAO implements ExchangerDAO {

    private EntityManager manager = CommonService.getManager();

    @Override
    public Exchanger mergeExchanger(Exchanger exchanger) {
        return super.merge(exchanger);
    }

    @Override
    public void deleteExchanger(Exchanger exchanger) {
        super.delete(exchanger);
    }

    @Override
    public double getMultiplier(Currency currencyFrom, Currency currencyTo) {
        if (currencyFrom.equals(currencyTo)) {
            return 1.;
        }
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Double> exchangerQuery = builder.createQuery(Double.class);
        Root<Exchanger> root = exchangerQuery.from(Exchanger.class);
        exchangerQuery.select(root.get(currencyTo.name().toLowerCase()))
                .where(builder.equal(root.get("id"), currencyFrom.name().toLowerCase()));
        Double multiplier = manager.createQuery(exchangerQuery).getSingleResult();
        return multiplier;
    }

    @Override
    public void build() {
        super.merge(new Exchanger("usd", 1., 0.92, 26.85));
        super.merge(new Exchanger("eur", 1.08, 1., 29.17));
        super.merge(new Exchanger("uah", 0.04, 0.34, 1.));
    }
}
