package gb.xokyopo.remote.ui;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryManipulation implements Serializable {
    private CategoryRep categoryRep;
    @EJB(beanName = "RemoteService")
    private EjbRemoteImpl ejbRemote;

    public String addCategory() {
        this.categoryRep = new CategoryRep();
        return "/category.xhtml";
    }

    public String editingCategory(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
        return "/category.xhtml";
    }

    public String deletingCategory(CategoryRep categoryRep) {
        this.ejbRemote.removeCategory(categoryRep);
        return "/categories.xhtml?faces-redirect=true";
    }

    public String saveChange() {
        if (!this.categoryRep.getName().equals("")) {
            if (this.categoryRep.getId() > 0) {
                //TODO Update
//                this.ejbRemote.(this.categoryRep);
            } else {
                this.ejbRemote.insertCategory(this.categoryRep);
            }
            return "/categories.xhtml?faces-redirect=true";
        }
        return "/category.xhtml";
    }

    public String cancel() {
        return "/categories.xhtml?faces-redirect=true";
    }

    public CategoryRep getCategoryRep() {
        return categoryRep;
    }

    public void setCategoryRep(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
    }

    public List<CategoryRep> getAll() {
        return this.ejbRemote.getAllCategory();
    }
}
