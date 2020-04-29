package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CategoryService implements ServiceImpl<CategoryRep> {
    private ServiceUtils serviceUtils;

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    @Override
    public List<CategoryRep> getAll() {
        return this.serviceUtils.getCategoriesRepository().getAll()
                .stream()
                .map(this.serviceUtils::categoryToCategoryRep)
                .collect(Collectors.toList());
    }

    @Override
    public void add(CategoryRep element) {
        this.serviceUtils.getCategoriesRepository().create(
                this.serviceUtils.categoryRepToCategory(element, new Category()));
    }

    @Override
    public void update(CategoryRep element) {
        this.serviceUtils.getCategoriesRepository().update(
                this.serviceUtils.categoryRepToCategory(element, this.serviceUtils.getCategoriesRepository().findById(element.getId())));
    }

    @Override
    public void delete(CategoryRep element) {
        this.serviceUtils.getCategoriesRepository().delete(element.getId());
    }

    @Override
    public CategoryRep getById(int elementId) {
        return this.serviceUtils.categoryToCategoryRep(this.serviceUtils.getCategoriesRepository().findById(elementId));
    }
}
