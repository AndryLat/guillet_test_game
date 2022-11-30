package dev.andrylat.game.javarush_game;

import dev.andrylat.game.javarush_game.engine.domain.Message;
import dev.andrylat.game.javarush_game.engine.domain.Quest;
import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.dto.FightingData;
import dev.andrylat.game.javarush_game.engine.repository.Repository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DialogServlet", value = "/dialog", initParams = @WebInitParam(name = "location", value = "D:/Uploads"))
public class DialogServlet extends HttpServlet {

    private Repository<Integer, Message> messageRepository;
    private Repository<Integer, Quest> questRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        messageRepository = (Repository<Integer, Message>) servletContext.getAttribute("messageRepository");
        questRepository = (Repository<Integer, Quest>) servletContext.getAttribute("questRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("finish") != null) {
            request.getSession().removeAttribute("currentNpcId"); // TODO move to user data
            response.sendRedirect("room");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        if(request.getParameter("quest") != null) {
            String questId = request.getParameter("quest");
            user.getQuests().add(Integer.parseInt(questId));

            request.getSession().removeAttribute("currentNpcId");
            response.sendRedirect("room");
            return;
        }

        if(request.getParameter("fight") != null) {
            String npcId = request.getParameter("fight");

            FightingData fightingData = new FightingData();
            fightingData.setNpcId(Integer.parseInt(request.getSession().getAttribute("currentNpcId").toString()));
            response.sendRedirect("fight");
            return;
        }
        String messageId = request.getParameter("message");
        Message message = messageRepository.getById(Integer.parseInt(messageId));
        request.setAttribute("message", message);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/dialog.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String messageId = request.getParameter("message");

        String npcId = request.getParameter("npcId");
        request.getSession().setAttribute("currentNpcId", npcId);

        response.sendRedirect("dialog?message=" + Integer.parseInt(messageId));
    }
}
