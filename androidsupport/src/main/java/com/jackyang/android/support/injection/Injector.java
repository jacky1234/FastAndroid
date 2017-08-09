package com.jackyang.android.support.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

import static java.lang.String.format;

/**
 * Given a DependencyInjectingObjectFactory, injects dependencies into a class.
 */
public class Injector {

    private BeanFactory beanFactory;

    public Injector(BeanFactory aBeanFactory) {
        beanFactory = aBeanFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> void injectDependenciesForClassHierarchy(T target) {
        // the reflection API requires that each class in the hierarchy be considered
        // start with the lowest class in the hierarchy
        Class<?> interfaceOfObject = target.getClass();

        do {
            // inject the dependencies for this class
            injectDependenciesForSingleClass(target, (Class<T>) interfaceOfObject);

            // move on up the class hierarchy...
            interfaceOfObject = interfaceOfObject.getSuperclass();

            // until the top is reached
        } while (interfaceOfObject != null);
    }

    private <T, S extends T> void injectDependenciesForSingleClass(S target, Class<T> type) {
        // for each
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                try {
                    // if the field has not already been set (possibly through injection)...
                    if (field.get(target) == null) {
                        Class<?> classOfDependency = field.getType();
                        final Object injectedValue;
                        final int modifiers = classOfDependency.getModifiers();
                        if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers)) {
                            injectedValue = createProxy(classOfDependency);
                        } else {
                            injectedValue = beanFactory.getObject(classOfDependency);
                        }
                        field.set(target, injectedValue);
                    }
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(format("Unable to access field %s.", field.getName()), e);
                }
            }
        }
    }

    /**
     * 获取动态代理对象
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T createProxy(final Class<T> type) {
        return (T) Proxy.newProxyInstance(BeanFactory.class.getClassLoader(),
                new Class[]{type}, new InvocationHandler() {
                    private T object;

                    public Object invoke(Object aProxy, Method aMethod, Object[] anArrayOfArguments) throws Throwable {
                        if (object == null) {
                            final ObjectSource<T> objectSource = beanFactory
                                    .getObjectSource(type);
                            if (objectSource == null) {
                                throw new RuntimeException(format(
                                        "No source was registered for the dependecy of type %s.", type.getName()));
                            }
                            object = (T) objectSource.getObject();
                        }
                        return aMethod.invoke(object, anArrayOfArguments);
                    }
                });
    }

}
