package Commands;

import Stuff.CommandWithObject;
import Stuff.LabWork;
import Stuff.LabWorkCollection;
import Utility.CreateLab;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class AddIfMax implements CommandWithObject {
    String name = "add_if_max";

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
    public String execute(Object o)  {
        try {
            long id = Long.parseLong((String) o);
            if (this.check(id)) {
                ServerSender.send(null,1);
                LabWork labWork = (LabWork) ServerReceiver.receive();
                labWork.setId(id);
                LabWork maxlab = LabWorkCollection.getCollection().iterator().next();
                for (LabWork labWork1 : LabWorkCollection.getCollection()){
                    if (labWork1.compareTo(maxlab)>=0){
                        maxlab = labWork1;
                    }
                }
                if (labWork.compareTo(maxlab)>0) {
                    LabWorkCollection.insert(labWork);
                    return ("Лабораторная работа добавлена в коллекцию!");
                }else return ("Заданный элемент меньше максимального,добавить не удалось");
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
