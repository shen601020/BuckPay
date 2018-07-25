package com.baokekeji.star.pay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.baokekeji.star.pay.PaymentStatus;

import java.util.Map;

import io.reactivex.subjects.PublishSubject;

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
public class AlipayWay {


    private static final int SDK_PAY_FLAG = 1;
    static PublishSubject<PaymentStatus> mSubjects = PublishSubject.create();
    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            @SuppressWarnings("unchecked")
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultInfo = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                mSubjects.onNext(new PaymentStatus(true, resultInfo));
            } else {
                mSubjects.onNext(new PaymentStatus(false, resultInfo));
            }
        }
    };


    public static void payMoney(final Activity activity, final String orderInfo) {


        Runnable payRunnable = new Runnable() {
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    public static PublishSubject<PaymentStatus> getSubjects() {
        return mSubjects;
    }


}
