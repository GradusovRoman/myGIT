package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.ui.local.impl.ManipulationImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryManipulation implements Serializable, ManipulationImpl<CategoryRep> {
    private CategoryRep categoryRep;
    @EJB(beanName = "CategoryService")
    private ServiceImpl<CategoryRep> categoryService;

    @Override
    public String cancel() {
        return "/categories.xhtml?faces-redirect=true";
    }

    @Override
    public List<CategoryRep> getAll() {
        return this.categoryService.getAll();
    }

    @Override
    public String update(CategoryRep element) {
        this.categoryRep = element;
        return "/category.xhtml";
    }

    @Override
    public String add() {
        this.categoryRep = new CategoryRep();
        return "/category.xhtml";
    }

    @Override
    public String delete(CategoryRep element) {
        this.categoryService.delete(element);
        return "/categories.xhtml?faces-redirect=true";
    }

    @Override
    public String save() {
        if (!this.categoryRep.getName().equals("")) {
            if (this.categoryRep.getId() > 0) {
                this.categoryService.update(this.categoryRep);
            } else {
                this.categoryService.add(this.categoryRep);
            }
            return "/categories.xhtml?faces-redirect=true";
        }
        return "/category.xhtml";
    }

    @Override
    public CategoryRep getElement() {
        return this.categoryRep;
    }

    @Override
    public void setElement(CategoryRep element) {
        this.categoryRep = element;
    }
}
