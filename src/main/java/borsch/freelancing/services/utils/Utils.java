package borsch.freelancing.services.utils;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
public class Utils {

    public boolean notEmpty(String str){
        if (str!=null&&!"".equals(str))
            return true;
        return false;
    }

    public long numberOfPages(long totalNumber, long perPage) {
        long result = totalNumber / perPage;
        if (totalNumber % perPage != 0)
            return result + 1;
        return result;
    }

    public boolean validUrl(String str){
        if (!notEmpty(str))
            return false;
        Pattern p = Pattern.compile("^[a-zA-Z0-9_\\-]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static Date convertDate(String dateInUtc){
        ZonedDateTime zdt = ZonedDateTime.parse(dateInUtc, DateTimeFormatter.RFC_1123_DATE_TIME);
        return Date.from(zdt.toInstant());
    }

    public String readResponseBody(final String url) throws IOException {
        URL rest = new URL(url);
        URLConnection connection = rest.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        StringBuilder jsonBody = new StringBuilder();
        while ((line = br.readLine()) != null) {
            jsonBody.append(line);
        }
        br.close();
        return jsonBody.toString();
    }
}
