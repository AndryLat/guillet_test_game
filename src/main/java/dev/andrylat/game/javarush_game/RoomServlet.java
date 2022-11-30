package dev.andrylat.game.javarush_game;

import dev.andrylat.game.javarush_game.engine.dto.RoomInfo;
import dev.andrylat.game.javarush_game.engine.service.RoomService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        roomService = (RoomService) servletContext.getAttribute("roomService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        RoomInfo roomInfo = roomService.fetchCurrentRoomInfo(username);

        request.setAttribute("roomInfo", roomInfo);
        request.setAttribute("doors", roomInfo.getDoors());
        request.setAttribute("npcs", roomInfo.getNpcs());
        request.setAttribute("items", roomInfo.getItems());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/room.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String nextRoomId = request.getParameter("nextRoomId");

        if (nextRoomId != null) {
            roomService.moveUser(username, nextRoomId);
        }

        String itemId = request.getParameter("itemId");
        if (itemId != null) {
            roomService.takeItem(username, itemId);
        }

        response.sendRedirect("room");
    }
}
