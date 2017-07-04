package online.omnia.finance_now.utils;

import javax.persistence.*;

/**
 * Created by lollipop on 04.07.2017.
 */
@Entity
@Table(name = "dir_account")
public class Account {
    @Id
    @Column(name = "id_account", length = 11)
    private int idAccount;
    @Column(name = "name_account", length = 200)
    private String nameAccount;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private AccountEntity accountEntity;

    public Account() {
    }

    public Account(int idAccount, String nameAccount) {
        this.idAccount = idAccount;
        this.nameAccount = nameAccount;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }
}
