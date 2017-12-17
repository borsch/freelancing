package borsch.freelancing.services.users;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongPasswordException;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.services.BaseService;
import borsch.freelancing.exceptions.not_found.NoSuchEntityException;
import borsch.freelancing.pojo.view.UserView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Andrii on 18.08.2016.
 */
public abstract class IUserService extends BaseService<UserEntity, UserView, Integer> {

    public IUserService() {
        super(UserEntity.class);
    }

    public abstract UserEntity getByEmail(String email) throws NoSuchEntityException;

    public abstract boolean signInUser(UserView view) throws NoSuchEntityException, WrongPasswordException;

    public abstract boolean logoutUser(HttpServletRequest request, HttpServletResponse response);

    public abstract Map<String, Object> registration(UserView view) throws BaseException, InstantiationException, IllegalAccessException;
}
