/**
 * 
 */
package shm.cool.common;

/**
 * @author Tsuyoshi
 *
 */
public class SystemException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
