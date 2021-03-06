package com.jack.ioultimateencrypt.sample.rx.rxbus;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;

/**
 * Created by jackyalice on 2017/3/27.
 * description:类似于EventBus消息总线的管理器，用RxJava进行了实现，用法：
 * 第一步、在需要接受消息的界面注册(String.class表示传递的消息类型,"delete"是tag，确定是否能接收到该消息)
 * ------第一个参数：对应的页面
 * ------第二个参数：事件的key
 * ------第三个参数：事件传递的时候携带的数据类型
 * ---Observable<String> observable = RxBusManager.register(this, "delete", String.class);
 * 第二步、发出消息
 * ------第一个参数：事件的key
 * ------第二个参数：传递的事件的实体
 * ---RxBusManager.post("delete", "fuck");
 * 第三步、注销
 * ------第一个参数：对应的页面
 * RxBusManager.unregister(this);
 */

public class RxBusManager {
    public static final RxBus gBus = RxBus.get();

    public static <T> Flowable<T> register(Object pageObject,
                                           @NonNull Integer eventKey,
                                           @NonNull Class<T> clazz) {
        return gBus.register(pageObject.getClass().getCanonicalName() + pageObject.hashCode(), eventKey, clazz);
    }

    public static void unregister(Object pageObject,
                                  @NonNull Integer eventKey) {
        gBus.unRegister(pageObject.getClass().getCanonicalName() + pageObject.hashCode(), eventKey);
    }

    public static void unregister(@NonNull Object pageObject) {
        gBus.unRegister(pageObject.getClass().getCanonicalName() + pageObject.hashCode());
    }


    public static void post(@NonNull Integer eventKey,
                            @NonNull Object content) {
        gBus.post(eventKey, content);
    }

}
