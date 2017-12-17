package borsch.freelancing.mergers;

import borsch.freelancing.exceptions.BaseException;
import borsch.freelancing.persistence.dao.repositories.UsersRepository;
import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.view.ClientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
@Service
public class ClientMerger implements Merger<ClientEntity, ClientView> {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void merge(ClientEntity entity, ClientView view) throws BaseException {
        if (view.getUser_id() != null) entity.setUser(usersRepository.findOne(view.getUser_id()));
        else if (entity.getUser() != null) view.setUser_id(entity.getUser().getId());
    }
}
