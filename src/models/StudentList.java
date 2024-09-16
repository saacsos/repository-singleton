package models;

import services.repositories.RepositoryArrayList;
import services.util.HasId;
import services.util.IdService;

import java.util.ArrayList;

public class StudentList implements RepositoryArrayList<Student> {
    private ArrayList<Student> students;
    private IdService idService;

    public StudentList() {
        idService = new IdService();
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        idService.compareLastId(student);
    }

    public String addNewStudent(String name) {
        Student student = new Student(name);
        idService.setNextId(student);
        addStudent(student);
        return idService.getLastId();
    }

    public void addStudentScore(String id, double score) {
        for (Student student : students) {
            if (student.isId(id)) {
                student.addScore(score);
            }
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return students.toString();
    }

    public String toCSV() {
        String result = "";
        for (Student student : students) {
            result += student.toCSV();
        }
        return result;
    }

    @Override
    public ArrayList<Student> getAll() {
        return students;
    }

    @Override
    public void add(Student student) {
        students.add(student);
        idService.compareLastId(student);
    }

    public String getLastId() {
        return idService.getLastId();
    }
}
