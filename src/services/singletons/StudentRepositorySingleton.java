package services.singletons;

import services.repositories.StudentGenericRepository;
import services.repositories.StudentRepository;

import java.time.LocalDateTime;

public class StudentRepositorySingleton {
    private static StudentGenericRepository instance;
    private static LocalDateTime lastCreated;

    private StudentRepositorySingleton() {}

    public static StudentGenericRepository getInstance() {
        if(instance == null) {
            instance = new StudentGenericRepository();
            lastCreated = LocalDateTime.now();
        }
        return instance;
    }

    public static String getLastCreated() {
        return lastCreated.toString();
    }
}
