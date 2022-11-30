package dev.andrylat.game.javarush_game;

import dev.andrylat.game.javarush_game.engine.repository.Repository;
import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.service.EnteranceService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "mapServlet", value = "/entrance")
public class EntranceServlet extends HttpServlet {

    private EnteranceService enteranceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();

        enteranceService = (EnteranceService) servletContext.getAttribute("enteranceService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("room");
            return;
        }

        String username = request.getParameter("username");
        User user = enteranceService.initOrCreateUser(username);
        session.setAttribute("user", user);
        session.setAttribute("username", username);

        resp.sendRedirect("room");

    }
}
