import java.io.*;

public class Main {

    public static void main(String[] args) {

        String inFile = "input.txt";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            String contents = reader.readLine();
            System.out.print(contents);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}