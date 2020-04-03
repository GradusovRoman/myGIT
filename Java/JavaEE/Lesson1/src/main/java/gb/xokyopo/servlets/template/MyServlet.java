package gb.xokyopo.servlets.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class MyServlet extends HttpServlet implements Serializable {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.logger.info("im created");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logger.info("---------------------------------------------------");
        this.logger.info("run method service in " + this.getClass().getSimpleName());
        this.logger.info("---------------------------------------------------");
        this.getServletContext().getRequestDispatcher("/" + this.getClass().getSimpleName().toLowerCase() + ".jsp").forward(req, resp);
    }

//    @Override
//    public String getServletInfo() {
//        return this.getServletInfo();
//    }

    @Override
    public void destroy() {
        this.logger.info("!!!!im destroyed!!!!");
    }
}
