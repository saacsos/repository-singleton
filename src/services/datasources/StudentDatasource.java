package services.datasources;

import models.Student;
import models.StudentList;

import java.io.*;

public class StudentDatasource implements Datasource<StudentList> {
    private String directory;
    private String filename;

    public StudentDatasource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        checkFileIfExists();
    }

    private void checkFileIfExists() {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(directory + File.separator + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public StudentList readData() {
        StudentList studentList = new StudentList();
        File file = new File(directory + File.separator + filename);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line;
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                double score = Double.parseDouble(data[2].trim());
                Student student = new Student(id, name);
                student.addScore(score);
                studentList.addStudent(student);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return studentList;
    }

    @Override
    public void writeData(StudentList data) {
        File file = new File(directory + File.separator + filename);
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Student student : data.getStudents()) {
                buffer.write(student.toCSV());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
