package gb.xokyopo.servlets.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ContentTypeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.logger.info("im created");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.logger.info("---->>> filter is running");
        this.logger.info(servletRequest.getCharacterEncoding());

        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);

        this.logger.info(servletResponse.getCharacterEncoding());
        this.logger.info("---->>> filter is stopped");
    }

    @Override
    public void destroy() {
        this.logger.info("im destroyed");
    }
}
