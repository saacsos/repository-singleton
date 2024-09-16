package controllers;

import models.Student;
import models.StudentList;
import services.repositories.RepositoryFilterer;
import services.repositories.StudentGenericRepository;
import services.repositories.StudentRepository;
import services.singletons.StudentRepositorySingleton;

public class MainProgram {
    public static void main(String[] args) {
        genericRepository();

//        addNewStudent();

//        StudentRepository studentRepository = StudentRepositorySingleton.getInstance();
//        StudentList studentList = studentRepository.getStudentList();
//        System.out.println(studentList.toCSV());

//        updateFilteredStudent();

    }

    public static void updateFilteredStudent() {
        StudentRepository studentRepository = StudentRepositorySingleton.getInstance();
        StudentList studentList = studentRepository.filterStudentListByScoreGreaterThan(20);
        System.out.println(studentList.toCSV());
        studentList.addStudentScore("1", 10);
        studentRepository.commit();
    }

    public static void addNewStudent() {
        StudentRepository studentRepository = StudentRepositorySingleton.getInstance();
        StudentList studentList = studentRepository.getStudentList();
        String studentId = studentList.addNewStudent("Jimmy");
        studentList.addStudentScore(studentId, 5);
        studentRepository.commit();
    }

    public static void genericRepository() {
        StudentGenericRepository repo = new StudentGenericRepository();
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
        repo.commit();

    }

}
