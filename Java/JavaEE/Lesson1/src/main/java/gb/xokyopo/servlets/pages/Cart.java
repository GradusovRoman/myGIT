package gb.xokyopo.servlets.pages;

//d. Сервлет для корзины /cart;
/*
2. В каждый сервлет добавить:
a. Заголовок с именем страницы (товары, товар, корзина и т.д.);
b. Спсиок (навигационное меню), с помощью которого можно перемещаться между созданными сервлетами.
 */

import gb.xokyopo.servlets.template.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "Cat", urlPatterns = "/cart")
public class Cart extends MyServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        super.service(servletRequest, servletResponse);
        servletResponse.getWriter().println("<h1 align =\"center\">This is Cart page</h1>");
        servletResponse.getWriter().println("<h1 align =\"center\">Это карзина покупок</h1>");
    }
}
