package online.omnia.finance_stat.utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lollipop on 04.07.2017.
 */
@Entity
@Table(name = "finance_1")
public class FinanceStat {
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFinanceNow;
    @Column(name = "account_id", length = 11)
    private int idAccount;
    @Column(name = "date", columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "balance", length = 10)
    private double balance;
    @Column(name = "currency", length = 5)
    private String currency;

    public FinanceStat() {
    }

    public FinanceStat(int idAccount, Date date, double balance, String currency) {
        this.idAccount = idAccount;
        this.date = date;
        this.balance = balance;
        this.currency = currency;
    }

    public int getIdFinanceNow() {
        return idFinanceNow;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public Date getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
