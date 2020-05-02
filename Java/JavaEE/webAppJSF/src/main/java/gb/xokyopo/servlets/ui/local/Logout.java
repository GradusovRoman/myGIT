package gb.xokyopo.servlets.ui.local;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
@ServletSecurity(
        @HttpConstraint(rolesAllowed={"GUEST", "ADMIN"})
)
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.logout();
         req.getSession().invalidate();
         resp.sendRedirect(getServletContext().getContextPath());
     }
}
