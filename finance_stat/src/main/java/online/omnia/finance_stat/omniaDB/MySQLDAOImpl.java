package online.omnia.finance_stat.omniaDB;

import online.omnia.finance_stat.campaign.Account;
import online.omnia.finance_stat.campaign.AccountEntity;
import online.omnia.finance_stat.campaign.CheetahTokenEntity;
import online.omnia.finance_stat.campaign.MyTargetTokenEntity;
import online.omnia.finance_stat.utils.FinanceStat;
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
    private static MySQLDAOImpl mySQLDAO;

    static {
        configuration = new Configuration()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(AccountEntity.class)
                .addAnnotatedClass(FinanceStat.class)
                .addAnnotatedClass(CheetahTokenEntity.class)
                .addAnnotatedClass(MyTargetTokenEntity.class)
                .configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    private MySQLDAOImpl() {}
    @Override
    public List<Account> getAccounts() {
        Session session = sessionFactory.openSession();
        List<Account> accounts = session.createQuery("select from Account", Account.class).list();
        session.close();
        return accounts;
    }
    @Override
    public Account getAccountByName(String name) {

        return null;
    }

    @Override
    public List<FinanceStat> getFinances() {
        Session session = sessionFactory.openSession();
        List<FinanceStat> finances = session.createQuery("select from FinanceStat", FinanceStat.class).list();
        session.close();
        return finances;
    }

    @Override
    public void addFinance(FinanceStat financeStat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(financeStat);
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
