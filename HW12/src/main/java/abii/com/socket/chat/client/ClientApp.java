package abii.com.socket.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static abii.com.socket.chat.client.Client.*;
import static abii.com.socket.chat.constants.ConsoleCommands.*;
import static abii.com.socket.chat.constants.ConsoleCommands.WRONG_ANSWER;

public class ClientApp {
    private static final int PORT = 3333;
    private static final int PORT_FOR_KIDS = 8080;
    private static final String HOST = "localhost";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(ASKING_FOR_AGE);
        String answer = SCANNER.nextLine();
        while (!(NO.equals(answer) || YES.equals(answer))) {
            System.out.println(WRONG_ANSWER);
            answer = SCANNER.nextLine();
        }

        Socket socket;
        if (answer.equals(NO)) {
            socket = new Socket(HOST, PORT_FOR_KIDS);
        } else {
            socket = new Socket(HOST, PORT);
        }

        System.out.println("Please, enter your username for the group chat: ");
        var username = SCANNER.nextLine();

        Client client = new Client(socket, username);
        client.listenMessages();
        client.sendMessages();
    }
}
