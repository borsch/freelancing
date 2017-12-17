package borsch.freelancing.exceptions.conflict;

import borsch.freelancing.exceptions.BaseException;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andrii on 25.07.2017.
 */
public class EntityValidateException extends BaseException {

    public EntityValidateException(){
        super("errors.EmailExistsException");
    }

    public EntityValidateException(String custom){
        super(custom);
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
