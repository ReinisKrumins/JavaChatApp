import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    static Logger log = Logger.getLogger(Server.class.getName());
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        log.info("Starting server!");
        try {
            //Server will run for as long as server socket is open
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); //
                // System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                // Will put a new connection in a different thread
                //int threadCounter = 0;
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {

        }
    }
    public void closeServerSocket() {
        // If there is no connection then the socket will close
        log.info("Server closed");
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
