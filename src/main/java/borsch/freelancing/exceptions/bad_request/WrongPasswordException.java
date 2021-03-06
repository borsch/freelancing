package borsch.freelancing.exceptions.bad_request;

import org.springframework.context.MessageSource;
import borsch.freelancing.exceptions.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleh_kurpiak on 11.09.2016.
 */
public class WrongPasswordException extends BaseException {

    public WrongPasswordException(){
        super("Wrong password");
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.WrongPasswordException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
