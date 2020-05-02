package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Group;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.GroupRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "GroupService")
public class GroupService implements ServiceImpl<GroupRep> {
    @EJB(beanName = "ServiceUtils")
    private ServiceUtils serviceUtils;

    @Override
    public List<GroupRep> getAll() {
        return this.serviceUtils.getGroupRepository().getAll().stream().map(this.serviceUtils::groupToGroupRep).collect(Collectors.toList());
    }

    @Override
    public void add(GroupRep element) {
        this.serviceUtils.getGroupRepository().create(this.serviceUtils.groupRepToGroup(element, new Group()));
    }

    @Override
    public void update(GroupRep element) {
        this.serviceUtils.getGroupRepository().update(
                this.serviceUtils.groupRepToGroup(element, this.serviceUtils.getGroupRepository().findById(element.getId())));
    }

    @Override
    public void delete(GroupRep element) {
        this.serviceUtils.getGroupRepository().delete(element.getId());
    }

    @Override
    public GroupRep getById(int elementId) {
        return this.serviceUtils.groupToGroupRep(this.serviceUtils.getGroupRepository().findById(elementId));
    }
}
