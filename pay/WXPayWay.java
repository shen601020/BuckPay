package com.baokekeji.star.pay;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.baokekeji.star.BuildConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @category:{类别}
 * @description: {类描述}
 * @author: shenhejun
 * @email: 409724179@qq.com
 * @date : 2018/7/19
 * @version:1.0.1
 * @modifyAuthor:
 * @modifyDate:
 * @modifyVersion:
 */
public class WXPayWay {
    public static void payMoney(Activity context, String orderInfo) {
        String appId = BuildConfig.WX_APPID;
        IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
        api.registerApp(appId);
        PayReq payReq = new PayReq();
        payReq.appId = appId;
        JsonObject obj = (JsonObject) new JsonParser().parse(orderInfo);
        payReq.partnerId = obj.get("partnerid").getAsString();
        payReq.prepayId = obj.get("prepayid").getAsString();
        payReq.nonceStr = obj.get("noncestr").getAsString();
        payReq.timeStamp = obj.get("timestamp").getAsString();
        payReq.sign = obj.get("sign").getAsString();
        payReq.packageValue = "Sign=WXPay";
        api.sendReq(payReq);
    }
}
