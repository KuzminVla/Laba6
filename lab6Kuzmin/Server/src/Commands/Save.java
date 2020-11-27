package Commands;

import Stuff.CommandWithoutArg;
import Stuff.LabWorkCollection;
import Utility.WriterToFile;

import java.io.FileNotFoundException;

public class Save implements CommandWithoutArg {
    String name = "save";
    @Override
    public String execute(Object o) throws FileNotFoundException {
        WriterToFile.writeLabToFile(LabWorkCollection.getCollection());
       return ("Коллекция успешно сохранена.");
    }


    @Override
    public String getName() {
        return name;
    }
}