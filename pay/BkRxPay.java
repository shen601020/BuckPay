package com.baokekeji.star.pay;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.baokekeji.star.pay.alipay.AlipayWay;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @category:{工具类}
 * @description: {支付类}
 * @author: shenhejun
 * @email: 409724179@qq.com
 * @date : 2018/7/19
 * @version:1.0.1
 * @modifyAuthor:
 * @modifyDate:
 * @modifyVersion:
 */
public class BkRxPay {

    static final String TAG = "BkRxPay";
    private Activity activity;

    public BkRxPay(@NonNull Activity activity) {
        this.activity = activity;
    }

    /**
     * @param payWay    支付方式
     * @param orderInfo from server order information
     * @return
     */
    @SuppressWarnings({"WeakerAccess", "unused"})
    public Observable<Boolean> pay(@NonNull PayWay payWay, @NonNull final String orderInfo) {
        return Observable.just(orderInfo).compose(ensure(payWay, orderInfo));
    }



    @SuppressWarnings("WeakerAccess")
    private ObservableTransformer<Object, Boolean> ensure(final PayWay payWay, final String orderInfo) {
        return new ObservableTransformer<Object, Boolean>() {
            @Override
            public ObservableSource<Boolean> apply(@NonNull Observable<Object> upstream) {
                if (payWay == PayWay.WECHATPAY) {
                    WXPayWay.payMoney(activity, orderInfo);
                    return RxBus.getDefault().toObservable(PaymentStatus.class)
                            .map(new Function<PaymentStatus, Boolean>() {
                                @Override
                                public Boolean apply(@NonNull PaymentStatus paymentStatus) throws Exception {
                                    return paymentStatus.status;
                                }
                            });
                }
                AlipayWay.payMoney(activity, orderInfo);
                return AlipayWay.getSubjects()
                        .map(new Function<PaymentStatus, Boolean>() {
                            @Override
                            public Boolean apply(@NonNull PaymentStatus paymentStatus) throws Exception {
                                return paymentStatus.status;
                            }
                        });
            }

        };
    }


}
