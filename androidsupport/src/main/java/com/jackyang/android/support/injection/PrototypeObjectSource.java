package com.jackyang.android.support.injection;

/**
 * 给定类型, 自动通过反射创建对象(prototype), 即每次创建新对象
 *
 * @param <T>
 */
class PrototypeObjectSource<T> extends ObjectSource<T> {


    PrototypeObjectSource(BeanFactory beanFactory, Class<? extends T> type) {
        super(beanFactory, type);
    }

    /**
     * @return 每次调用创建新的对象
     */
    @Override
    public T getObject() {
        return createObject(type);
    }
}