package services.repositories;

public interface RepositoryFilterer<E> {
    boolean filter(E element);
}
