package abii.com.socket.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;


public class ServerApp {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int PORT = 3333;
    private static final int PORT_FOR_KIDS = 8080;

    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(PORT);
        var serverSocketForKids = new ServerSocket(PORT_FOR_KIDS);
        var server = new Server(serverSocket);
        var kidsServer = new Server(serverSocketForKids);

        Thread first = new Thread(kidsServer::startServer);
        Thread second = new Thread(server::startServer);

        first.start();
        second.start();
    }
}
