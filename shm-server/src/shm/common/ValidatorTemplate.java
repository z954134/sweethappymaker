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
    
    @Override
    public final String validate(Map<String, Object> parameters, String name) {
        if (isValid(parameters, name)) {
            return null;
        }
        if (message == null) {
            return getDefaultMessage(parameters, name);
        }
        return message;
    }
    protected abstract String getDefaultMessage(Map<String, Object> parameters, String name); 

    protected abstract boolean isValid(Map<String, Object> parameters, String name);
}
