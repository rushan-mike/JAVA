import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class Generate {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int exitCode = 1;
        try {
            String pubKey = args[0];
            String priKey = args[1];

            // String pubKey = "C:\\Network\\JAVA\\Code\\public.key";
            // String priKey = "C:\\Network\\JAVA\\Code\\private.key";

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();

            PublicKey publicKey = pair.getPublic();
            PrivateKey privateKey = pair.getPrivate(); 

            byte[] pubEncode = publicKey.getEncoded();
            byte[] priEncode = privateKey.getEncoded();

            System.out.println("\nPublicKey : ");
            System.out.println(Base64.getEncoder().encodeToString(pubEncode));

            System.out.println("\nPrivateKey : ");
            System.out.println(Base64.getEncoder().encodeToString(priEncode));
            
            writeToFile(pubKey, pubEncode);
            writeToFile(priKey, priEncode);
            exitCode = 0;
        }
        
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format : [PublicKey file path] [PrivateKey file path]");
        }

        finally {
            System.exit(exitCode);
        }

    }

    public static void writeToFile(String path, byte[] key){
        File fileInstance = new File(path);
        try {
            if (!fileInstance.createNewFile()) {
                fileInstance.getParentFile().mkdirs();
            }
         
            FileOutputStream fileOutStream = new FileOutputStream(fileInstance);
            fileOutStream.write(key);
            fileOutStream.flush();
            fileOutStream.close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}