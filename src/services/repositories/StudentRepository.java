package services.repositories;

import models.Student;
import models.StudentList;
import services.datasources.Datasource;
import services.datasources.StudentDatasource;

public class StudentRepository {
    private final StudentList studentList;
    private final Datasource<StudentList> datasource;

    public StudentRepository() {
        datasource = new StudentDatasource("data", "student-data.csv");
        studentList = datasource.readData();
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public StudentList filterStudentListByScoreGreaterThan(double score) {
        StudentList list = new StudentList();
        for (Student student : studentList.getStudents()) {
            if (student.getScore() > score) {
                list.addStudent(student);
            }
        }
        return list;
    }

    public void commit() {
        datasource.writeData(studentList);
    }


}
