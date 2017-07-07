package online.omnia.finance_stat.omniaDB;


import online.omnia.finance_stat.campaign.Account;
import online.omnia.finance_stat.utils.FinanceStat;

import java.util.List;

/**
 * Created by lollipop on 04.07.2017.
 */
public interface MySQLDAO {
    List<Account> getAccounts();
    List<FinanceStat> getFinances();
    void addFinance(FinanceStat financeStat);

}
