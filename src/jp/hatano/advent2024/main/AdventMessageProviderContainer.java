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
            anObject = aClass.getConstructor().newInstance();
            methodGetHello = aClass.getMethod("getHello");
            methodGetLanguageName = aClass.getMethod("getLanguageName");
            methodGetEnglishLanguageName = aClass.getMethod("getEnglishLanguageName");
        } catch ( InvocationTargetException | IllegalAccessException | InstantiationException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
            instantiateSuccessful = false;
        }
        if ( anObject != null && methodGetHello != null && methodGetLanguageName != null && methodGetEnglishLanguageName != null ) {
            instantiateSuccessful = true;
        }
        instantiateSuccessful = false;
    }

    private Method methodGetHello = null;
    private Method methodGetLanguageName = null;
    private Method methodGetEnglishLanguageName = null;

    public boolean applicable() {
        if ( instantiateSuccessful && anObject != null && methodGetHello != null && methodGetLanguageName != null && methodGetEnglishLanguageName != null ) {
            return true;
        } else {
            try {
                if ( anObject == null ) anObject = aClass.getConstructor().newInstance();
                if ( methodGetHello == null ) methodGetHello = aClass.getMethod("getHello");
                if ( methodGetLanguageName == null ) methodGetLanguageName = aClass.getMethod("getLanguageName");
                if ( methodGetEnglishLanguageName == null ) methodGetEnglishLanguageName = aClass.getMethod("getEnglishLanguageName");
            } catch ( InvocationTargetException | IllegalAccessException | InstantiationException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
                instantiateSuccessful = false;
                return false;
            }
        }
        if ( anObject != null && methodGetHello != null && methodGetLanguageName != null && methodGetEnglishLanguageName != null ) {
            instantiateSuccessful = true;
            return true;
        }
        instantiateSuccessful = false;
        return false;
    }

    @Override
    public String getHello() {
        if ( methodGetHello == null && !applicable()) {
            return null;
        }
        if ( methodGetHello != null ) {
            try {
                return (String) methodGetHello.invoke(anObject);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getLanguageName() {
        if ( methodGetLanguageName == null && !applicable() ) {
            return null;
        }
        if ( methodGetLanguageName != null ) {
            try {
                return (String) methodGetLanguageName.invoke(anObject);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.err.println("method is null");
        return null;
    }

    @Override
    public String getEnglishLanguageName() {
        if ( methodGetEnglishLanguageName == null && !applicable() ) {
            return null;
        }
        if ( methodGetEnglishLanguageName != null ) {
            try {
                return (String) methodGetEnglishLanguageName.invoke(anObject);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                return null;
            }
        }
        return null;
    }
}
