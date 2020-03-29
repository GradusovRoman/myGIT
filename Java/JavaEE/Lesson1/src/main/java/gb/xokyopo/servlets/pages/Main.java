package gb.xokyopo.servlets.pages;

//a. Сервлет для главной страницы /main;
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

@WebServlet(name = "mainPage", urlPatterns = "/main")
public class Main extends MyServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        super.service(servletRequest, servletResponse);
        servletResponse.getWriter().println("<h1 align =\"center\">This is Main page</h1>");
        servletResponse.getWriter().println("<h1 align =\"center\">Это главная страница</h1>");
    }
}
