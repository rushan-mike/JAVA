import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;

public class Decrypt {

    public static void main(String[] args) throws IOException{
        int exitCode = 1;
        try {
            String priKeyFile = args[0];
            String inFile = args[1];
            String outFile = args[2];

            // String priKeyFile = "C:\\Network\\JAVA\\Code\\private.key";
            // String inFile = "C:\\Network\\JAVA\\Code\\cipher.txt";
            // String outFile = "C:\\Network\\JAVA\\Code\\secret.txt";

            byte[] data = readFromFile(inFile);
            // String contents = new String(data);

            byte[] priEncode = readFromFile(priKeyFile);
            String priKey = new String(priEncode);
            // String priKey = Base64.getEncoder().encodeToString(priEncode);

            byte[] encryptedData = decrypt(data, priKey);
            writeToFile(outFile, encryptedData);
            exitCode = 0;
        }
        
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format : [key file path] [source file path] [destination file path]");
        }

        finally {
            System.exit(exitCode);
        }

    }


    public static PrivateKey getPrivateKey(String base64PrivateKey){
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } 
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] decrypt(byte[] data, String PrivateKey){

        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(PrivateKey));
            // return cipher.doFinal(data.getBytes());
            return cipher.doFinal(data);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readFromFile(String inFile) throws IOException{
        File fileInstance = new File(inFile);
        FileInputStream fileInStream = new FileInputStream(fileInstance);
        byte[] data = new byte[(int) fileInstance.length()];
        fileInStream.read(data);
        fileInStream.close();
        return data;
    }


    public static void writeToFile(String path, byte[] key) throws IOException{
        File fileInstance = new File(path);
        if (!fileInstance.createNewFile()) {
            fileInstance.getParentFile().mkdirs();
        }

        FileOutputStream fileOutStream = new FileOutputStream(fileInstance);
        fileOutStream.write(key);
        fileOutStream.flush();
        fileOutStream.close();
    }

}