package Commands;

import Stuff.CommandWithoutArg;
import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;

public class MinByTuned implements CommandWithoutArg {
    String name ="min_by_tuned_in_works";
    @Override
    public String execute(Object o) {
            try{
                if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая.");
                else {
                    LabWork minLab = null;
                    double minimumPoint = LabWorkCollection.getCollection().iterator().next().getTunedInWorks();
                    for (LabWork labWork1 : LabWorkCollection.getCollection())
                        if (labWork1.getTunedInWorks() <= minimumPoint) {
                            minLab = labWork1;
                        }
                    if (!(minLab==null)) {
                        String answer = "";
                        answer+=("Лабораторная с наименьшем значением поля \"Настрой на работу\":\n");
                        answer+=(minLab.getInfo());
                        return answer;
                    }
                }
            } catch (Exception e) {
                return ("Аргумент команды должен быть типа \"long\"");
            }
return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
