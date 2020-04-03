package gb.xokyopo.servlets.pages;

//d. Сервлет для корзины /cart;
/*
2. В каждый сервлет добавить:
a. Заголовок с именем страницы (товары, товар, корзина и т.д.);
b. Спсиок (навигационное меню), с помощью которого можно перемещаться между созданными сервлетами.
 */

import gb.xokyopo.servlets.template.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Cat", urlPatterns = "/cart")
public class Cart extends MyServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
