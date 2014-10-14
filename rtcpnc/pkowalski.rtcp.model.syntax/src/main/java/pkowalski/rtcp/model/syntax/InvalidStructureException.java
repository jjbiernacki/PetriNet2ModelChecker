package pkowalski.rtcp.model.syntax;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    15:14:55
 *
 */
public class InvalidStructureException extends Exception{

    @SuppressWarnings({"WeakerAccess"})
    public static final String DEFAULT_MESSAGE = "Invalid RTCP-net structure";


    public InvalidStructureException(String message) {
        // Add your code here:
        super(DEFAULT_MESSAGE + ": " + message);
        
    }


    public InvalidStructureException(String message, Throwable cause){
        // Add your code here:
        super(DEFAULT_MESSAGE + ": " + message, cause);

    }



}
