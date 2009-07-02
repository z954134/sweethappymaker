package shm.common;


public class SecurityViolationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public SecurityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityViolationException(String message) {
        super(message);
    }
    


}
