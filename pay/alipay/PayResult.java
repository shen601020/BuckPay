package com.baokekeji.star.pay.alipay;

import android.text.TextUtils;

import java.util.Map;

/**
 * @category:{类别}
 * @description: {alipay回调对象--来源支付婊sdk的demo}
 * @author: shenhejun
 * @email: 409724179@qq.com
 * @date : 2018/7/19
 * @version:1.0.1
 * @modifyAuthor:
 * @modifyDate:
 * @modifyVersion:
 */
public class PayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public PayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    /**
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
