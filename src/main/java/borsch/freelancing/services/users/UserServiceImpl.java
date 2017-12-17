package borsch.freelancing.services.users;

import borsch.freelancing.criteria.Criteria;
import borsch.freelancing.criteria.impl.UserCriteria;
import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.exceptions.bad_request.WrongPasswordException;
import borsch.freelancing.persistence.dao.repositories.ClientRepository;
import borsch.freelancing.persistence.dao.repositories.DeveloperRepository;
import borsch.freelancing.persistence.dao.repositories.UsersRepository;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.RolesEnum;
import borsch.freelancing.pojo.view.ClientView;
import borsch.freelancing.pojo.view.DeveloperView;
import borsch.freelancing.services.clients.IClientService;
import borsch.freelancing.services.developers.IDeveloperService;
import borsch.freelancing.exceptions.bad_request.WrongRestrictionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import borsch.freelancing.exceptions.not_found.NoSuchEntityException;
import borsch.freelancing.pojo.view.UserView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Andrii on 18.08.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BaseException.class)
public class UserServiceImpl extends IUserService {

    @Resource
    private UsersRepository usersRepository;

    @Autowired
    private IDeveloperService developerService;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserEntity getByEmail(String email) throws NoSuchEntityException {
        UserEntity entity = usersRepository.findByEmail(email);

        if (entity == null)
            throw new NoSuchEntityException("users", "user email " + email);

        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Integer create(UserView view) throws BaseException, InstantiationException, IllegalAccessException {
        if (view.getRole() == null)
            view.setRole(RolesEnum.user);

        return super.create(view);
    }

    public void update(UserEntity entity) {
        usersRepository.saveAndFlush(entity);
    }

    @Override
    public boolean signInUser(UserView view) throws NoSuchEntityException, WrongPasswordException {
        UserEntity entity = getByEmail(view.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(entity, "1", CustomUserDetailsService.getGrantedAuthorities(entity));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return true;
    }

    @Override
    public boolean logoutUser(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return true;
    }


    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Map<String, Object> registration(UserView user, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, BaseException, InstantiationException {
        int developerId = 0;
        int clientId = 0;

        try {
            final int userId = create(user);
            user.setId(userId);
            signInUser(user);

            DeveloperView developerView = user.createDeveloper();
            developerView.setUser_id(userId);
            developerId = developerService.create(developerView);

            ClientView clientView = new ClientView();
            clientView.setUser_id(userId);
            clientId = clientService.create(clientView);

            return new HashMap<String, Object>() {{
                put("user_id", userId);
            }};
        } catch (BaseException e) {
            if (developerId > 0) {
                developerRepository.delete(developerId);
            }

            if (clientId > 0) {
                clientRepository.delete(clientId);
            }

            if (user.getId() > 0) {
                usersRepository.delete(user.getId());
            }

            logoutUser(request, response);

            throw e;
        }
    }

    @Override
    public Criteria<UserEntity> parse(String restrict) throws WrongRestrictionException {
        return new UserCriteria(restrict);
    }
}
