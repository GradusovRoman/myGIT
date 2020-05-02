package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.GroupRep;
import gb.xokyopo.servlets.ui.local.impl.ManipulationImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class GroupsManipulation implements Serializable, ManipulationImpl<GroupRep> {
    @EJB(beanName = "GroupService")
    private ServiceImpl<GroupRep> groupService;
    private GroupRep groupRep;

    @Override
    public String update(GroupRep element) {
        this.groupRep = element;
        return "/admin/group.xhtml";
    }

    @Override
    public String add() {
        this.groupRep = new GroupRep();
        return "/admin/group.xhtml";
    }

    @Override
    public String delete(GroupRep element) {
        this.groupService.delete(element);
        return "/admin/grouplist.xhtml";
    }

    @Override
    public String save() {
        if (!this.groupRep.getName().equals("")) {
            if (this.groupRep.getId() > 0) {
                this.groupService.update(this.groupRep);
            } else {
                //TODO проверка на совпадение имен.
                this.groupService.add(groupRep);
            }
            return "/admin/grouplist.xhtml?faces-redirect=true";
        }
        return "/admin/group.xhtml";
    }

    @Override
    public GroupRep getElement() {
        return this.groupRep;
    }

    @Override
    public void setElement(GroupRep element) {
        this.groupRep = element;
    }

    @Override
    public String cancel() {
        return "/admin/grouplist.xhtml?faces-redirect=true";
    }

    @Override
    public List<GroupRep> getAll() {
        return this.groupService.getAll();
    }

    public List<GroupRep> getAllWithoutIncludedGroup() {
        List<GroupRep> sortGroup = this.getAll();
        sortGroup.removeIf(groupRep->{
            if (groupRep.getName().equals("ADMIN") || groupRep.getName().equals("GUEST")) return true;
            return false;
        });
        return sortGroup;
    }
}
