package dev.andrylat.game.javarush_game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "badServlet", value = "/bad")
public class BadServlet extends HttpServlet {

    private int numberOfGames = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("numberOfGames", numberOfGames);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/bad.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        numberOfGames++;

        resp.sendRedirect("bad");
    }
}
