package Commands;

import Stuff.CommandWithObject;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.CreateLab;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class UpdateId implements CommandWithObject {
    String name = "update";

    @Override
    public boolean check(Object arg) {
        return !LabWorkCollection.isKeyFree((Long) arg);
    }

    @Override
    public LabWork getNewlabwork(Object params) {CreateLab createLab = new CreateLab();
        if (!CreateLab.isFromScript) return createLab.create();
        else return CreateLab.labFromScript;
    }

    @Override
    public String execute(Object arg) throws FileNotFoundException {
        try {
            long id = Long.parseLong((String) arg);
            if (this.check(id)) {
                LabWork old = LabWorkCollection.getCollection().stream().filter(x->x.getId()==id).findAny().get();
                ServerSender.send(old,2);
                LabWork labWork = (LabWork) ServerReceiver.receive();
                if (labWork != null) {
                    labWork.setId(id);
                    LabWorkCollection.update( id, labWork);
                    return ("Лабораторная работа с id[" + arg + "] успешно обновлена.");
                } throw new NullPointerException();
            } else return ("Лабораторная работа с указанным id не найден.");
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
