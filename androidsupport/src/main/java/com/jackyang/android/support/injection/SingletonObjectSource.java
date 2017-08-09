package com.jackyang.android.support.injection;

/**
 * 单例对象工厂
 *
 * @param <T>
 */
class SingletonObjectSource<T> extends ObjectSource<T> {


    private T target;

    private boolean dependenciesInjected;

    SingletonObjectSource(BeanFactory beanFactory, Class<? extends T> type) {
        super(beanFactory, type);
    }

    @Override
    public T getObject() {
        if (!this.dependenciesInjected && this.target == null) {
            this.target = createObject(type);
            this.dependenciesInjected = true;
        }
        return this.target;
    }
}