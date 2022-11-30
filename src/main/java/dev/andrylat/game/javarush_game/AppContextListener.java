package dev.andrylat.game.javarush_game;

import dev.andrylat.game.javarush_game.engine.GameInitializer;
import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Message;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import dev.andrylat.game.javarush_game.engine.domain.Quest;
import dev.andrylat.game.javarush_game.engine.repository.Repository;
import dev.andrylat.game.javarush_game.engine.domain.Room;
import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.repository.RoomRepository;
import dev.andrylat.game.javarush_game.engine.service.EnteranceService;
import dev.andrylat.game.javarush_game.engine.service.RoomService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        GameInitializer initializer = new GameInitializer();
        ServletContext ctx = servletContextEvent.getServletContext();

        Repository<String, User> userRepository = new Repository<>(new HashMap<>());
        ctx.setAttribute("userRepository", userRepository);

        RoomRepository roomRepository = new RoomRepository(new HashMap<>());
        for(Room room: initializer.getDefaultMap()) {
            roomRepository.save(room.getId(), room);
        }
        ctx.setAttribute("roomRepository", roomRepository);

        Repository<Integer, Quest> questRepository = new Repository<>(new HashMap<>());
        for (Quest quest : initializer.getDefaultQuests()) {
            questRepository.save(quest.getId(), quest);
        }
        ctx.setAttribute("questRepository", questRepository);

        Repository<Integer, Npc> npcRepository = new Repository<>(new HashMap<>());
        for (Npc npc : initializer.getDefaultNpc()) {
            npcRepository.save(npc.getId(), npc);
        }
        ctx.setAttribute("npcRepository", npcRepository);

        Repository<Integer, Message> messageRepository = new Repository<>(new HashMap<>());
        for (Message message : initializer.getDefaultMessages()) {
            messageRepository.save(message.getId(), message);
        }
        ctx.setAttribute("messageRepository", messageRepository);

        Repository<Integer, Item> itemRepository = new Repository<>(new HashMap<>());
        for (Item item : initializer.getDefaultItems()) {
            itemRepository.save(item.getId(), item);
        }
        ctx.setAttribute("itemRepository", itemRepository);
        EnteranceService enteranceService = new EnteranceService(userRepository);
        ctx.setAttribute("enteranceService", enteranceService);

        RoomService roomService = new RoomService(userRepository, roomRepository, npcRepository, itemRepository);
        ctx.setAttribute("roomService", roomService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

    }

}