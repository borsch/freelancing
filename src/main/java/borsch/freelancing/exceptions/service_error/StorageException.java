package borsch.freelancing.exceptions.service_error;

import borsch.freelancing.exceptions.BaseException;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

/**
 * Created by Oleh on 24.07.2017.
 */
public class StorageException extends BaseException {

    private String message;

    public StorageException(String message) {
        this.message = message;
    }

    public StorageException() {
        this(null);
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        if (message != null)
            return message;
        return "problems with storage";
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
