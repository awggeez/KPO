package abii.com.socket.chat.server;

import static abii.com.socket.chat.utils.IOUtils.close;
import static abii.com.socket.chat.utils.IOUtils.writeAndFlush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable {

    public static Map<Integer, List<ClientHandler>> clientHandlersMap = new HashMap<>();

    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String clientUsername;

    public ClientHandler(Socket socket, ServerSocket serverSocket) {
        try {
            this.socket = socket;
            this.serverSocket = serverSocket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlersMap.computeIfAbsent(serverSocket.getLocalPort(), k -> new ArrayList<>());
            clientHandlersMap.get(serverSocket.getLocalPort()).add(this);
            publishMessage("SERVER: " + clientUsername + " has entered the chat!");
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                publishMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }

    private boolean checkForName(String clientUsername) {
        var clientHandlers = clientHandlersMap.get(serverSocket.getLocalPort());
        if (clientHandlers == null) {
            return true;
        }
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(clientUsername)) {
                return false;
            }
        }
        return true;
    }

    private void publishMessage(final String messageToPublish) {
        List<ClientHandler> clientHandlers = clientHandlersMap.get(serverSocket.getLocalPort());
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    writeAndFlush(clientHandler.bufferedWriter, messageToPublish);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }

    }

    private void removeClientHandler() {
        clientHandlersMap.remove(this);
        publishMessage("SERVER: " + clientUsername + " has left the chat!");
    }

    private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        removeClientHandler();
        close(socket);
        close(bufferedWriter);
        close(bufferedReader);
    }
}
