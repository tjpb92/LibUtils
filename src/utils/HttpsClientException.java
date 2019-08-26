package utils;

/**
 * Exception lanc�e en cas d'erreur sur la connexion Https
 * @author Thierry Baribaud
 * @version 0.05
 */
public class HttpsClientException extends Exception {

    /**
     * Exception lanc�e en cas d'erreur sur la connexion Https
     * (sans message)
     */
    public HttpsClientException() {
        super();
    }

    /**
     * Exception lanc�e en cas d'erreur sur la connexion Https 
     * (avec message)
     *
     * @param msg the detail message.
     */
    public HttpsClientException(String msg) {
        super(msg);
    }
}
