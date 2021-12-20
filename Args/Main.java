public class Main {

    public static void main(String[] args) {

        int exitCode = 1;

        try {
            
            String key = args[0];
            String scr = args[1];
            String des = args[2];

		    System.out.println(key);
            System.out.println(scr);
            System.out.println(des);

            exitCode = 0;
        }
        
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format : [key file path] [source file path] [destination file path]");
        }

        finally {
            System.exit(exitCode);
        }
    }
}