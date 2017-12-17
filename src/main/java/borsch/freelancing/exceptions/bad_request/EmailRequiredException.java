package borsch.freelancing.exceptions.bad_request;

import borsch.freelancing.exceptions.BaseException;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by Oleh on 09.09.2017.
 */
public class EmailRequiredException extends BaseException {

    public EmailRequiredException(){
        super("Wrong email");
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.EmailRequiredException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }

}
