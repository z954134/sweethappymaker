package shm.cool.common;

import java.util.Map;


public abstract class SingleItemValidator extends ValidatorTemplate {

    public SingleItemValidator() {
        super();
    }

    public SingleItemValidator(String message) {
        super(message);
    }

    @Override
    protected boolean isValid(Map<String, Object> parameters, String name) {
        Object param = parameters.get(name);
        return isValid(param);
    }
    
    @Override
    protected String getDefaultMessage(Map<String, Object> parameters, String name) {
        Object param = parameters.get(name);
        return getDefaultMessage(param, name);
    }
    
    protected abstract String getDefaultMessage(Object param, String name);

    protected abstract boolean isValid(Object param);
}
