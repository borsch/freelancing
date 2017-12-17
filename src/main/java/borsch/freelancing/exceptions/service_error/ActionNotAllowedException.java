package borsch.freelancing.exceptions.service_error;

import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by Oleh on 29.07.2017.
 */
public class ActionNotAllowedException extends ServiceErrorException {

    public ActionNotAllowedException(String message) {
        super(message);
    }

    public ActionNotAllowedException() {
        this("errors.ActionNotAllowedException");
    }

    @Override
    public int getCode(){
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(getMessage(), null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }

}
