package com.jackyang.android.support.injection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * Factory that creates objects with their dependencies injected. Object dependencies are required to be coded as fields
 * (static or instance) annotated with the Dependency getAnnotation. Supports object graph cycles through a proxy-based
 * lazy-dependency-injection of interface types; class types require eager-dependency-injection.
 * <p/>
 * Example usage: <code>
 * // register implementations
 * BeanFactory factory = new BeanFactory();
 * factory.registerPrototype(Y.class, YImpl.class);
 * factory.registerSingleton(X.class, XImpl.class);
 * factory.registerBean(X.class, new X());
 * <p/>
 * // obtain an instance of Y with its dependency on X pre-populated
 * Y y1 = factory.getObject(Y.class);
 * </code>
 */
public class BeanFactory {

    private static final BeanFactory INSTANCE = new BeanFactory();


    private BeanFactory() {
    }

    public static BeanFactory getInstance() {
        return INSTANCE;
    }

    private Map<Class<?>, ObjectSource<?>> implementationSources = new HashMap<>();

    /**
     * Registers a class as the implementor of the type. A <b>new</b> instance of the class will be injected into every
     * matching dependency.
     */
    public <T> void registerPrototype(Class<T> anInterface, Class<? extends T> anImplementationClass) {
        checkClassNotAlreadyRegistered(anInterface);
        implementationSources.put(anInterface, new PrototypeObjectSource<T>(this, anImplementationClass));
    }

    /**
     * Registers a class as the implementor of the type. A <b>single</b> instance of the class will be injected into
     * every matching dependency.
     */
    public <T> void registerSingleton(Class<T> anInterface, Class<? extends T> anImplementationClass) {
        checkClassNotAlreadyRegistered(anInterface);
        implementationSources.put(anInterface, new SingletonObjectSource<T>(this, anImplementationClass));
    }

    /**
     * Registers a single object as the implementor of the type. The object will be injected into every matching
     * dependency.
     */
    public <T> void registerBean(Class<T> anInterface, T bean) {
        checkClassNotAlreadyRegistered(anInterface);
        implementationSources.put(anInterface, new BeanObjectSource<T>(this, bean));
    }

    /**
     * If the passed class was not previously registered, throws an IllegalArgumentException. Otherwise, based on the
     * prior registrations, returns an object that is assignable to the passed type, and which has had all of its
     * dependencies injected.
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> aClass) {
        if (implementationSources.containsKey(aClass)) {
            return (T) implementationSources.get(aClass).getObject();
        }

        throw new RuntimeException(format("No object source was registered for class %s.", aClass.getCanonicalName()));
    }

    @SuppressWarnings("unchecked")
    <T, S extends T> ObjectSource<S> getObjectSource(Class<T> aClass) {
        return (ObjectSource<S>) implementationSources.get(aClass);
    }

    private void checkClassNotAlreadyRegistered(Class<?> aClass) {
        if (implementationSources.containsKey(aClass)) {
            throw new IllegalArgumentException(format(
                    "Unable to register for the class %s as it is already registered: %s.", aClass.getCanonicalName(),
                    implementationSources.get(aClass).toString()));
        }
    }

    /**
     * @return 工厂注册的所有对象
     */
    public List getBeans() {
        List beans = new ArrayList();
        for (Map.Entry<Class<?>, ObjectSource<?>> entry : implementationSources.entrySet()) {
            beans.add(entry.getValue().getObject());
        }
        return beans;
    }
}
