package shm.common;

import java.util.Map;

import org.slim3.controller.validator.AbstractValidator;

public abstract class ValidatorTemplate extends AbstractValidator {

    public ValidatorTemplate() {
        super();
    }
    public ValidatorTemplate(String message) {
        super(message);
    }
    
    public final String validate(Map<String, Object> parameters, String name) {
        if (isValid(parameters, name)) {
            return null;
        }
        String msg = getMessage(parameters, name);
        return msg;
    }
    
    private String getMessage(Map<String, Object> parameters, String name) {
        String msg = null;
        msg = message != null ? message : getDefaultMessage(parameters, name);
        if (msg == null) {
            throw new IllegalStateException("Can't get message");
        }
        return msg;
    }
    
    protected abstract String getDefaultMessage(Map<String, Object> parameters, String name); 

    protected abstract boolean isValid(Map<String, Object> parameters, String name);
}
