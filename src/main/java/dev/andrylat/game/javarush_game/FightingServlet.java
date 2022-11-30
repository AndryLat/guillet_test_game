package dev.andrylat.game.javarush_game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "fightServlet", value = "/fight")
public class FightingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/fight.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String block = request.getParameter("block");
        String attack = request.getParameter("attack");

        Random random = new Random();
        List<String> values = List.of("head", "body", "legs");

        String monsterBlock = values.get(random.nextInt(values.size()));
        String monsterAttack = values.get(random.nextInt(values.size()));


    }
}
