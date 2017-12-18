package borsch.freelancing.mergers;

import borsch.freelancing.persistence.dao.repositories.RoleRepository;
import borsch.freelancing.pojo.entities.RoleEntity;
import borsch.freelancing.pojo.entities.UserEntity;
import borsch.freelancing.pojo.view.UserView;
import borsch.freelancing.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oleh on 31.07.2017.
 */
@Service
public class UserMerger implements Merger<UserEntity, UserView> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void merge(UserEntity entity, UserView view) {
        if(view.getEmail() != null)
            entity.setEmail(view.getEmail());
        else view.setEmail(entity.getEmail());

        if (view.getRole() != null) entity.setRoleEntity(roleRepository.findByName(view.getRole()));
        else if (entity.getRoleEntity() != null) view.setRole(entity.getRoleEntity().getName());

        if (view.getName() != null) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
