package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.CategoryService;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryManipulation implements Serializable {
    private CategoryRep categoryRep;
    private CategoryService categoryService;

    @Inject
    public CategoryManipulation(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String addCategory() {
        this.categoryRep = new CategoryRep();
        return "/category.xhtml";
    }

    public String editingCategory(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
        return "/category.xhtml";
    }

    public String deletingCategory(CategoryRep categoryRep) {
        this.categoryService.deleteCategory(categoryRep);
        return "/categories.xhtml?faces-redirect=true";
    }

    public String saveChange() {
        if (!this.categoryRep.getName().equals("")) {
            if (this.categoryRep.getId() <= 0) {
                this.categoryService.addCategory(this.categoryRep);
            } else {
                this.categoryService.updateCategory(this.categoryRep);
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
        return this.categoryService.getCategoryRepList();
    }
}
