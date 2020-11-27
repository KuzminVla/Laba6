package Utility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerReceiver {
    private static ServerSocketChannel serverSocketChan;
    private static ByteBuffer buffer = ByteBuffer.allocate(10000);
    private static SocketChannel socketChannel;
    public static Boolean isBusy = false;

    public ServerReceiver(String host, int serverPort) throws IOException {
        try {
            System.out.println("Запускаю сервер.");
            serverSocketChan = ServerSocketChannel.open();
            serverSocketChan.bind(new InetSocketAddress(host, serverPort));
            serverSocketChan.configureBlocking(false);
        }
        catch (Exception e){
            System.out.println("Не удалось запустить сервер,возможно указанный порт занят.");
            System.exit(0);
        }
    }
    public static Object receive() {
        while (true) {
            try {
                if (!isBusy) {
                    socketChannel = serverSocketChan.accept();
                }
                if (socketChannel != null&&socketChannel.isOpen()) {
                    ServerSender.currentClientSocket = socketChannel.socket();
                    socketChannel.read(buffer);
                    buffer.flip();
                    int limit = buffer.limit();
                    byte bytes[] = new byte[limit];
                    buffer.get(bytes, 0, limit);
                    buffer.clear();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Object object = objectInputStream.readObject();
                    objectInputStream.close();
                    byteArrayInputStream.close();
                    return object;
                }
            } catch (IOException e) {
                break;
            } catch (NullPointerException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}