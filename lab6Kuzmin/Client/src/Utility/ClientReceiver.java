package Utility;

import Stuff.LabWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Map;

public class ClientReceiver {
    ClientSender clientSender;
    public ClientReceiver(ClientSender clientSender){
        this.clientSender = clientSender;
    }

    public static Socket socket;
    public Object receive() throws IOException, ClassNotFoundException, SocketTimeoutException {
        socket.setSoTimeout(2500);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object obj = objectInputStream.readObject();
        CreateLab createLab = new CreateLab();
        Map<Object,Integer> answerMap = (Map<Object, Integer>) obj;
        obj = answerMap.entrySet().iterator().next().getKey();
        int a = answerMap.entrySet().iterator().next().getValue();
        if (a == 0) {
            return obj;
        }
        // новая лаба
        else if (a == 1){
            System.out.println("Необходимо заполнить доп.данные для выполнения команды");
            clientSender.send(createLab.create());
            obj =this.receive();
        }
        // апдейт маршрута с номером
        else if (a == 2){
            System.out.println("Необходимо заполнить доп.данные для выполнения команды");
            LabWork labWork = (LabWork) obj;
            System.out.println("Текущее состояние обьекта:\n"+labWork.getInfo());
            clientSender.send(createLab.create());
            obj =this.receive();
        }

        return obj;
    }
}

