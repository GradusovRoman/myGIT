package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.*;
import gb.xokyopo.servlets.service.represantations.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless(name = "ServiceUtils")
public class ServiceUtils {
    @EJB(beanName = "CategoriesRepository")
    private Repository<Category> categoriesRepository;
    @EJB(beanName = "ProductRepository")
    private Repository<Product> productRepository;
    @EJB(beanName = "OrderRepository")
    private Repository<Orders> orderRepository;
    @EJB(beanName = "UserRepository")
    private Repository<User> userRepository;
    @EJB(beanName = "GroupRepository")
    private Repository<Group> groupRepository;

    public Product productRepToProduct(ProductRep productRep, Product product) {
        //TODO id тут не тужен
//        product.setId(productRep.getId());
        product.setName(productRep.getName());
        product.setPrice(productRep.getPrice());
        product.setCategory(this.categoriesRepository.findById(productRep.getCategoryRep().getId()));
        return product;
    }

    public ProductRep productToProductRep(Product product) {
        ProductRep outer = new ProductRep();
        outer.setId(product.getId());
        outer.setName(product.getName());
        outer.setPrice(product.getPrice());
        outer.setCategoryRep(this.categoryToCategoryRep(product.getCategory()));
        return outer;
    }

    public Category categoryRepToCategory(CategoryRep categoryRep, Category category) {
        //TODO id тут не тужен
//        category.setId(categoryRep.getId());
        category.setName(categoryRep.getName());
        category.setDescription(categoryRep.getDescription());
        return category;
    }

    public CategoryRep categoryToCategoryRep(Category category) {
        CategoryRep outer = new CategoryRep();
        outer.setId(category.getId());
        outer.setName(category.getName());
        outer.setDescription(category.getDescription());
        return outer;
    }

    public GroupRep groupToGroupRep(Group group) {
        GroupRep outer = new GroupRep();
        outer.setId(group.getId());
        outer.setName(group.getName());
        return outer;
    }

    public Group groupRepToGroup(GroupRep groupRep, Group group) {
        //TODO id тут не тужен
//        group.setId(groupRep.getId());
        group.setName(groupRep.getName());
        return group;
    }

    public UserRep userToUserRep(User user) {
        UserRep outer = new UserRep();
        outer.setId(user.getId());
        outer.setName(user.getName());
        outer.setPass(user.getPass());
        outer.setGroupRepList(
                user.getGroupList().stream().map(this::groupToGroupRep).collect(Collectors.toList())
        );
        return outer;
    }

    public User userRepToUser(UserRep userRep, User user) {
        //TODO id тут не тужен
//        user.setId(userRep.getId());
        user.setName(userRep.getName());
        user.setPass(userRep.getPass());
        user.setGroupList(
                userRep.getGroupRepList().stream().map(groupRep -> this.groupRepository.findById(groupRep.getId())).collect(Collectors.toList())
        );
        return user;
    }

    public OrderRep ordersToOrderRep(Orders orders) {
        OrderRep outer = new OrderRep();
        outer.setId(orders.getId());
        Map<ProductRep, Integer> productRepIntegerMap = new HashMap<>();
        orders.getProductIntegerMap().forEach(
                (product, integer) ->productRepIntegerMap.put(this.productToProductRep(product), integer)
        );
        outer.setProductRepIntegerMap(productRepIntegerMap);
        return outer;
    }

    public Orders orderRepToOrders(OrderRep orderRep, Orders orders) {
        //TODO ID не нужен
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        orderRep.getProductRepIntegerMap().forEach(
                (productRep, integer) -> productIntegerMap.put(this.productRepository.findById(productRep.getId()), integer)
        );
        orders.setProductIntegerMap(productIntegerMap);
        return orders;
    }

    public Repository<Category> getCategoriesRepository() {
        return categoriesRepository;
    }

    public Repository<Product> getProductRepository() {
        return productRepository;
    }

    public Repository<Orders> getOrderRepository() {
        return orderRepository;
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Repository<Group> getGroupRepository() {
        return groupRepository;
    }
}
