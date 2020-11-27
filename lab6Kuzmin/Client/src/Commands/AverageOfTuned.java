package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;

public class AverageOfTuned implements CommandWithoutArg {
    String name = "average_of_tuned_in_works";
    @Override
    public String execute(Object o) {
        try{
            if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая.");
            else {
                double result = 0;
                int size = LabWorkCollection.getCollection().size();
                for (LabWork labWork1 : LabWorkCollection.getCollection())
                {
                    result+=labWork1.getTunedInWorks();
                    }
                return ("Средний настрой на работу: "+result/size);
            }
        } catch (Exception e) {
            return ("Аргумент команды должен быть типа \"long\"");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
