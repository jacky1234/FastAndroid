package com.jackyang.android.support.injection;


import com.jackyang.android.support.lang.Initializable;

import static java.lang.String.format;

/**
 * 对象来源接口, 用于创建对象
 *
 * @param <T>
 */
abstract class ObjectSource<T> {

    protected BeanFactory beanFactory;

    protected Class<? extends T> type;

    ObjectSource(BeanFactory aBeanFactory) {
        beanFactory = aBeanFactory;
    }

    ObjectSource(BeanFactory beanFactory, Class<? extends T> type) {
        this.beanFactory = beanFactory;
        denyInterfaceType(type);
        this.type = type;
    }

    abstract T getObject();


    /**
     * 拒绝接口类型
     *
     * @param type type
     */
    private void denyInterfaceType(Class<? extends T> type) {
        if (type.isInterface()) {
            throw new IllegalArgumentException(format(
                    "provided class must be a concrete type, however %s is an interface.", type.getName()));
        }
    }

    /**
     * 通过反射创建对象
     *
     * @param type type
     * @return object
     */
    protected T createObject(Class<? extends T> type) {
        T target;
        try {
            target = type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(String.format("create Object for class:%s failed", type), ex);
        }
        new Injector(beanFactory).injectDependenciesForClassHierarchy(target);
        /**
         * 调用initialize
         */
        if (target instanceof Initializable) {
            ((Initializable) target).initialize();
        }
        return target;
    }
}