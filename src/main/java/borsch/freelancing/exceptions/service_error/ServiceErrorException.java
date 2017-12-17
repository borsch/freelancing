package borsch.freelancing.exceptions.service_error;

import org.springframework.context.MessageSource;
import borsch.freelancing.exceptions.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class ServiceErrorException extends BaseException {


    public ServiceErrorException(String message) {
        super(message);
    }

    public ServiceErrorException() {
        this("Internal Server Error");
    }

    @Override
    public int getCode(){
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.ServiceErrorException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
