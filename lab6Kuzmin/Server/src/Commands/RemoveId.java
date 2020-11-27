package Commands;

import Stuff.Commandable;
import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.io.FileNotFoundException;

public class RemoveId implements Commandable {
    String name = "remove";

    @Override
    public String execute(Object o) {
        try{
        boolean tumb = false;
        if (LabWorkCollection.getSize() == 0)return ("Коллекция итак пустая.");
        else {
            for (LabWork labWork1 : LabWorkCollection.getCollection())
                if (labWork1.getId() == Long.parseLong((String) o)) {
                    tumb = true;
                    LabWorkCollection.remove(labWork1.getId());
                    return ("Элемент с id[" + o + "] успешно удален.");
                }
            if (!tumb) return ("Лабораторная работа с указанным id не найдена.");
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
