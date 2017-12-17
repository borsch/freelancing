package borsch.freelancing.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleh on 04.08.2017.
 */
public class PermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return hasPermission(authentication, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, permission);
    }

    private boolean hasPermission(Authentication authentication, Object permissionObject) {
        if (authentication == null || permissionObject == null || !(permissionObject instanceof String)) {
            return false;
        }

        Set<String> permissions = parsePermission(permissionObject);

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (permissions.contains(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    private Set<String> parsePermission(Object permissionObject) {
        String[] permissions = permissionObject.toString().split(",");
        Set<String> result = new HashSet<>();

        for (String permission : permissions) {
            result.add(permission.trim());
        }

        return result;
    }
}
