package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.util.Iterator;

public class Show implements CommandWithoutArg {
    String name = "show";
    @Override
    public String execute(Object o) {
        if (LabWorkCollection.getSize() == 0) return ("Коллекция пустая.");
        else {
            StringBuilder a = new StringBuilder();
            for (LabWork labWork : LabWorkCollection.getCollection()) {
                a.append(labWork.getInfo()+"\n");
            }
            return a.toString();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}