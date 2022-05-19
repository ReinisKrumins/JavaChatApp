import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    static Logger log = Logger.getLogger(Server.class.getName());
    private ServerSocket serverSocket;

    private int socketCounter = 0;
    private int threadCounter = 0;

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
                log.info("New connection with nr.: " + (socketCounter++));
                ClientHandler clientHandler = new ClientHandler(socket);

                // Will put a new connection in a different thread
                //int threadCounter = 0;
                Thread thread = new Thread(clientHandler);
                thread.start();
                log.info("New thread with nr.: " + (threadCounter++));
            }
        } catch (IOException e) {

        }
    }
    public void closeServerSocket() {
        // If there is no connection then the socket will close
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
