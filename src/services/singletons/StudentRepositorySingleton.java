package services.singletons;

import services.repositories.StudentRepository;

public class StudentRepositorySingleton {
    private static StudentRepository instance;

    private StudentRepositorySingleton() {}

    public static StudentRepository getInstance() {
        if(instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }
}
