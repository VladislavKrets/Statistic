package online.omnia.finance_now.utils;

import online.omnia.finance_now.campaign.CheetahTokenEntity;
import online.omnia.finance_now.campaign.MyTargetTokenEntity;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
import online.omnia.finance_now.omniaDB.MySQLDAOImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by lollipop on 06.07.2017.
 */
public class TokenAdder {
    private static MySQLDAOImpl mySQLDAO;
    private static Date currentDate;

    static {
        mySQLDAO = MySQLDAOImpl.getInstance();
        currentDate = new Date();
    }

    public static void tokenChangeMT(MyTarget myTarget) {
        List<MyTargetTokenEntity> tokens = mySQLDAO.getMyTargetTokens();
        for (MyTargetTokenEntity entity : tokens) {
            if (entity.getIdAccount() == myTarget.getIdAccount()) {
                if (currentDate.compareTo(entity.getTimeRenew()) <= 0) {
                    online.omnia.finance_now.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                            myTarget.updateToken();
                    entity.setToken(myTargetTokenEntity.getAccessToken());
                    entity.setTokenType(myTargetTokenEntity.getTokenType());
                    entity.setTimeExpired(new Date(myTargetTokenEntity.getExpiresIn()));
                    entity.setTimeCreate(currentDate);

                    entity.setTimeRenew(new Date(currentDate.getTime() + myTargetTokenEntity.getExpiresIn()));
                    mySQLDAO.updateMytargetToken(entity);
                }
                else {
                    online.omnia.finance_now.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                            new online.omnia.finance_now.networks.mytarget.MyTargetTokenEntity(
                            entity.getToken(), entity.getTokenType(), entity.getTimeExpired().getTime(),
                                    entity.getRefreshToken()
                    );
                    myTarget.setAccessToken(myTargetTokenEntity);
                }
                return;
            }
        }
        online.omnia.finance_now.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                myTarget.updateToken();
        MyTargetTokenEntity entity = new MyTargetTokenEntity();
        entity.setTokenType(myTargetTokenEntity.getTokenType());
        entity.setToken(myTargetTokenEntity.getAccessToken());
        entity.setTimeCreate(currentDate);
        entity.setTimeExpired(new Date(myTargetTokenEntity.getExpiresIn()));
        entity.setTimeRenew(new Date(currentDate.getTime() + myTargetTokenEntity.getExpiresIn()));
        entity.setRefreshToken(myTargetTokenEntity.getRefreshToken());
        entity.setIdAccount(myTarget.getIdAccount());
        mySQLDAO.addMyTargetToken(entity);
    }
    public static void tokenChangeCheetah(CheetahAds cheetAh) {
        List<CheetahTokenEntity> tokens = mySQLDAO.getCheetahTokens();
        for (CheetahTokenEntity entity : tokens) {
            if (entity.getIdAccount() == cheetAh.getIdAccount()) {
                if (currentDate.compareTo(entity.getTimeRenew()) <= 0) {
                    online.omnia.finance_now.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                            cheetAh.updateToken();
                    entity.setToken(cheetahTokenEntity.getAccessToken());
                    entity.setTokenType(cheetahTokenEntity.getTokenType());
                    entity.setTimeExpired(new Date(cheetahTokenEntity.getExpiresIn()));
                    entity.setTimeCreate(currentDate);
                    entity.setTimeRenew(new Date(currentDate.getTime() + cheetahTokenEntity.getExpiresIn()));
                    mySQLDAO.updateCheetahToken(entity);
                }
                else {
                    online.omnia.finance_now.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                            new online.omnia.finance_now.networks.cheetah.CheetahTokenEntity(
                                    entity.getToken(), entity.getTokenType(), entity.getTimeExpired().getTime());
                    cheetAh.setAccessToken(cheetahTokenEntity);
                }
                return;
            }
        }
        online.omnia.finance_now.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                cheetAh.updateToken();
        CheetahTokenEntity entity = new CheetahTokenEntity();
        entity.setTokenType(cheetahTokenEntity.getTokenType());
        entity.setToken(cheetahTokenEntity.getAccessToken());
        entity.setTimeCreate(currentDate);
        entity.setTimeExpired(new Date(cheetahTokenEntity.getExpiresIn()));
        entity.setTimeRenew(new Date(currentDate.getTime() + cheetahTokenEntity.getExpiresIn()));
        entity.setIdAccount(cheetAh.getIdAccount());
        mySQLDAO.addCheetahToken(entity);
    }
}
