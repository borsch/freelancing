package borsch.freelancing.services.clients;

import borsch.freelancing.pojo.entities.ClientEntity;
import borsch.freelancing.pojo.view.ClientView;
import borsch.freelancing.services.BaseService;

/**
 * Created by olehkurpiak on 17.12.2017.
 */
public abstract class IClientService extends BaseService<ClientEntity, ClientView, Integer> {
    public IClientService() {
        super(ClientEntity.class);
    }
}
