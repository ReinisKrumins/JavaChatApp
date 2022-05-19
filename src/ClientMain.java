import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        // Get a username for the user and a socket connection.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        // Create a socket to connect to the server.
        Socket socket = new Socket("localhost", 8080);

        // Pass the socket and give the client a username.
        Client client = new Client(socket, username);

        // Infinite loop to read and send messages.
        client.listenForMessage();
        client.sendMessage();
    }
}