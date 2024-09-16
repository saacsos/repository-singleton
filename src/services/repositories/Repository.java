package services.repositories;

import services.datasources.Datasource;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class Repository<E, T extends RepositoryArrayList<E>> {
    private final T list;
    private final Datasource<T> datasource;


    public Repository(Datasource<T> datasource) {
        this.datasource = datasource;
        list = datasource.readData();
    }

    public T getList() {
        return list;
    }

    public T filterList(RepositoryFilterer<E> filterer, Class<T> clazz) {
        T newList = null;
        try {
            newList = clazz.getDeclaredConstructor().newInstance();
            for (E e : list.getAll()) {
                if (filterer.filter(e)) {
                    newList.add(e);
                }

            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return newList;

    }

    public void commit() {
        datasource.writeData(list);
    }
}
