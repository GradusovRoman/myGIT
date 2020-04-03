package gb.xokyopo.servlets.dao.entity;

import gb.xokyopo.servlets.dao.entity.template.AbstractProduct;

public class Product extends AbstractProduct {
    private String test;

    @Override
    public AbstractProduct createNew() {
        return new Product();
    }
}
