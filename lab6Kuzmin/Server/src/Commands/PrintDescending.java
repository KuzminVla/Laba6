package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class PrintDescending implements CommandWithoutArg {
    String name = "print_descending";

    @Override
    public String execute(Object o) {
        try {
            if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая,выводить нечего.");
            else {
                StringBuilder a = new StringBuilder();
                a.append("Элементы в порядке убывания по кол-ву часов,необходимх на обучение.\n");
                HashSet<LabWork> set =  new HashSet<>(LabWorkCollection.getCollection());
                int size=set.size();
                for (int i = 0; i<size; i++) {
                    LabWork labWork = set.iterator().next();
                    for (LabWork labWork1 : set)
                        if (labWork.compareTo(labWork1) <= 0) {
                            labWork = labWork1;
                        }
                    a.append(labWork.getInfo()).append("\n");
                    set.remove(labWork);
                }
                return a.toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Override
    public String getName() {
        return name;
    }
}
