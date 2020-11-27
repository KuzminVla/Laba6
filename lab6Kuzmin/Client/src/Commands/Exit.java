package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWorkCollection;

public class Exit implements CommandWithoutArg {
    String name = "exit";

    @Override
    public String execute(Object o) {
        System.exit(0); return null;
    }

    @Override
    public String getName() {
        return name;
    }
}