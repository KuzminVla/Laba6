package Utility;

import Stuff.Coordinates;
import Stuff.Difficulty;
import Stuff.Discipline;
import Stuff.LabWork;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateLab {
    public static boolean isFromScript;
    Scanner scanner = new Scanner(System.in);
    String whyFailed = "";
    public static LabWork labFromScript;

    public LabWork create() {
        LabWork labWork = new LabWork();
        this.setName(labWork);
        Coordinates coordinates = new Coordinates();
        this.setCoordinateX(coordinates);
        this.setCoordinateY(coordinates);
        labWork.setCoordinates(coordinates);
        labWork.setCreationDate(LocalDate.now());
        this.setTunedInWorks(labWork);
        this.setMinimalPoint(labWork);
        this.setDifficulty(labWork);
        Discipline discipline = new Discipline();
        this.setDisName(discipline);
        this.setDisHours(discipline);
        labWork.setDiscipline(discipline);
        return labWork;
    }
    public void createFromFile(Object pars) {
        try {
            ArrayList<String> params = (ArrayList) pars;
            params.stream().forEach(x-> System.out.println(x));
            LabWork labWork = new LabWork();
            this.setName(labWork, params.get(0));
            Coordinates coordinates = new Coordinates();
            this.setCoordinateX(coordinates, params.get(1));
            this.setCoordinateY(coordinates, params.get(2));
            this.setMinimalPoint(labWork,params.get(3));
            this.setTunedInWorks(labWork,params.get(4));
            this.setDifficulty(labWork,params.get(5));
            Discipline discipline = new Discipline();
            this.setDisName(discipline,params.get(6));
            this.setDisHours(discipline,params.get(7));
            labWork.setDiscipline(discipline);
            labWork.setCoordinates(coordinates);
            labWork.setCreationDate(LocalDate.now());
            labFromScript = labWork;
            isFromScript = true;
        } catch (Exception e) {
            System.out.println(whyFailed);
            whyFailed = "";
        }
    }

    public void setName(LabWork labWork) {
        System.out.println("Введите название лабораторной работы:");
        System.out.print("~ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals(null)) this.setName(labWork);
        labWork.setName(name);
    }

    public void setCoordinateX(Coordinates coords) {
        try {
            System.out.println("Введите координату x:");
            System.out.print("~ ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setCoordinateX(coords);
            else {
                float xn = Float.parseFloat(x);
                if (xn <= 30) {
                    coords.setX(xn);
                } else {
                    System.out.println("Максимальное значение поля: 30");
                    this.setCoordinateX(coords);
                }
                coords.setX(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"float\".");
            this.setCoordinateX(coords);
        }
    }

    public void setCoordinateY(Coordinates coords) {
        try {
            System.out.println("Введите координату y:");
            System.out.print("~ ");
            String y = scanner.nextLine();
            if (y.equals("") || y.equals(null)) this.setCoordinateY(coords);
            else {
                float xn = Float.parseFloat(y);
                if (xn <= 387) coords.setY(xn);
                else {
                    System.out.println("Максимальное значение поля: 387");
                    this.setCoordinateY(coords);
                }
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"float\".");
            this.setCoordinateY(coords);
        }
    }

    public void setMinimalPoint(LabWork labWork) {
        try {
            System.out.println("Введите минимальную оценку:");
            System.out.print("~ ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setMinimalPoint(labWork);
            else{
                double xn = Double.parseDouble(x);
            labWork.setMinimalPoint(xn);
        }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"double\".");
            this.setMinimalPoint(labWork);
        }
    }

    public void setTunedInWorks(LabWork labWork) {
        try {
            System.out.println("Введите настрой на работу:");
            System.out.print("~ ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setTunedInWorks(labWork);
            else {
                long xn = Long.parseLong(x);
                labWork.setTunedInWorks(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"long\".");
            this.setTunedInWorks(labWork);
        }
    }

    public void setDifficulty(LabWork labWork) {
        System.out.println("Введите сложность работы,регистр не важен.(VERY_EASY,IMPOSSIBLE,HOPELESS):");
        System.out.print("~ ");
        String dif = scanner.nextLine().toUpperCase();
        try {
            labWork.setDifficulty(Difficulty.valueOf(dif));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
            this.setDifficulty(labWork);
        }
    }
    public void setDisName(Discipline discipline){
        System.out.println("Введите предмет по которому лабораторная работа:");
        System.out.print("~ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals(null)) this.setDisName(discipline);
       else discipline.setName(name);

    }
    public void setDisHours(Discipline discipline){
        try {
            System.out.println("Введите кол-во часов для освоения дисциплины:");
            System.out.print("~ ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setDisHours(discipline);
            else {
                int xn = Integer.parseInt(x);
                discipline.setSelfStudyHours(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"int\".");
            this.setDisHours(discipline);
        }
    }

    public void setName(LabWork labWork,String name) throws  Exception{
        if (name.equals("") || name.equals(null)){
         whyFailed+="У лабораторной работы отсутствует имя.";
         throw null;
        }else labWork.setName(name);
    }

    public void setCoordinateX(Coordinates coords,String x) throws Exception {
        try {
            if (x.equals("") || x.equals(null)) whyFailed+= "У лабораторной работы отсутвует координата х.";
            else {
                float xn = Float.parseFloat(x);
                if (xn <= 30) coords.setX(xn);
                else {
                    whyFailed += ("Максимальное значение координаты х  30");
                    throw null;
                }
                coords.setX(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed+=("Значение координаты х должно быть типа:\"float\".");
            throw new Exception();
        }
    }

    public void setCoordinateY(Coordinates coords,String y) throws Exception {
        try {
            if (y.equals("") || y.equals(null)) {
                whyFailed+= "У лабораторной работы отсутвует координата y.";
                throw null;
            }
            else {
                float xn = Float.parseFloat(y);
                if (xn <= 387) coords.setY(xn);
                else {
                    whyFailed += ("Максимальное значение координаты у: 387");
                    throw null;
                }
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение поля координаты у должно быть типа:\"float\".");
            throw new Exception();
        }
    }

    public void setMinimalPoint(LabWork labWork,String x) throws Exception {
        try {
            if (x.equals("") || x.equals(null)) {
                whyFailed+="У лабораторной отсутвует минимальная оценка.";
                throw null;
            }else {
                double xn = Double.parseDouble(x);
                labWork.setMinimalPoint(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed+=("Значение минимальной оценки должно быть типа:\"double\".");
            throw  new Exception();
        }
    }

    public void setTunedInWorks(LabWork labWork ,String tunedInWorks) throws Exception {
        try {
            long xn = Long.parseLong(tunedInWorks);
            labWork.setTunedInWorks(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed+=("Значение настроя на работу должно быть типа:\"long\".");
            throw  new Exception();
        }
    }

    public void setDifficulty(LabWork labWork,String dif) throws Exception {
        try {
            labWork.setDifficulty(Difficulty.valueOf(dif.toUpperCase()));
        } catch (Exception e) {
            whyFailed+=("Значение сложности должно соответствовать перечислинным типам.");
           throw new Exception();
        }
    }
    public void setDisName(Discipline discipline,String name){
        if (name.equals("") || name.equals(null)){
            whyFailed+="У дисциплины лабораторной работы отсутствует имя.";
            throw null;
        }else discipline.setName(name);
    }
    public void setDisHours(Discipline discipline, String x) throws Exception {
        try {
            if (x.equals("") || x.equals(null)) {
                whyFailed+="У дисциплины отсутвует кол-во часов для освоения.";
                throw null;
            }
            else {
                int xn = Integer.parseInt(x);
                discipline.setSelfStudyHours(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed+=("Значение должно быть типа:\"int\".");
            throw new Exception();
        }
    }
}