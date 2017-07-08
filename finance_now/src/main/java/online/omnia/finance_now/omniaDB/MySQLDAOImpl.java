package online.omnia.finance_now.omniaDB;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.campaign.CheetahTokenEntity;
import online.omnia.finance_now.campaign.MyTargetTokenEntity;
import online.omnia.finance_now.utils.FinanceNow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public class MySQLDAOImpl implements MySQLDAO{
    @PersistenceContext
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static MySQLDAOImpl mySQLDAO;

    static {
        configuration = new Configuration()
                .addAnnotatedClass(Account.class)
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
        List<Account> accounts = session.createQuery("from Account", Account.class).list();
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
        session.persist(entity);
        session.beginTransaction();
        session.lock(entity, LockModeType.PESSIMISTIC_WRITE);
        session.createQuery("update CheetahTokenEntity set token=:token, timeCreate=:timeCreate, timeExpired=:timeExpired")
                .setParameter("token", entity.getToken())
                .setParameter("timeCreate", entity.getTimeCreate())
                .setParameter("timeExpired", entity.getTimeExpired()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public synchronized void updateCheetahToken(CheetahTokenEntity entity) {
        Session session = sessionFactory.openSession();
        session.persist(entity);
        session.beginTransaction();
        session.lock(entity, LockModeType.PESSIMISTIC_WRITE);
        session.createQuery("update CheetahTokenEntity set token=:token, timeCreate=:timeCreate, timeExpired=:timeExpired")
        .setParameter("token", entity.getToken())
        .setParameter("timeCreate", entity.getTimeCreate())
        .setParameter("timeExpired", entity.getTimeExpired()).executeUpdate();
        session.getTransaction().commit();
        session.close();
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
