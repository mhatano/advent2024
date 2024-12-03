package jp.hatano.advent2024.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdventMessageProviderContainer implements AdventMessageProvider {
    private final Class<?> aClass;
    private Object anObject = null;
    private boolean instantiateSuccessful = true;
    public AdventMessageProviderContainer(Class<?> aClass) {
        this.aClass = aClass;
        try {
            anObject = aClass.getConstructor(new Class<?>[] {}).newInstance(new Object[] {});
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            instantiateSuccessful = false;
        }
    }

    private Method methodGetHello = null;

    public boolean applicable() {
        if ( !instantiateSuccessful ) {
            return false;
        }
        try {
            methodGetHello = aClass.getMethod("getHello",new Class<?>[] {});
        } catch (NoSuchMethodException | SecurityException e) {
            return false;
        }
        return true;
    }

    @Override
    public String getHello() {
        if ( methodGetHello == null && !applicable()) {
            return null;
        }
        return getHello0();
    }

    private String getHello0() {
        if ( methodGetHello != null ) {
            try {
                return (String) methodGetHello.invoke(anObject,new Object[] {});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                return null;
            }
        }
        return null;
    }
}
