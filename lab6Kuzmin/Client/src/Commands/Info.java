package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWorkCollection;

public class Info implements CommandWithoutArg {
    String name = "info";
    @Override
    public String execute(Object o) {
        return (LabWorkCollection.getInfo());
    }

    @Override
    public String getName() {
        return name;
    }
}