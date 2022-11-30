package dev.andrylat.game.javarush_game;

import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import dev.andrylat.game.javarush_game.engine.dto.DoorInfo;
import dev.andrylat.game.javarush_game.engine.dto.RoomInfo;
import dev.andrylat.game.javarush_game.engine.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext context;

    @Mock
    // TODO показать как понять, что это за класс и что именн омокать
    RequestDispatcher requestDispatcher;

    @Mock
    RoomService roomService;

    RoomServlet roomServlet;


    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext())
                .thenReturn(context);

        when(context.getAttribute(eq("roomService")))
                .thenReturn(roomService);

        when(request.getSession())
                .thenReturn(session);

        roomServlet = new RoomServlet();
        roomServlet.init(servletConfig);
    }

    @Test
    void test_doGet() throws ServletException, IOException {
        RoomInfo roomInfo = RoomInfo.builder()
                .id(1)
                .name("Room")
                .doors(List.of(DoorInfo.builder()
                        .roomInfo(RoomInfo.builder()
                                .id(2)
                                .name("OtherRoom")
                                .build())
                        .build()))
                .items(List.of(Item.builder()
                        .id(1)
                        .name("Item")
                        .build()))
                .npcs(List.of(Npc.builder()
                        .id(1)
                        .name("NPC")
                        .build()))
                .build();

        when(session.getAttribute(eq("username")))
                .thenReturn("test");

        when(roomService.fetchCurrentRoomInfo(eq("test")))
                .thenReturn(roomInfo);

        when(context.getRequestDispatcher(eq("/WEB-INF/jsp/room.jsp")))
                .thenReturn(requestDispatcher); //TODO надо, чтобы не было NPE

        roomServlet.doGet(request, response);

        verify(request, times(1))
                .setAttribute(eq("roomInfo"), eq(roomInfo));
        verify(request, times(1))
                .setAttribute(eq("doors"), eq(roomInfo.getDoors()));
        verify(request, times(1))
                .setAttribute(eq("npcs"), eq(roomInfo.getNpcs()));
        verify(request, times(1))
                .setAttribute(eq("items"), eq(roomInfo.getItems()));

        verify(requestDispatcher, times(1))
                .forward(request, response);
    }

    @Test
    void test_doPost_WhenUserMoveToOtherRoom() throws ServletException, IOException {
        when(session.getAttribute(eq("username")))
                .thenReturn("test");

        when(request.getParameter(eq("nextRoomId")))
                .thenReturn("1");

        roomServlet.doPost(request, response);

        verify(roomService, times(1))
                .moveUser(eq("test"), eq("1"));
        verify(roomService, never())
                .takeItem(anyString(), anyString());

        verify(response, times(1))
                .sendRedirect(eq("room"));

    }

    @Test
    void test_doPost_WhenUserTakeItem() throws ServletException, IOException {
        when(session.getAttribute(eq("username")))
                .thenReturn("test");

        when(request.getParameter(eq("itemId")))
                .thenReturn("1");
        when(request.getParameter(eq("nextRoomId")))
                .thenReturn(null); // TODO это обязательная строка, иначе мокито нас не поймет и бросит эксепшен

        roomServlet.doPost(request, response);

        verify(roomService, times(1))
                .takeItem(eq("test"), eq("1"));
        verify(roomService, never())
                .moveUser(anyString(), anyString());

        verify(response, times(1))
                .sendRedirect(eq("room"));
    }

}