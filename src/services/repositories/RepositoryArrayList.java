package services.repositories;

import java.util.ArrayList;

public interface RepositoryArrayList<E> {
    public ArrayList<E> getAll();
    public void add(E e);
}
