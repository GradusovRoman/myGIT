package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.CategoriesRepository;
import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryService {
    private CategoriesRepository categoriesRepository;
    private List<CategoryRep> categoryRepList;
    public List<CategoryRep> getCategoryRepList() {
        this.updateCategoryList();
        return categoryRepList;
    }

    public void addCategory(CategoryRep categoryRep) {
        this.categoriesRepository.createCategory(new Category(categoryRep.getName(), categoryRep.getDescription()));
    }

    public void deleteCategory(CategoryRep categoryRep) {
        categoryRepList.remove(categoryRep);
    }

    public void updateCategory(CategoryRep categoryRep) {
        this.categoriesRepository.updateCategory(new Category(categoryRep.getId(), categoryRep.getName(), categoryRep.getDescription()));
    }

    private void updateCategoryList() {
        if (this.categoryRepList == null) this.categoryRepList = new ArrayList<>();
        this.categoryRepList.clear();
        if (this.categoriesRepository == null) {
            this.categoryRepList.add(new CategoryRep(0, "нет","categoriesRepository == null"));
        } else {
            this.categoriesRepository.getAll().forEach(category -> {
                this.categoryRepList.add(new CategoryRep(category.getId(), category.getName(), category.getDescription()));
            });
        }

    }

    @Inject
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }
}
