package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CategoryService {
    private ServiceUtils serviceUtils;


    public List<CategoryRep> getCategoryRepList() {
        return this.serviceUtils.getCategoriesRepository().getAll()
                .stream()
                .map(this.serviceUtils::categoryToCategoryRep)
                .collect(Collectors.toList());
    }

    public void addCategory(CategoryRep categoryRep) {
        this.serviceUtils.getCategoriesRepository().create(
                this.serviceUtils.categoryRepToCategory(categoryRep, new Category()));
    }

    public void deleteCategory(CategoryRep categoryRep) {
        this.serviceUtils.getCategoriesRepository().delete(categoryRep.getId());
    }

    public void updateCategory(CategoryRep categoryRep) {
        this.serviceUtils.getCategoriesRepository().update(
                this.serviceUtils.categoryRepToCategory(categoryRep, this.getCategoryById(categoryRep.getId())));
    }

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    public Category getCategoryById(int categoryId) {
        return this.serviceUtils.getCategoriesRepository().findById(categoryId);
    }

}
