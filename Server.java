import java.io.*;
import java.net.*;
import java.util.zip.GZIPOutputStream;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected.");

            // Receive file name from client
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            String fileName = reader.readLine();

            System.out.println("Client requested: " + fileName);

            // Open requested file
            FileInputStream fileInput =
                    new FileInputStream(fileName);

            // Send compressed file
            GZIPOutputStream gzip =
                    new GZIPOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInput.read(buffer)) != -1) {
                gzip.write(buffer, 0, bytesRead);
            }

            gzip.finish();

            fileInput.close();
            gzip.close();
            socket.close();
            serverSocket.close();

            System.out.println("File sent successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
