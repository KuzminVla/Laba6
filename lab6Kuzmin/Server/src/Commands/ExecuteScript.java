package Commands;

import Stuff.CommandWithObject;
import Stuff.Commandable;
import Stuff.Commands;
import Stuff.LabWorkCollection;
import Utility.CreateLab;
import Utility.ReaderFromFile;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ExecuteScript implements Commandable {
    String name = "execute_script";

    public static ArrayList <String> calledScripts = new ArrayList<>();
    @Override
    public String execute(Object arg) {
        StringBuilder a = new StringBuilder();
        ReaderFromFile reader = new ReaderFromFile();
        Commands invoker = new Commands();
        try {
            String data = reader.readFromFile((String) arg);
            Commands command = new Commands();
            if (data != null) {
                calledScripts.add("execute_script "+ arg);
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    if (!(commands[i].equals(""))) {
                        if (!(calledScripts.contains(commands[i]))) {
                            try {
                                String[] commandAndName = commands[i].split(" ");
                                CommandWithObject commandWithObject = (CommandWithObject) invoker.getCommand(commandAndName[0]);
                                if (commandWithObject != null) {
                                    ArrayList<String> params = new ArrayList<>();
                                    try {
                                        for (int j = 0; j <8; j++) {
                                            i++;
                                            params.add(commands[i]);
                                        }
                                        CreateLab.isFromScript = true;
                                        CreateLab creater = new CreateLab();
                                        creater.createFromFile(params);
                                        a.append("Команда \"" + commands[i-8] + "\":\n");
                                        command.executeCommand(commands[i-8]);
                                        CreateLab.isFromScript = false;
                                    } catch (IndexOutOfBoundsException e) {
                                        a.append("Недостаточно параметров\n");
                                    }
                                }
                            } catch (Exception e) {
                                a.append("Команда \"" + commands[i] + "\":\n");
                                a.append(command.executeCommand(commands[i])+"\n");
                            }

                        } else a.append("Команда \"" + commands[i] + "\": невыполнима во избежания бесконечной рекурсии.\n");
                    }
                }
            } else a.append("Указанный файл не найден.");
             if (arg.equals(calledScripts.get(1))) calledScripts.clear();
        } catch (NullPointerException | FileNotFoundException e) {
            a.append(e.getMessage());
        }
        return a.toString();
    }

    @Override
    public String getName() {
        return name;
    }
}