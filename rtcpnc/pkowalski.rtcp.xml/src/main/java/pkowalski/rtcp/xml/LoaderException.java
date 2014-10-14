package pkowalski.rtcp.xml;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    13:44:54
 *
 */
public class LoaderException extends Exception{

    @SuppressWarnings({"WeakerAccess"})
    public static final String DEFAULT_MESSAGE = "Invalid RTCP-net structure";


    @SuppressWarnings({"SameParameterValue"})
    public LoaderException(String message, Throwable cause){
        // Add your code here:
        super(DEFAULT_MESSAGE + ": " + message, cause);

    }


    public LoaderException(Throwable cause) {
        // Add your code here:
        super(DEFAULT_MESSAGE, cause);
        
    }


}
