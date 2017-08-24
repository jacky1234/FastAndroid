package com.jack.ioultimateencrypt.sample.mvp.model;

import android.content.Context;

import com.jack.ioultimateencrypt.sample.mvp.model.bean.CatalogBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class CatalogModule {
    public Observable<List<CatalogBean>> getCatalogs(final Context context, final String folder) {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                e.onNext(CatalogModule.this.filterActivity(context, folder));
            }
        })
                .map(new Function<List<String>, List<CatalogBean>>() {
                    @Override
                    public List<CatalogBean> apply(@NonNull List<String> lists) throws Exception {
                        List<CatalogBean> beans = new ArrayList<CatalogBean>();
                        for (String s : lists) {
                            beans.add(new CatalogBean(s, s.substring(s.lastIndexOf(".") + 1), null));
                        }
                        return beans;
                    }
                });
    }

    private List<String> filterActivity(Context context, String packageName) {
        List<String> classNameList = new ArrayList<String>();
        try {

            DexFile df = new DexFile(context.getApplicationInfo().sourceDir);//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();

                if (className.contains(packageName) && className.endsWith("Activity")) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNameList;
    }
}
