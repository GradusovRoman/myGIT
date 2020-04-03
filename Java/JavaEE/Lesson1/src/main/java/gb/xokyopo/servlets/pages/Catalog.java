package gb.xokyopo.servlets.pages;

//b. Сервлет для каталога товаров /catalog;
/*
2. В каждый сервлет добавить:
a. Заголовок с именем страницы (товары, товар, корзина и т.д.);
b. Спсиок (навигационное меню), с помощью которого можно перемещаться между созданными сервлетами.
 */

import gb.xokyopo.servlets.entity.ProductsCatalog;
import gb.xokyopo.servlets.template.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Catalog" , urlPatterns = "/catalog")
public class Catalog extends MyServlet {
    private ProductsCatalog productsCatalog = ProductsCatalog.getProductsCatalog();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productList", this.productsCatalog.getProducts(9));
        super.doGet(req, resp);
    }
}
