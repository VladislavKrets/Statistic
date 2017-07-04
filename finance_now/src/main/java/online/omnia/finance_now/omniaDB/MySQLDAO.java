package online.omnia.finance_now.omniaDB;

import online.omnia.finance_now.utils.Account;
import online.omnia.finance_now.utils.FinanceNow;

import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public interface MySQLDAO {
    List<Account> getAccounts();
    Account getAccountByName(String name);
    List<FinanceNow> getFinances();
    void addFinance(FinanceNow financeNow);

}
