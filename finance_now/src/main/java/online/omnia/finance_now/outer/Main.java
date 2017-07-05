package online.omnia.finance_now.outer;

import online.omnia.finance_now.networks.BaseNetwork;
import online.omnia.finance_now.networks.cheetah.CheetahAds;
import online.omnia.finance_now.networks.mytarget.MyTarget;
import online.omnia.finance_now.networks.mytarget.MyTargetTokenEntity;
import online.omnia.finance_now.utils.HttpMethodUtils;
import online.omnia.finance_now.utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by lollipop on 03.07.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        /*MyTarget target = new MyTarget("", "");
        String str = target.signEncoder("GET", "https://target.mail.ru/api/v1/campaigns.json", "");
        System.out.println(str);
        HttpMethodUtils utils = new HttpMethodUtils("https://target.my.com/api/v1/");
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", "AuthHMAC " + "6qW93na1vpBBX9O7:" + str);
        String answer = utils.getMethod("statistics/campaigns/10/day/05.05.2017-05.07.2017.json", map);
        System.out.println(answer);*/
        /*
        CheetahAds ads = new CheetahAds("https://api.ori.cmcm.com/");
        System.out.println(ads.getAccessToken("13256", "ae3a27715fb432f9ba036f163354e598"));*/
        /*MyTarget target = new MyTarget("https://target.my.com/api/v2/");
        MyTargetTokenEntity entity = target.getAccessToken("6qW93na1vpBBX9O7",
                "SSAipEtQb7vkNaEgmix5gIVll0cjP3eW7roj5uGJ5G04sEaVyRcOWxvPXhwHa5CBSoG4BgbTNGCw4ROMBIXTVePapdN3iWNNM2vvyU0geaKhtidXwkAsJNc8gF2X3dJmekKxNJGY8XtU6dEADkHRsMUIG4Gz4ovRdMVBfuMTF7G1z4QN1sJJLdybEUoyFnCyhrEifIDWygcsmKxHCWGpXzOqUFyg");
        System.out.println(entity.getAccessToken());*/

    }

}
