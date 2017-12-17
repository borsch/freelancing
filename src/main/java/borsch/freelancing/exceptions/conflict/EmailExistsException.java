package borsch.freelancing.exceptions.conflict;

import borsch.freelancing.exceptions.BaseException;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class EmailExistsException extends BaseException {

    public EmailExistsException(){
        super("errors.EmailExistsException");
    }

    public EmailExistsException(String customCode){
        super(customCode);
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_CONFLICT;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage(getMessageCode(), null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
