package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.GroupRep;
import gb.xokyopo.servlets.service.represantations.UserRep;
import gb.xokyopo.servlets.ui.local.impl.ManipulationImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class UsersManipulation implements Serializable, ManipulationImpl<UserRep> {
    @EJB(beanName = "UserService")
    private ServiceImpl<UserRep> userService;
    @EJB(beanName = "GroupsService")
    private ServiceImpl<GroupRep> groupService;
    private UserRep userRep;

    @Override
    public String update(UserRep element) {
        this.userRep = element;
        this.userRep.getGroupRepList().forEach(groupRep -> {
            System.out.println("group = " + groupRep.getName());
        });
        return "/admin/user.xhtml?faces-redirect=true";
    }

    @Override
    public String add() {
        this.userRep = new UserRep();
        this.userRep.setGroupRepList(new ArrayList<>());
        return "/admin/user.xhtml?faces-redirect=true";
    }

    @Override
    public String delete(UserRep element) {
        this.userService.delete(element);
        return "/admin/userlist.xhtml?faces-redirect=true";
    }

    @Override
    public String save() {
        if (!(userRep.getName().equals("") || this.userRep.getPass().equals(""))) {
            if (this.userRep.getId() > 0) {
                this.userService.update(this.userRep);
            } else {
                //TODO проверка на совпадения
                this.userService.add(this.userRep);
            }
            return "/admin/userlist.xhtml?faces-redirect=true";
        }
        return "/admin/user.xhtml?faces-redirect=true";
    }

    @Override
    public UserRep getElement() {
        return this.userRep;
    }

    @Override
    public void setElement(UserRep element) {
        this.userRep = element;
    }

    @Override
    public String cancel() {
        return "/admin/userlist.xhtml?faces-redirect=true";
    }

    @Override
    public List<UserRep> getAll() {
        return this.userService.getAll();
    }

    public List<Integer> getGroupListsID() {
        return this.userRep.getGroupRepList().stream().map(GroupRep::getId).collect(Collectors.toList());
    }

    public void setGroupListsID(List<Integer> groupLists) {
        this.userRep.setGroupRepList(groupLists.stream()
                .map(this.groupService::getById)
                .collect(Collectors.toList())
        );
    }
}
