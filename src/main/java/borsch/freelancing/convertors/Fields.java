package borsch.freelancing.convertors;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class Fields {


    public static class User {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_NAME = "first_name";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String ACTIVE = "active";
        public static final String PHONE = "phone";
        public static final String REQUESTS = "requests_ids";
        public static final String REQUESTS_COMMENTS = "requests_comments_ids";

        public static final String DEFAULT = ID + ',' + EMAIL;
    }

    public static class InfoPage {
        public static final String ID = "id";
        public static final String URL = "url";
        public static final String HEADER = "header";
        public static final String SUB_HEADER = "sub_header";
        public static final String TEXT = "text";
        public static final String ACTIVE = "active";
        public static final String META_TITLE = "meta_title";
        public static final String META_DESCRIPTION = "meta_description";
        public static final String META_KEYWORDS = "meta_keywords";

        public static final String DEFAULT = ID + ',' + URL;
    }

    public static class Company{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String HAS_IMG = "has_image";
        public static final String TEXT = "text";
        public static final String ACTIVE ="active";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static class Service{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String HAS_IMG = "has_icon";
        public static final String DESCRIPTION = "description";
        public static final String SHORT_DESCRIPTION = "short_description";
        public static final String ACTIVE ="active";
        public static final String POSITION = "position";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static class Testimonial{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TEXT = "text";
        public static final String POSITION = "position";
        public static final String ACTIVE ="active";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static class Block{
        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String TITLE = "title";
    }

    public static class Industry{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POSITION = "position";
    }

    public static class Interest{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POSITION = "position";
    }

    public static class Role {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PERMISSIONS = "permissions";
    }

    public static class Request{
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String COMMENTS = "comments";
        public static final String INDUSTRY = "industry";
        public static final String INDUSTRY_NAME = "industry_name";
        public static final String INTEREST_ALTER = "interest_alter";
        public static final String INTERESTS = "interests";
        public static final String INTERESTS_NAME = "interests_name";
        public static final String COMPANY_NAME = "company_name";
        public static final String HAS_FILE = "has_file";
        public static final String STATUS = "status";
    }

    public static class RequestComment{
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String REQUEST = "request";
        public static final String TEXT = "text";
        public static final String HAS_FILE = "has_file";
        public static final String DATE = "date";
    }

    public static class EmailSubscribe{
        public static final String ID = "id";
        public static final String EMAIL = "email";
    }

    public static class EmailTemplate{
        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String SUBJECT = "subject";
        public static final String TEXT = "text";
    }

    public static class Menu{
        public static final String ID = "id";
        public static final String CODE="code";
        public static final String NAME = "name";
        public static final String MENU_ITEMS = "menu_items";
    }

    public static class MenuItem{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String MENU = "menu";
        public static final String PARENT_MENU = "parent_menu";
        public static final String CHILD_ITEMS = "child_items";
        public static final String URL = "url";
        public static final String POSITION = "position";
    }

    public static class BlogCategory{
        public static final String ID ="id";
        public static final String NAME ="name";
        public static final String CODE = "code";
        public static final String POSITION = "position";
        public static final String POSTS = "posts";
    }
    public static class Post{
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String SHORT_DESCRIPTION = "short_description";
        public static final String DATE = "date";
        public static final String HAS_IMG = "has_img";
        public static final String META_TITLE = "meta_title";
        public static final String META_DESCRIPTION = "meta_description";
        public static final String META_KEYWORDS = "meta_keywords";
        public static final String CATEGORY = "category";
        public static final String CATEGORY_CODE = "category_code";
        public static final String CATEGORY_NAME = "category_name";
        public static final String POST_TEXT = "post_text";
    }

    public static class Employee{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POSITION = "position";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String DESCRIPTION = "description";
        public static final String HAS_IMG = "has_image";
    }

    public static class CarouselImage{
        public static final String ID = "id";
        public static final String POSITION = "position";
        public static final String DESCRIPTION = "description";
        public static final String HAS_IMG = "has_image";
    }

    public static final String DEFAULT ="id";
}
