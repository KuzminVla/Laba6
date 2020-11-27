package Stuff;

import java.io.Serializable;
import java.time.LocalDate;

public class LabWork implements Comparable, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double minimalPoint; //Значение поля должно быть больше 0
    private long tunedInWorks;
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле не может быть null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public long getTunedInWorks() {
        return tunedInWorks;
    }

    public void setTunedInWorks(long tunedInWorks) {
        this.tunedInWorks = tunedInWorks;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public String getInfo() {
        return "Лабораторная работа [id:" + id + "]:\n\t" + "Название: " + name + "\n\tКоординаты:\n\t\tx: " + coordinates.getX() +
                "\n\t\ty: " + coordinates.getY() +  "\n\tДата cоздания: " + creationDate + "\n\tМинимальная оценка: " + minimalPoint +
                "\n\tНастрой на работу: "+ tunedInWorks + "\n\tСложность: " + difficulty.name() +
                "\n\tДисциплина:\n\t\tНазвание: " + discipline.getName() + "\n\t\tКол-во часов для освоения: " + discipline.getSelfStudyHours();
    }

    @Override
    public int compareTo(Object o) {
        return this.getDiscipline().getSelfStudyHours()-((LabWork) o).getDiscipline().getSelfStudyHours();
    }
}

