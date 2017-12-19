package borsch.freelancing.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by olehkurpiak on 19.12.2017.
 */
public final class Coefficients {

    private Coefficients() { }

    public static void setCoefficient(String code, float value) {
        COEFFICIENTS_MAP.put(code, value);
    }

    public static float getCoefficient(String code) {
        Float value = COEFFICIENTS_MAP.get(code);

        if (value == null) {
            throw new IllegalArgumentException(String.format(
                    "No coefficient with codename [%s]", code
            ));
        }

        return value;
    }

    public static final String SKILL_LEVEL_WEIGHT = "SKILL_LEVEL_WEIGHT";
    public static final String TAGS_WEIGHT = "TAGS_WEIGHT";
    public static final String RATING_WEIGHT = "RATING_WEIGHT";

    // public -  bad solution. just to access in controller without additional getmap method
    public static final Map<String, Float> COEFFICIENTS_MAP = new HashMap<>();
    static {
        COEFFICIENTS_MAP.put(SKILL_LEVEL_WEIGHT, 0.15f);
        COEFFICIENTS_MAP.put(TAGS_WEIGHT, 0.8f);
        COEFFICIENTS_MAP.put(RATING_WEIGHT, 0.05f);
    }
}
