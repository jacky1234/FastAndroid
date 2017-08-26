package com.jack.ioultimateencrypt.sample.rx.rxbus;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by jackyalice on 2017/3/27.
 * rxjava2 版本
 */

public class RxBus {
    /**
     * string:pageKey
     * Map->Integer:eventKey
     */
    private Hashtable<String, Map<Integer, FlowableProcessor>> mSubscribers = new Hashtable<>();
//    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private RxBus() {
    }

    private static RxBus instance = new RxBus();

    public static RxBus get() {
        return instance;
    }

    public void post(@NonNull final Object eventKey, @NonNull final Object content) {
        Observable.fromIterable(mSubscribers.entrySet())
                .map(new Function<Map.Entry<String, Map<Integer, FlowableProcessor>>, Map<Integer, FlowableProcessor>>() {

                    @Override
                    public Map<Integer, FlowableProcessor> apply(@io.reactivex.annotations.NonNull Map.Entry<String, Map<Integer, FlowableProcessor>> map) throws Exception {
                        return map.getValue();
                    }
                })
                .filter(new Predicate<Map<Integer, FlowableProcessor>>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull Map<Integer, FlowableProcessor> map) throws Exception {
                        return map != null && !map.isEmpty();
                    }
                })
                .map(new Function<Map<Integer, FlowableProcessor>, FlowableProcessor>() {
                    @Override
                    public FlowableProcessor apply(@io.reactivex.annotations.NonNull Map<Integer, FlowableProcessor> map) throws Exception {
                        return map.get(eventKey);
                    }
                })
                .filter(new Predicate<FlowableProcessor>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull FlowableProcessor flowableProcessor) throws Exception {
                        return flowableProcessor != null;
                    }
                })
                .subscribe(new Consumer<FlowableProcessor>() {
                               @Override
                               public void accept(@io.reactivex.annotations.NonNull FlowableProcessor flowableProcessor) throws Exception {
                                   flowableProcessor.onNext(content);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
    }

    public <T> Flowable<T> register(@NonNull String page, @NotNull Integer eventkey, @NotNull Class<T> clz) {
        Map<Integer, FlowableProcessor> map = mSubscribers.get(page);
        if (map == null) {
            map = new HashMap<Integer, FlowableProcessor>();
            mSubscribers.put(page, map);
        }

        FlowableProcessor processor = map.get(eventkey);
        if (processor == null) {
            processor = PublishProcessor.create().toSerialized();
            map.put(eventkey, processor);
        }

        return processor;
    }

    public void unRegister(@NonNull String page, @NotNull Integer eventkey) {
        final Map<Integer, FlowableProcessor> map = mSubscribers.get(page);
        if (map != null) {
            map.remove(eventkey);
        }
    }

    public void unRegister(@NonNull String page) {
        mSubscribers.remove(page);
    }

}