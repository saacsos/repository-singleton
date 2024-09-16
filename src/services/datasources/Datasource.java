package services.datasources;

public interface Datasource<T> {
    T readData();
    void writeData(T data);
}
