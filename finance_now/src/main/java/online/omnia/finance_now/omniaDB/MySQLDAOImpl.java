package online.omnia.finance_now.omniaDB;

import online.omnia.finance_now.campaign.*;
import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.utils.FinanceNow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public class MySQLDAOImpl implements MySQLDAO{

    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static MySQLDAOImpl mySQLDAO;

    static {
        configuration = new Configuration()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(AccountEntity.class)
                .addAnnotatedClass(FinanceNow.class)
                .addAnnotatedClass(CheetahTokenEntity.class)
                .addAnnotatedClass(MyTargetTokenEntity.class)
                .configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    private MySQLDAOImpl() {}
    @Override
    public List<Account> getAccounts() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Account", Account.class);

        System.out.println("Started");

        List<Account> accounts = query.getResultList();

        session.close();

        System.out.println("Exit");
        return accounts;
    }
    @Override
    public Account getAccountByName(String name) {

        return null;
    }

    @Override
    public List<FinanceNow> getFinances() {
        Session session = sessionFactory.openSession();
        List<FinanceNow> finances = session.createQuery("from FinanceNow", FinanceNow.class).list();
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

    public static synchronized MySQLDAOImpl getInstance() {
        if (mySQLDAO == null) mySQLDAO = new MySQLDAOImpl();
        return mySQLDAO;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
