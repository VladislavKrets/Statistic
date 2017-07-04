package online.omnia.finance_now.omniaDB;

import online.omnia.finance_now.utils.Account;
import online.omnia.finance_now.utils.AccountEntity;
import online.omnia.finance_now.utils.FinanceNow;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public class MySQLDAOImpl implements MySQLDAO{

    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static {
        configuration = new Configuration()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(AccountEntity.class)
                .addAnnotatedClass(FinanceNow.class)
                .configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public List<Account> getAccounts() {
        
        return null;
    }

    @Override
    public Account getAccountByName(String name) {
        return null;
    }

    @Override
    public List<FinanceNow> getFinances() {
        return null;
    }

    @Override
    public void addFinance(FinanceNow financeNow) {

    }
}
