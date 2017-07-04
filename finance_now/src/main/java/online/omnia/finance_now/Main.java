package online.omnia.finance_now;

import online.omnia.finance_now.campaign.Account;
import online.omnia.finance_now.campaign.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by lollipop on 03.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(AccountEntity.class);
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.openSession();
        List<Account> list = session.createQuery("from Account").list();
        System.out.println(list.size());
    }

}
