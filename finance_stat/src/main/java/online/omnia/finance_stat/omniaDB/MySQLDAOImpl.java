package online.omnia.finance_stat.omniaDB;

import online.omnia.finance_stat.campaign.Account;
import online.omnia.finance_stat.campaign.CheetahTokenEntity;
import online.omnia.finance_stat.campaign.MyTargetTokenEntity;
import online.omnia.finance_stat.utils.FinanceStat;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
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
        Query query = session.createQuery("from Account", Account.class);
        List<Account> accounts = query.getResultList();
        session.close();
        return accounts;
    }

    public List<MyTargetTokenEntity> getMyTargetTokens() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from MyTargetTokenEntity", MyTargetTokenEntity.class);
        List<MyTargetTokenEntity> tokens = query.getResultList();
        return tokens;
    }

    public void addMyTargetToken(MyTargetTokenEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void addCheetahToken(CheetahTokenEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List<CheetahTokenEntity> getCheetahTokens() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CheetahTokenEntity", CheetahTokenEntity.class);
        List<CheetahTokenEntity> tokens = query.getResultList();
        return tokens;
    }

    public synchronized void updateMytargetToken(MyTargetTokenEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.lock(entity, LockMode.UPGRADE_NOWAIT);
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public synchronized void updateCheetahToken(CheetahTokenEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.lock(entity, LockMode.UPGRADE_NOWAIT);
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public List<FinanceStat> getFinances() {
        Session session = sessionFactory.openSession();
        List<FinanceStat> finances = session.createQuery("from FinanceStat", FinanceStat.class).list();
        session.close();
        return finances;
    }

    @Override
    public void addFinance(FinanceStat financeNow) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(financeNow);
        session.getTransaction().commit();
        session.close();
    }

    public static MySQLDAOImpl getInstance() {
        if (mySQLDAO == null) mySQLDAO = new MySQLDAOImpl();
        return mySQLDAO;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
