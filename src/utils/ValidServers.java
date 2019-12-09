package utils;

import java.util.Arrays;
import java.util.List;

/**
 * Liste des noms de serveurs valides
 * @author Thierry Baribaud
 * @version 0.22
 */
public class ValidServers {
    
    private static final String[] validServers = {"dev", "dev2", "prod", "prod2", "pre-prod", "pre-prod2", "test", "train"};
    private static final List<String> validServersList = Arrays.asList( validServers );
    
    public static boolean isAValidServer(String server) {
        boolean retValue = false;
        if (server != null) {
            retValue = validServersList.contains(server);
        }
        return retValue;
    }
}
