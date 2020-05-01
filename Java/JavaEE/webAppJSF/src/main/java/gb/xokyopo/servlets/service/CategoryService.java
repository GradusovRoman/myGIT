package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "CategoryService")
public class CategoryService implements ServiceImpl<CategoryRep> {
    @EJB(beanName = "ServiceUtils")
    public ServiceUtils serviceUtils;

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
