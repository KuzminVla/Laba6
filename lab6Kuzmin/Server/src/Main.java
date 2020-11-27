import Commands.*;
import Stuff.Commandable;
import Stuff.Commands;
import Utility.Collection;
import Utility.ReaderFromFile;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "";
        if (args.length != 0) filename = args[0];
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Введите порт сервера\n~ ");
                int port = Integer.parseInt(in.nextLine());

                ServerReceiver serverReceiver = new ServerReceiver("localhost", port);
                flag = false;
            } catch (Exception e) {
                System.out.println("Неккоректные данные.");
            }
        }
        ServerSender serverSender = new ServerSender();
        ReaderFromFile readerFromFile = new ReaderFromFile();
        Collection collection = new Collection();
        collection.fillCollection(readerFromFile.readFromFile(filename));
        Commands commands = new Commands();
        commands.regist(new Show(), new Info(), new Help(), new Clear(), new Exit(), new Save(), new UpdateId(), new Add(), new ExecuteScript(), new AddIfMax(), new AverageOfTuned(), new MinByTuned(), new PrintDescending(), new RemoveGreater(), new RemoveId());
        checkForSaveCommand();

        while (true) {
            try {
                Map<Commandable, String> commandableStringMap = (Map<Commandable, String>) ServerReceiver.receive();
                ServerReceiver.isBusy = true;
                System.out.println("Принял команду " + commandableStringMap.entrySet().iterator().next().getKey().getName() + " от клиента.");
                String answer = commandableStringMap.entrySet().iterator().next().getKey().execute(commandableStringMap.entrySet().iterator().next().getValue());
                serverSender.send(answer, 0);   
                ServerReceiver.isBusy = false;
            } catch (Exception e) {
                ServerSender.currentClientSocket.close();
                ServerReceiver.isBusy = false;
            }
        }
    }

    private static Thread backgroundReaderThread = null;

    public static void checkForSaveCommand() throws IOException {
        backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            Save s = new Save();
                            System.out.println(s.execute(null));
                        }
                        if (line.equalsIgnoreCase("exit")) {
                            Save s = new Save();
                            System.out.println(s.execute(null));
                            System.out.println("Завершаю работу.");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.start();
    }
}


