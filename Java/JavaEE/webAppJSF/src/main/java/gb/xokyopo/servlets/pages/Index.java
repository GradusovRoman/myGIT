package gb.xokyopo.servlets.pages;

//a. Сервлет для главной страницы /main;
/*
1. Создать jsp-страницы для отдельного товара, корзины, оформления заказа, страницы о компании.
2. В созданных в предыдущем ДЗ сервлетах использовать объект RequestDispatcher для перехода на созданные JSP-страницы.
3. Добавить html-верстку на JSP-страницы
4. На страницу с каталогом добавить 9 статических товаров.
 */

import gb.xokyopo.servlets.template.MyServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainPage", urlPatterns = "/main")
public class Index extends MyServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
