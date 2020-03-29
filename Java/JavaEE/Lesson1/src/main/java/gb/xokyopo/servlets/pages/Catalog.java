package gb.xokyopo.servlets.pages;

//b. Сервлет для каталога товаров /catalog;
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

@WebServlet(name = "Catalog" , urlPatterns = "/catalog")
public class Catalog extends MyServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        super.service(servletRequest, servletResponse);
        servletResponse.getWriter().println("<h1 align =\"center\">This is Catalog page</h1>");
        servletResponse.getWriter().println("<h1 align =\"center\">Это Каталог</h1>");
    }

}
