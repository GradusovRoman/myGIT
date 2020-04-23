package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.CategoriesRepository;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryService {
    private CategoriesRepository categoriesRepository;
    private final HashMap<Integer, CategoryRep> categoryRepHashMap;
    private ServiceUtils serviceUtils;

    public CategoryService() {
        this.categoryRepHashMap = new HashMap<>();
    }

    public List<CategoryRep> getCategoryRepList() {
        if (this.categoryRepHashMap.size() == 0) this.updateCategoryList();
        return (List<CategoryRep>) categoryRepHashMap.values();
    }

    public void addCategory(CategoryRep categoryRep) {
        this.categoriesRepository.create(this.serviceUtils.categoryRepToCategory(categoryRep));
        this.updateCategoryList();
    }

    public void deleteCategory(CategoryRep categoryRep) {
        this.categoriesRepository.delete(categoryRep.getId());
        this.updateCategoryList();
    }

    public void updateCategory(CategoryRep categoryRep) {
        this.categoriesRepository.update(this.serviceUtils.categoryRepToCategory(categoryRep));
        this.updateCategoryList();
    }

    private void updateCategoryList() {
        this.categoryRepHashMap.clear();
        this.categoriesRepository.getAll().forEach(category -> {
            this.categoryRepHashMap.put(category.getId() ,this.serviceUtils.categoryToCategoryRep(category));
        });
    }

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    @Inject
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }
    
    public CategoryRep getById(int categoryId) {
        if (this.categoryRepHashMap.size() == 0) this.updateCategoryList();
        return this.categoryRepHashMap.get(categoryId);
    }
}
