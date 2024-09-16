package controllers;

import models.Student;
import models.StudentList;
import services.repositories.RepositoryFilterer;
import services.repositories.StudentGenericRepository;
import services.singletons.StudentRepositorySingleton;

public class MainProgram {
    public static void main(String[] args) {
        genericRepository();

        addNewStudent();

        StudentGenericRepository studentRepository = StudentRepositorySingleton.getInstance();
        StudentList studentList = studentRepository.getList();
        System.out.println(studentList.toCSV());
        System.out.println("2: " + StudentRepositorySingleton.getLastCreated());

    }

    public static void addNewStudent() {
        StudentGenericRepository studentRepository = StudentRepositorySingleton.getInstance();
        StudentList studentList = studentRepository.getList();
        String studentId = studentList.addNewStudent("Jimmy");
        studentList.addStudentScore(studentId, 5);
//        studentRepository.commit();
        System.out.println("1: " + StudentRepositorySingleton.getLastCreated());
    }

    public static void genericRepository() {
        StudentGenericRepository repo = StudentRepositorySingleton.getInstance();
        StudentList studentList = repo.filterList(new RepositoryFilterer<Student>() {
            @Override
            public boolean filter(Student student) {
                return student.getScore() > 10;
            }
        }, StudentList.class);
        System.out.println(studentList.toCSV());
        System.out.println(studentList.getLastId()); // avoid adding new data to filtered list
        System.out.println(repo.getList().getLastId()); // always adding new data to list in repo

        String studentId = repo.getList().addNewStudent("Joey");
        studentList.addStudentScore(studentId, 20);
        System.out.println(repo.getList().toCSV());
//        repo.commit();
        System.out.println("0: " + StudentRepositorySingleton.getLastCreated());
    }

}
