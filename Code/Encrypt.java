import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;

public class Encrypt {

    public static void main(String[] args) throws IOException{
        int exitCode = 1;
        try {
            String inFile = args[0];
            String pubKeyFile = args[1];
            String outFile = args[2];

            // String inFile = "C:\\Network\\JAVA\\Code\\plain.txt";
            // String pubKeyFile = "C:\\Network\\JAVA\\Code\\public.key";
            // String outFile = "C:\\Network\\JAVA\\Code\\cipher.txt";

            byte[] data = readFromFile(inFile);
            String contents = new String(data, "UTF-8");

            byte[] pubEncode = readFromFile(pubKeyFile);
            String pubKey = Base64.getEncoder().encodeToString(pubEncode);
            
            byte[] encryptedData = encrypt(contents, pubKey);
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


    public static PublicKey getPublicKey(String base64PublicKey){
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] encrypt(String data, String publicKey){ 
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
            return cipher.doFinal(data.getBytes());
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