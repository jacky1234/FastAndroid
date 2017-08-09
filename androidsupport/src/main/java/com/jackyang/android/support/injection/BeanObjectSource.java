package com.jackyang.android.support.injection;


import com.jackyang.android.support.lang.Initializable;

/**
 * 直接返回已创建的Bean
 *
 * @param <T>
 */
public class BeanObjectSource<T> extends ObjectSource<T> {

    private T target;

    private boolean dependenciesInjected;

    BeanObjectSource(BeanFactory beanFactory, T target) {
        super(beanFactory);
        this.target = target;
    }

    @Override
    public T getObject() {
        if (!dependenciesInjected) {
            new Injector(beanFactory).injectDependenciesForClassHierarchy(target);
            if (target instanceof Initializable) {
                ((Initializable) target).initialize();
            }
            dependenciesInjected = true;
        }
        return target;
    }
}