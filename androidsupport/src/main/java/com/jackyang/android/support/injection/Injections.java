package com.jackyang.android.support.injection;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

import com.jackyang.android.support.exception.SpicaAndroidException;
import com.jackyang.android.support.function.Closure;
import com.jackyang.android.support.function.Supplier;
import com.jackyang.android.support.lang.Disposable;
import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.prefs.SharedPreferenceKeyValueStore;

/**
 * Injections
 * <p/>
 * Created by jackyang on 4/13/15.
 */
public class Injections {


    /**
     * singleton injection instance
     */
    private static DefaultInjection injection;

    /**
     * 初始化
     *
     * @param context android Context
     */
    public static Injection initialize(Context context) {
        if (injection == null) {
            injection = new DefaultInjection(context);
        }
        return injection;
    }

    /**
     * 销毁
     */
    public static void destroy() {
        if (injection != null) {
            injection.destroy();
        }
    }


    /**
     * 获取Bean类型
     */
    public static <T> T getBean(Class<T> type) {
        return injection == null ? null : injection.getBean(type);
    }


    /**
     * DefaultInjection
     */
    private static class DefaultInjection implements Injection {

        private Context context;

        /**
         * BeanFactory
         */
        protected BeanFactory factory;

        public DefaultInjection(Context context) {
            this.context = context;
            this.factory = BeanFactory.getInstance();
            registerAndroidSystemService();
        }



        /**
         * 注册android系统服务
         */
        protected void registerAndroidSystemService() {
            factory.registerBean(TelephonyManager.class,
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));

            factory.registerBean(ConnectivityManager.class,
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

            factory.registerBean(NotificationManager.class,
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));

            factory.registerBean(AlarmManager.class,
                    (AlarmManager) context.getSystemService(Context.ALARM_SERVICE));

            factory.registerBean(ContentResolver.class, context.getContentResolver());
        }


        @Override
        public Injection enableKeyValueStore(String name, int mode) {
            SharedPreferences preferences =
                    context.getSharedPreferences(name, mode);
            factory.registerBean(KeyValueStore.class, new SharedPreferenceKeyValueStore(preferences));
            return this;
        }


        @Override
        public <T> Injection registerBean(Class<T> interfaceType, T bean) {
            factory.registerBean(interfaceType, bean);
            return this;
        }

        @Override
        public <T> Injection registerSingleton(Class<T> interfaceType, Class<? extends T> implementation) {
            factory.registerSingleton(interfaceType, implementation);
            return this;
        }

        @Override
        public <T> Injection registerPrototype(Class<T> interfaceType, Class<? extends T> implementation) {
            factory.registerPrototype(interfaceType, implementation);
            return this;
        }

        @Override
        public <T> Injection registerBean(Class<T> interfaceType, Supplier<T> supplier) {
            try {
                T target = supplier.supply();
                registerBean(interfaceType, target);
            } catch (Exception ex) {
                throw new SpicaAndroidException("创建对象失败:" + ex.getMessage(), ex);
            }
            return this;
        }

        /**
         * 获取对象
         */
        public <T> T getBean(Class<T> type) {
            return this.factory.getObject(type);
        }

        /**
         * 销毁工厂
         */
        public Injection destroy() {
            for (Object bean : this.factory.getBeans()) {
                if (bean instanceof Disposable) {
                    ((Disposable) bean).destroy();
                }
            }
            factory = null;
            return this;
        }

        @Override
        public <T> Injection configure(Class<T> type, Closure<T> closure) {
            try {
                closure.execute(getBean(type));
            } catch (Exception ex) {
                throw new SpicaAndroidException(ex.getMessage(), ex);
            }
            return this;
        }
    }
}
