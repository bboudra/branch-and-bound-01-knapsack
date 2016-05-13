package Exceptions;

/**
 * Created by benbo on 5/2/2016.
 */
public class ExceededMaxSizeException extends Exception {

    public ExceededMaxSizeException()
    {
        super();
    }

    public ExceededMaxSizeException(String message)
    {
        super(message);
    }
}
