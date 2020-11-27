package Commands;

import Stuff.CommandWithObject;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.CreateLab;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Add implements CommandWithObject {
    String name = "add";
    @Override
    public boolean check(Object arg) {
        return LabWorkCollection.isKeyFree((Long) arg);
    }

    @Override
    public LabWork getNewlabwork(Object params) {
        CreateLab createLab = new CreateLab();
        if (!CreateLab.isFromScript) return createLab.create();
        else return CreateLab.labFromScript;
    }

    @Override
    public String execute(Object arg) {
        try {
            long id = Long.parseLong((String) arg);
            if (this.check(id)) {
                LabWork labWork = this.getNewlabwork(null);
                labWork.setId(id);
                LabWorkCollection.insert(labWork);
                return ("Лабораторная работа добавлена в коллекцию!");
            } else return ("Указанный ключ занят");
        } catch (NumberFormatException | InputMismatchException e) {
            return ("Аргумент команды должен быть типа \"long\"");
        }
        catch (NullPointerException e){
            return ("Неверно указаны данные.");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
