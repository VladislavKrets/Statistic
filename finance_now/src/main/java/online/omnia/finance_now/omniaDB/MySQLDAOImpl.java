package online.omnia.finance_now.omniaDB;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.campaign.AccountEntity;
import online.omnia.finance_now.utils.FinanceNow;
import org.hibernate.Session;
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
        Session session = sessionFactory.openSession();
        List<Account> accounts =  session.createQuery("select from Account").list();
        session.close();
        return accounts;
    }

    @Override
    public Account getAccountByName(String name) {

        return null;
    }

    @Override
    public List<FinanceNow> getFinances() {
        Session session = sessionFactory.openSession();
        List<FinanceNow> finances = session.createQuery("select from FinanceNow").list();
        session.close();
        return finances;
    }

    @Override
    public void addFinance(FinanceNow financeNow) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(financeNow);
        session.beginTransaction().commit();
        session.close();
    }
}
