package pkowalski.rtcp.codefactory;


public class GenerationException extends Exception{
    private static final String DEFAULT_MESSAGE = "Generation error";



    public GenerationException(String message, Throwable cause){
        // Add your code here:
        super(message, cause);

    }


    public GenerationException(Throwable cause){
        // Add your code here:
        super(DEFAULT_MESSAGE, cause);

    }

}
