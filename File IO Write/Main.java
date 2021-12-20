import java.io.*;

public class Main {

    public static void main(String[] args) {

        String outFile = "output.txt";
        String contents = "Happy file";
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            writer.write(contents);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}