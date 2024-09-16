package services.repositories;

import models.Student;
import models.StudentList;
import services.datasources.Datasource;
import services.datasources.StudentDatasource;

public class StudentGenericRepository extends Repository<Student, StudentList> {
    public StudentGenericRepository() {
        super(new StudentDatasource("data", "student-data.csv"));
    }
}
