package borsch.freelancing.services.users;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.pojo.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import borsch.freelancing.exceptions.not_found.NoSuchEntityException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 18.08.2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private IUserService userService;

    @Transactional(readOnly=true, rollbackFor = BaseException.class)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user;

        try {
            user = userService.getByEmail(email);
        } catch (NoSuchEntityException e) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), "1",
                true, true, true, true, getGrantedAuthorities(user));
    }


    public static List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getRoleEntity().getName().name()));

        return authorities;
    }
}
