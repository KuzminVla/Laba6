package Commands;

import Stuff.CommandWithoutArg;
import Stuff.*;
import java.util.ArrayList;

public class History implements CommandWithoutArg {
    String name = "history";

    @Override
    public String execute(Object o) {
        ArrayList<String> history = Commands.getHistory();
        if (history.size() == 0) return ("История пустая.");
        else {
            StringBuilder answer = new StringBuilder("");
            int numOfCommands = history.size();
            answer.append("Последние выполненные команды:\n");
            try{
                for(int i=numOfCommands;i > numOfCommands-12;i--) {
                    answer.append(history.get(i-1)+"\n");
                }
                return answer.toString();
            }
            catch(IndexOutOfBoundsException e) {
                return "ошибка "+e.getMessage();
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
}