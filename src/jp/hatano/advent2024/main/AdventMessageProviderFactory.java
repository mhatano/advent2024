package jp.hatano.advent2024.main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Paths;

public class AdventMessageProviderFactory implements Iterable<AdventMessageProvider>, Iterator<AdventMessageProvider> {
    private final List<AdventMessageProvider> listProviders = new ArrayList<>();
    public AdventMessageProviderFactory(String packageName) {
        String currentClassPath;
        String thisPackageName = getClass().getPackageName().replace(".",File.separator);
        String targetPackageName = packageName.replace(".",File.separator);
        try {
            URL classPath = getClass().getResource(".");
            if ( classPath != null ) {
                currentClassPath = Paths.get(classPath.toURI()).toAbsolutePath().toString().replace(thisPackageName,targetPackageName);
                File[] providerFiles = new File(currentClassPath).listFiles();
                if ( providerFiles != null ) {
                    for (File providerFile : providerFiles) {
                        String providerAbsolutePath = providerFile.getAbsolutePath();
                        String filename = providerAbsolutePath.substring(providerAbsolutePath.lastIndexOf(File.separatorChar)+1);
                        String className = packageName + "." + filename.replace(".class","");
                        Class<?> aClass = Class.forName(className);
                        listProviders.add((AdventMessageProvider) aClass.getConstructor(new Class[]{}).newInstance(new Object[]{}));
                    }
                }
            }
        } catch (URISyntaxException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int size() {
        return listProviders.size();
    }

    public AdventMessageProvider get(int i) {
        return listProviders.get(i);
    }

    public AdventMessageProvider get(String name) {
        for ( AdventMessageProvider provider : listProviders ) {
            if ( provider.getClass().getName().equals(name) ) {
                return provider;
            }
        }
        return null;
    }

    @Override
    public Iterator<AdventMessageProvider> iterator() {
        return listProviders.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator().hasNext();
    }

    @Override
    public AdventMessageProvider next() {
        return iterator().next();
    }
}
