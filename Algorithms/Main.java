import java.util.TreeSet;
import java.security.Provider;
import java.security.Security;
import java.security.Provider.Service;

public class Main {

    public static void main(String[] args) {

        TreeSet<String> algorithms = new TreeSet<>();
        for (Provider provider : Security.getProviders()){
            for (Service service : provider.getServices()){
                if (service.getType().equals("Signature")){
                    algorithms.add(service.getAlgorithm());
                }
            }
        }

        for (String algorithm : algorithms){
            System.out.println(algorithm);
        }
    }
}