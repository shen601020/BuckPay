package com.baokekeji.star.pay;

/**
 * @category:{类别}
 * @description: {支付状态}
 * @author: shenhejun
 * @email: 409724179@qq.com
 * @date : 2018/7/19
 * @version:1.0.1
 * @modifyAuthor:
 * @modifyDate:
 * @modifyVersion:
 */
public class PaymentStatus {
    public boolean status;

    public String msg;

    public PaymentStatus(boolean status,String msg) {
        this.status = status;
        this.msg = msg;
    }

}
