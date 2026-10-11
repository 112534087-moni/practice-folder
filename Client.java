import java.io.*;
import java.net.*;
import java.util.zip.GZIPInputStream;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket =
                    new Socket("localhost", 5000);

            System.out.println("Connected to server.");

            // Send file name to server
            PrintWriter writer =
                    new PrintWriter(socket.getOutputStream(), true);

            writer.println("sample.txt");

            System.out.println("File request sent.");

            // Receive compressed file
            GZIPInputStream gzip =
                    new GZIPInputStream(socket.getInputStream());

            // Create output file
            FileOutputStream fileOutput =
                    new FileOutputStream("received_sample.txt");

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = gzip.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

            fileOutput.close();
            gzip.close();
            socket.close();

            System.out.println("File received and decompressed successfully.");
            System.out.println("Saved as received_sample.txt");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
