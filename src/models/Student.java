package models;

import services.util.HasId;

public class Student implements HasId {
    private String id;
    private String name;
    private double score;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        score = 0;
    }

    public Student(String name) {
        this.name = name;
        score = 0;
        id = null;
    }

    public void setId(String id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public void addScore(double score) {
        this.score += score;
    }

    public boolean isId(String id) {
        return this.id.equals(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public String toCSV() {
        return id + "," + name + "," + score + '\n';
    }
}
