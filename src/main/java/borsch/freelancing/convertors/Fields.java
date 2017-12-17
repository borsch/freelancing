package borsch.freelancing.convertors;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class Fields {


    public static class User {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String ROLE = "role";
        public static final String DEVELOPER_RATING = "developer_rating";
        public static final String DEVELOPER_PROJECTS_AMOUNT = "developer_projects_amount";
        public static final String CLIENT_RATING = "client_rating";
        public static final String CLIENT_PROJECTS_AMOUNT = "client_projects_amount";
        public static final String DEVELOPER_SKILL_LEVEL = "developer_skill_level";
        public static final String DEVELOPER_TAGS = "developer_tags";
    }

    public static class Project {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String STATUS = "status";
        public static final String MIN_SKILL_LEVEL = "min_skill_level";
        public static final String DEVELOPER_RATING = "developer_rating";
        public static final String CLIENT_RATING = "client_rating";
        public static final String TAGS = "tags";
        public static final String DEVELOPER_ID = "developer_id";
        public static final String CLIENT_ID = "client_id";
    }

    public static final String DEFAULT ="id";
}
