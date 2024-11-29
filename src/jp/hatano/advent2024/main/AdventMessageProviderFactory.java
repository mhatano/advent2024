package jp.hatano.advent2024.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdventMessageProviderFactory implements Iterable<AdventMessageProvider>, Iterator<AdventMessageProvider> {
    private final List<AdventMessageProvider> listProviders = new ArrayList<>();
    public AdventMessageProviderFactory(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ( reader.ready() ) {
                String providerName = reader.readLine();
                Class<?> aClass = Class.forName(providerName);
                listProviders.add((AdventMessageProvider)aClass.getConstructor(new Class[]{}).newInstance(new Object[] {}));
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
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
