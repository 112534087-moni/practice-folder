import java.io.*;
import java.util.Scanner;

public class FileInformation {
public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter file name: ");
    String filename = sc.nextLine();

    File file = new File(filename);

    if (!file.exists()) {
        System.out.println("File not found!");
        sc.close();
        return;
    }

    if (filename.endsWith(".txt")) {

        BufferedReader br = new BufferedReader(
            new FileReader(file));

        String line;
        int lines = 0;
        int words = 0;

        while ((line = br.readLine()) != null) {
            lines++;

            String trimmed = line.trim();

            if (!trimmed.isEmpty()) {
                words += trimmed.split("\\s+").length;
            }
        }

        br.close();

        System.out.println("Number of lines: " + lines);
        System.out.println("Number of words: " + words);

    } else if (filename.endsWith(".xml")
            || filename.endsWith(".json")) {

        BufferedReader br = new BufferedReader(
            new FileReader(file));

        String line;

        System.out.println("File data:");

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();

    } else {
        System.out.println("Unsupported file type!");
    }

    sc.close();
}

}
