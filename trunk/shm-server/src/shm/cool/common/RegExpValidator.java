package shm.cool.common;

import org.slim3.util.ApplicationMessage;


public class RegExpValidator extends SingleItemValidator {

    protected String pattern;
    
    public RegExpValidator(String pattern) {
        this(pattern, null);
    }
    
    public RegExpValidator(String pattern, String message) {
        super(message);
        this.pattern = pattern;
    }

    @Override
    protected boolean isValid(Object item) {
        if (item == null) {
            return false;
        }
        return item.toString().matches(pattern);
    }

    @Override
    protected String getDefaultMessage(Object param, String name) {
        return ApplicationMessage.get("validator.regexp", getLabel(name));
    }


}
