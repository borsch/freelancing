package borsch.freelancing.exceptions;

import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public abstract class BaseException extends Exception {

    private int code;
    private String messageCode;

    public BaseException(){
        this("errors.BaseException");
    }

    public BaseException(String message){
        super(message);
        this.messageCode = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * override this method to create custom message for users
     *
     * @return converted message
     */
    public abstract String formMessage(MessageSource messageSource, Locale locale);

    /**
     * override this method to create a list of errors for user
     *
     * return null to show that exception has no list of errors
     *
     * @return list of errors
     */
    public abstract List<String> formListErrors(MessageSource messageSource, Locale locale);
}
