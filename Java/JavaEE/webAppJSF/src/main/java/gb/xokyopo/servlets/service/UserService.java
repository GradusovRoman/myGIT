package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.User;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.UserRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "UserService")
public class UserService implements ServiceImpl<UserRep> {
    @EJB(beanName = "ServiceUtils")
    private ServiceUtils serviceUtils;

    @Override
    public List<UserRep> getAll() {
        return this.serviceUtils.getUserRepository().getAll().stream().map(this.serviceUtils::userToUserRep).collect(Collectors.toList());
    }

    @Override
    public void add(UserRep element) {
        this.serviceUtils.getUserRepository().create(this.serviceUtils.userRepToUser(element, new User()));
    }

    @Override
    public void update(UserRep element) {
        this.serviceUtils.getUserRepository().update(
                this.serviceUtils.userRepToUser(element, this.serviceUtils.getUserRepository().findById(element.getId())));
    }

    @Override
    public void delete(UserRep element) {
        this.serviceUtils.getUserRepository().delete(element.getId());
    }

    @Override
    public UserRep getById(int elementId) {
        return this.serviceUtils.userToUserRep(this.serviceUtils.getUserRepository().findById(elementId));
    }
}
