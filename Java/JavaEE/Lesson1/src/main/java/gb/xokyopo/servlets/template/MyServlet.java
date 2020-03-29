package gb.xokyopo.servlets.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;

public abstract class MyServlet implements Servlet, Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        this.logger.info("---------------------------------------------------");
        this.logger.info("run method service in " + this.getClass().getSimpleName());
        this.logger.info("---------------------------------------------------");
        servletResponse.getWriter().println("<head><title>" + this.getTitle()+ "</title></head>");
        servletResponse.getWriter().println(this.getNavigationMenu());
    }

    @Override
    public String getServletInfo() {
        return this.getServletInfo();
    }

    @Override
    public void destroy() {
        this.logger.info("!!!!im destroyed!!!!");
    }

    public String getTitle() {
        return this.getClass().getSimpleName();
    }

    public String getNavigationMenu() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table width=\"30%\" align=\"center\"><tr>");
        stringBuffer.append("<td width=\"20%\"><a href = \"main\">Home</a></td>");
        stringBuffer.append("<td width=\"20%\"><a href = \"cart\">Cart</a></td>");
        stringBuffer.append("<td width=\"20%\"><a href = \"order\">Order</a></td>");
        stringBuffer.append("<td width=\"20%\"><a href = \"catalog\">Catalog</a></td>");
        stringBuffer.append("<td width=\"20%\"><a href = \"products\">Products</a></td>");
        stringBuffer.append("</tr></table>");
        return  stringBuffer.toString();
    }
}
