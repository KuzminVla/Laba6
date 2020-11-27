package Stuff;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Commands {
    private static Map<String, Commandable> commands = new TreeMap<>();
    private static ArrayList<String> history = new ArrayList<>();
    public static ArrayList<String> getHistory() {
        return history;
    }

    public Commandable getCommand(String commandname) {
        return commands.get(commandname);
    }

    public void regist(Commandable... commands) {
        for (Commandable command : commands) {
            Commands.commands.put(command.getName(), command);
        }
    }
    public void addToHistory(String commandName){
        if (!commandName.equals("history"))
            history.add(commandName);
    }

    public String executeCommand(String commandName) {
        String[] nameAndArgument = commandName.split(" ");
        if (!commandName.equals("")) {
            if (commands.get(nameAndArgument[0]) == null) {
                return ("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
            } else {
                try {
                    CommandWithoutArg commandWithoutArg = (CommandWithoutArg) commands.get(nameAndArgument[0]);
                    try {
                        String s = nameAndArgument[1];
                        return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                    } catch (Exception e) {
                        this.addToHistory(nameAndArgument[0]);
                        return commands.get(nameAndArgument[0]).execute(null);
                    }
                } catch (Exception e) {
                    try {
                        String s = nameAndArgument[2];
                        return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                    } catch (IndexOutOfBoundsException e1) {
                        try {
                            this.addToHistory(nameAndArgument[0]);
                            return commands.get(nameAndArgument[0]).execute(nameAndArgument[1]);

                        } catch (IndexOutOfBoundsException | FileNotFoundException e2) {
                           return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        }
                    }
                }
            }
        }
        return null;
    }
}