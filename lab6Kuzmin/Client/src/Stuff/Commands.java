package Stuff;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import Commands.*;
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
    public static void addToHistory(String commandName){
        if (!commandName.equals("history"))
            history.add(commandName);
    }

    public static  Map<Commandable, String> executeCommand(String commandName) {
        String[] nameAndArgument = commandName.split(" ");
        Map<Commandable, String> map = new HashMap<>();
        if (!commandName.equals("")) {
            if (commandName.equals("exit")){System.out.println("Завершаю работу");System.exit(0);}
            if (commandName.equals("history")){System.out.println(new History().execute(""));return null;}
            if (commands.get(nameAndArgument[0]) == null) {
                System.out.println("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                return null;
            } else {
                try {
                    CommandWithoutArg commandWithoutArg = (CommandWithoutArg) commands.get(nameAndArgument[0]);
                    try {
                        String s = nameAndArgument[1];
                        System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        return null;
                    } catch (Exception e) {
                        addToHistory(nameAndArgument[0]);
                       map.put(commands.get(nameAndArgument[0]),null);
                       return map;
                    }
                } catch (Exception e) {
                    try {
                        String s = nameAndArgument[2];
                        System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        return null;
                    } catch (IndexOutOfBoundsException e1) {
                        try {
                           addToHistory(nameAndArgument[0]);
                           map.put(commands.get(nameAndArgument[0]), nameAndArgument[1]);
                            return map;
                        } catch (IndexOutOfBoundsException e2) {
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }
}