package com.jackyang.android.support.injection;

import com.jackyang.android.support.function.Closure;
import com.jackyang.android.support.function.Supplier;

/**
 * 依赖注入配置
 * <p/>
 * Created by jackyang on 4/13/15.
 */
public interface Injection {

    /**
     * 启用shared preferences
     *
     * @param name shared_prefs名称, 无需使用后缀
     * @param mode 文件模式
     */
    Injection enableKeyValueStore(String name, int mode);


    /**
     * 注册bean
     *
     * @param interfaceType 接口类型
     * @param supplier      对象提供者
     * @return this
     */
    <T> Injection registerBean(Class<T> interfaceType, Supplier<T> supplier);

    /**
     * 注册bean
     *
     * @param interfaceType 接口类型
     * @param bean          对象
     * @return this
     */
    <T> Injection registerBean(Class<T> interfaceType, T bean);

    /**
     * 注册单例bean
     *
     * @param interfaceType  接口类型
     * @param implementation 实现类型
     * @return this
     */
    <T> Injection registerSingleton(Class<T> interfaceType, Class<? extends T> implementation);


    /**
     * 注册prototype bean
     *
     * @param interfaceType  接口类型
     * @param implementation 实现类型
     * @return this
     */
    <T> Injection registerPrototype(Class<T> interfaceType, Class<? extends T> implementation);

    /**
     * 完成后的额外工作
     *
     * @param closure Closure
     * @return this
     */
    <T> Injection configure(Class<T> type, Closure<T> closure);

    /**
     * 获取Bean类型
     */
    <T> T getBean(Class<T> type);
}
