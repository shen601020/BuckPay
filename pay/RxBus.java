package com.baokekeji.star.pay;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

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
public class RxBus {
    private final Subject<Object> bus;
    private RxBus() {
        bus = PublishSubject.create();
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }


    public void post(Object o) {
        bus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
