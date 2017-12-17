package borsch.freelancing.services.users;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.RolesEnum;
import borsch.freelancing.services.BaseValidator;
import borsch.freelancing.services.utils.SessionUtils;
import borsch.freelancing.exceptions.conflict.EmailExistsException;
import borsch.freelancing.exceptions.not_found.NoSuchEntityException;
import borsch.freelancing.exceptions.service_error.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import borsch.freelancing.exceptions.service_error.ServiceErrorException;

import javax.validation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 10.09.2016.
 *
 * TODO: add permission check
 */
@Service
public class UserValidateServiceImpl
        extends BaseValidator<UserEntity, Integer>
        implements IUserValidateService {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private Validator validator;

    @Autowired
    private IUserService userService;

    public UserValidateServiceImpl() {
        super(UserEntity.class);
    }

    @Override
    public void validForCreate(UserEntity user) throws ServiceErrorException, borsch.freelancing.exceptions.service_error.ValidationException, EmailExistsException {
        if (user.getId()>0)
            throw new ServiceErrorException("User should be updated");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        if(violations != null && !violations.isEmpty()) {
            throw new borsch.freelancing.exceptions.service_error.ValidationException(UserEntity.class.getName(), violations);
        }
        try{
            userService.getByEmail(user.getEmail());
            throw new EmailExistsException();
        } catch (NoSuchEntityException e) {
            if (!sessionUtils.isAuthorized()){
                if (user.getRoleEntity()==null || user.getRoleEntity().getName() != RolesEnum.user){
                    throw new ServiceErrorException();
                }
            }else if (!sessionUtils.isUserWithRole(RolesEnum.admin)){
                throw new ServiceErrorException();
            }
        }
    }

    @Override
    public void validForUpdate(UserEntity entity) throws BaseException {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new borsch.freelancing.exceptions.service_error.ValidationException(UserEntity.class.getName(), violations);
        }

        UserEntity current = sessionUtils.getCurrentUser();
        if (!sessionUtils.isAdmin() &&
                (current == null || current.compareId(entity.getId()) != 0))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(UserEntity entity) throws BaseException {
        UserEntity current = sessionUtils.getCurrentUser();
        if (!sessionUtils.isAdmin() &&
                (current == null || current.compareId(entity.getId()) != 0))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(List<UserEntity> entities) throws BaseException {
        if (!sessionUtils.isAdmin())
            throw new ForbiddenException();
    }

    @Override
    public void validForDelete(UserEntity entity) throws BaseException {
        if (!sessionUtils.isAdmin())
            throw new ForbiddenException();
    }
}
