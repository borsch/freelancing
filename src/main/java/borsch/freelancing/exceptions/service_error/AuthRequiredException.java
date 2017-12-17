package borsch.freelancing.exceptions.service_error;

import borsch.freelancing.exceptions.BaseException;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andrii on 10.09.2016.
 */
public class AuthRequiredException extends BaseException {

    @Override
    public int getCode(){
        return HttpServletResponse.SC_UNAUTHORIZED;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.AuthRequiredException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
