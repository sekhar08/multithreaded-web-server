
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000); // wait for 10 seconds before timing out
        while (true) {
            try {

                System.out.println("Waiting for a connection on port " + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from " + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(
                        new java.io.InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the server!");

                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            } catch (IOException e) {
                System.out.println("Error accepting connection: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            System.out.println("Server encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Server is shutting down.");

    }

}
