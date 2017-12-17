package borsch.freelancing.services.utils;

import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.enums.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import borsch.freelancing.exceptions.service_error.AuthRequiredException;
import borsch.freelancing.persistence.dao.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 10.09.2016.
 */
@Service
public class SessionUtils {

    @Autowired
    private UsersRepository usersRepository;

    public UserEntity getCurrentUser() {
        if (isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity entity = (UserEntity)authentication.getPrincipal();

            return usersRepository.findByEmail(entity.getEmail());
        } else
            return null;
    }
    public boolean isAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    public void authorized() throws AuthRequiredException {
        if(!isAuthorized()){
            throw new AuthRequiredException();
        }
    }

    public boolean isAdmin() {
        return isUserWithRole(RolesEnum.admin);
    }

    public boolean isUserWithRole(RolesEnum... userRoles){
        if(isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for (RolesEnum rolesEnum : userRoles) {
                if (authentication.getAuthorities().contains(ROLES_MAP.get(rolesEnum.name()))) {
                    return true; // user has this role, so it's not forbidden
                }
            }
        }
        return false;
    }


    static final Map<String , SimpleGrantedAuthority> ROLES_MAP = new HashMap<String , SimpleGrantedAuthority>() {{
        put("admin",    new SimpleGrantedAuthority("ROLE_ADMIN"));
        put("user",   new SimpleGrantedAuthority("ROLE_USER"));
    }};
}
