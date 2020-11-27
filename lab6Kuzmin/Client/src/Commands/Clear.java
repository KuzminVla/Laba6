package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;

public class Clear implements CommandWithoutArg {
    String name = "clear";
    @Override
    public String execute(Object o) {
        if (LabWorkCollection.getSize() == 0) return ("Коллекция итак пустая.");
        else {
            LabWorkCollection.clear();
            return ("Коллекция очищена");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
