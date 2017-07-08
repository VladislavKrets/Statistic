package online.omnia.finance_stat.utils;

import online.omnia.finance_stat.campaign.CheetahTokenEntity;
import online.omnia.finance_stat.campaign.MyTargetTokenEntity;
import online.omnia.finance_stat.networks.cheetah.CheetahAds;
import online.omnia.finance_stat.networks.mytarget.MyTarget;
import online.omnia.finance_stat.omniaDB.MySQLDAOImpl;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by lollipop on 06.07.2017.
 */
public class TokenAdder {
    private static MySQLDAOImpl mySQLDAO;
    private static Date currentDate;
    private static SimpleDateFormat dateFormat;
    final static Logger logger;

    static {
        logger = Logger.getLogger(TokenAdder.class);
        mySQLDAO = MySQLDAOImpl.getInstance();
        currentDate = new Date();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            currentDate = dateFormat.parse(dateFormat.format(currentDate));
        } catch (ParseException e) {
            logger.debug(e.getMessage());
        }
    }

    public static void tokenChangeMT(MyTarget myTarget) {
        List<MyTargetTokenEntity> tokens = mySQLDAO.getMyTargetTokens();
        for (MyTargetTokenEntity entity : tokens) {
            if (entity.getIdAccount() == myTarget.getIdAccount()) {
                if (currentDate.compareTo(entity.getTimeExpired()) >= 0) {
                    online.omnia.finance_stat.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                            myTarget.updateToken();
                    entity.setToken(myTargetTokenEntity.getAccessToken());
                    entity.setTokenType(myTargetTokenEntity.getTokenType());
                    try {
                        entity.setTimeExpired(dateFormat.parse(dateFormat.format(new Date(myTargetTokenEntity.getExpiresIn()))));
                    } catch (ParseException e) {
                        logger.debug(e.getMessage());
                    }
                    entity.setTimeCreate(currentDate);

                    try {
                        entity.setTimeExpired(dateFormat.parse(dateFormat.format(
                                new Date(currentDate.getTime() + myTargetTokenEntity.getExpiresIn()))));
                    } catch (ParseException e) {
                        logger.debug(e.getMessage());
                    }
                    mySQLDAO.updateMytargetToken(entity);
                }
                else {
                    online.omnia.finance_stat.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                            new online.omnia.finance_stat.networks.mytarget.MyTargetTokenEntity(
                            entity.getToken(), entity.getTokenType(), entity.getTimeExpired().getTime() - currentDate.getTime(),
                                    entity.getRefreshToken()
                    );
                    myTarget.setAccessToken(myTargetTokenEntity);
                }
                return;
            }
        }

        online.omnia.finance_stat.networks.mytarget.MyTargetTokenEntity myTargetTokenEntity =
                myTarget.updateToken();
        MyTargetTokenEntity entity = new MyTargetTokenEntity();
        entity.setTokenType(myTargetTokenEntity.getTokenType());
        entity.setToken(myTargetTokenEntity.getAccessToken());
        entity.setTimeCreate(currentDate);

        try {
            entity.setTimeExpired(dateFormat.parse(dateFormat.format(
                    new Date(currentDate.getTime() + myTargetTokenEntity.getExpiresIn()))));
        } catch (ParseException e) {
            logger.debug(e.getMessage());
        }
        entity.setRefreshToken(myTargetTokenEntity.getRefreshToken());
        entity.setIdAccount(myTarget.getIdAccount());
        mySQLDAO.addMyTargetToken(entity);
    }
    public static void tokenChangeCheetah(CheetahAds cheetAh) {
        List<CheetahTokenEntity> tokens = mySQLDAO.getCheetahTokens();

        for (CheetahTokenEntity entity : tokens) {
            if (entity.getIdAccount() == cheetAh.getIdAccount()) {

                if (currentDate.compareTo(entity.getTimeExpired()) >= 0) {

                    online.omnia.finance_stat.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                            cheetAh.updateToken();
                    entity.setToken(cheetahTokenEntity.getAccessToken());
                    entity.setTokenType(cheetahTokenEntity.getTokenType());

                    entity.setTimeCreate(currentDate);
                    try {
                        entity.setTimeExpired(dateFormat.parse(dateFormat.format(
                                new Date(currentDate.getTime() + cheetahTokenEntity.getExpiresIn()))));
                    } catch (ParseException e) {
                        logger.debug(e.getMessage());
                    }
                    mySQLDAO.updateCheetahToken(entity);
                }
                else {
                    online.omnia.finance_stat.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                            new online.omnia.finance_stat.networks.cheetah.CheetahTokenEntity(
                                    entity.getToken(), entity.getTokenType(), entity.getTimeExpired().getTime() - currentDate.getTime());
                    cheetAh.setAccessToken(cheetahTokenEntity);
                }
                return;
            }
        }

        online.omnia.finance_stat.networks.cheetah.CheetahTokenEntity cheetahTokenEntity =
                cheetAh.updateToken();
        CheetahTokenEntity entity = new CheetahTokenEntity();

        entity.setTokenType(cheetahTokenEntity.getTokenType());

        entity.setToken(cheetahTokenEntity.getAccessToken());

        entity.setTimeCreate(currentDate);

        try {
            entity.setTimeExpired(dateFormat.parse(dateFormat.format(
                    new Date(currentDate.getTime() + cheetahTokenEntity.getExpiresIn()))));
        } catch (ParseException e) {
            logger.debug(e.getMessage());
        }

        entity.setIdAccount(cheetAh.getIdAccount());
        mySQLDAO.addCheetahToken(entity);
    }
}
