import Stuff.*;
import Utility.ClientReceiver;
import Utility.ClientSender;
import Utility.Console;
import Commands.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Commands commands = new Commands();
        commands.regist(new Show(),new History(),new Info(),new Help(),new Clear(),new Exit(), new UpdateId(),new Add(),new ExecuteScript(),new AddIfMax(),new AverageOfTuned(),new MinByTuned(),new PrintDescending(),new RemoveGreater(), new RemoveId());
        boolean flag = true;
        ClientSender clientSender = null;
        Scanner in = new Scanner(System.in);
        while (flag) {
            try {

                System.out.print("Введите порт сервера\n~ ");
                int port = Integer.parseInt(in.nextLine());
                clientSender = new ClientSender(port);
                ClientSender.socketChannel = SocketChannel.open(ClientSender.socketAddress);
                ClientSender.socketChannel.close();
                flag = false;

            } catch (Exception e) {
                System.out.println("Неккоректные данные.");
            }
        }
        ClientReceiver receiver = new ClientReceiver(clientSender);
        while (true) {
            System.out.println("Введите команду для отправки на сервер");
            System.out.print("~ ");
            String commandName = in.nextLine();
            if (!commandName.equals("")) {
                try {
                    Map<Commandable, String> map = Commands.executeCommand(commandName);
                    if (map != null) {
                        ClientSender.connect();
                        clientSender.send(map);
                        System.out.println(receiver.receive());
                        ClientSender.socketChannel.close();
                    }
                } catch (SocketTimeoutException | InterruptedException e) {
                    System.out.println("Возможно сервер занят другим пользователем,попробуйте позже..");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}