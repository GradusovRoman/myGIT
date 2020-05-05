package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.GroupRep;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class MainManipulation implements Serializable {
    @EJB(beanName = "GroupsService")
    private ServiceImpl<GroupRep> groupRepService;
    private List<GroupRep> userRole;

    public String getUserName() {
        String name = "";
        try {
            name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if (name == null) { name = "нет имени!"; }
        return name;
    }

    public List<GroupRep> getUserRole() {
        this.userRole = this.groupRepService.getAll();
        userRole.removeIf(GroupRep ->!this.isUserInRole(GroupRep.getName()));
        return userRole;
    }

    public void setUserRole(List<GroupRep> userRole) {
        this.userRole = userRole;
    }

    public boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }

    public boolean isUserLogin() {
        return !this.getUserName().equals("");
    }

    public String logouting() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
