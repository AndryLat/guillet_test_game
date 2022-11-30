package dev.andrylat.game.javarush_game.engine;

import dev.andrylat.game.javarush_game.engine.domain.Door;
import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Message;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import dev.andrylat.game.javarush_game.engine.domain.Quest;
import dev.andrylat.game.javarush_game.engine.domain.Room;
import dev.andrylat.game.javarush_game.engine.game_predicates.CheckItemInventoryPredicate;

import java.util.ArrayList;
import java.util.List;

public class GameInitializer {

    public List<Room> getDefaultMap() {
        List<Room> rooms = new ArrayList<>();
        int idCounter = 0;
        
        Room startingRoom = Room.builder()
                .id(idCounter++)
                .name("Starting Room")
                .build();
        rooms.add(startingRoom);

        Room garden = Room.builder()
                .id(idCounter++)
                .name("Garden")
                .npcs(List.of(1))
                .build();
        rooms.add(garden);

        Room hotel = Room.builder()
                .id(idCounter++)
                .name("Hotel")
                .items(List.of(1))
                .build();
        rooms.add(hotel);

        Room bathroom = Room.builder()
                .id(idCounter++)
                .name("Bathroom")
                .items(List.of(2))
                .build();
        rooms.add(bathroom);


        Room dungeon = Room.builder()
                .id(idCounter++)
                .name("Dungeon")
                .build();
        rooms.add(dungeon);


        Room mines = Room.builder()
                .id(idCounter++)
                .name("Mines")
                .build();
        rooms.add(mines);


        Room torture = Room.builder()
                .id(idCounter++)
                .name("Torture")
                .build();
        rooms.add(torture);


        Room hospital = Room.builder()
                .id(idCounter++)
                .name("Hospital")
                .build();
        rooms.add(hospital);


        Room university = Room.builder()
                .id(idCounter++)
                .name("University")
                .build();
        rooms.add(university);


        Room church = Room.builder()
                .id(idCounter++)
                .name("Church")
                .build();
        rooms.add(church);

        startingRoom.addDoor(Door.builder().roomId(garden.getId()).build());
        garden.addDoor(Door.builder().roomId(startingRoom.getId()).build());
        garden.addDoor(Door.builder().roomId(hotel.getId()).build());
        garden.addDoor(Door.builder().roomId(bathroom.getId()).build());
        hotel.addDoor(Door.builder().roomId(garden.getId()).build());
        hotel.addDoor(Door.builder().roomId(dungeon.getId()).build());
        bathroom.addDoor(Door.builder().roomId(garden.getId()).build());
        bathroom.addDoor(Door.builder().roomId(dungeon.getId()).build());
        dungeon.addDoor(Door.builder().roomId(hotel.getId()).build());
        dungeon.addDoor(Door.builder().roomId(bathroom.getId()).build());
        dungeon.addDoor(Door.builder().roomId(mines.getId()).build());
        dungeon.addDoor(Door.builder().roomId(hospital.getId()).build());
        mines.addDoor(Door.builder().roomId(dungeon.getId()).build());
        mines.addDoor(Door.builder().roomId(torture.getId()).build());
        torture.addDoor(Door.builder().roomId(mines.getId()).build());
        hospital.addDoor(Door.builder().roomId(dungeon.getId()).build());
        hospital.addDoor(Door.builder().roomId(university.getId()).build());
        university.addDoor(Door.builder().roomId(hospital.getId()).build());
        university.addDoor(Door.builder().roomId(church.getId()).build());
        church.addDoor(Door.builder().roomId(university.getId()).build());

        return rooms;
    }

    public List<Npc> getDefaultNpc() {
        List<Npc> npcs = new ArrayList<>();

        Npc npc = Npc.builder()
                .id(1)
                .name("Mauro")
                .startMessageId(1)
                .health(150)
                .build();
        npcs.add(npc);

        return npcs;
    }

    public List<Message> getDefaultMessages() {
        List<Message> messages = new ArrayList<>();

        messages.add(
                Message.builder()
                        .id(1)
                        .text("Hello, I am Maauro! How are you? ")
                        .answers(List.of(
                                Message.Answer.builder()
                                        .text("Hello, bro!")
                                        .nextMessageId(2)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Go away")
                                        .build(),
                                Message.Answer.builder()
                                        .text("Fight!")
                                        .isFighting(true)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(2)
                        .text("I can give you quest. What do you want to search? ")
                        .answers(List.of(
                                Message.Answer.builder()
                                        .text("Sword")
                                        .nextMessageId(3)
                                        .build(),
                                Message.Answer.builder()
                                        .text("Fire")
                                        .nextMessageId(4)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(3)
                        .text("OK, lets go for the Sword!!")
                        .answers(List.of(
                                Message.Answer.builder()
                                        .text("Go!")
                                        .questId(1)
                                        .build()
                        ))
                        .build()
        );

        messages.add(
                Message.builder()
                        .id(4)
                        .text("OK, lets go for the Fire!!")
                        .answers(List.of(
                                Message.Answer.builder()
                                        .text("Lets go!")
                                        .questId(2)
                                        .build()
                        ))
                        .build()
        );

        return messages;

    }

    public List<Quest> getDefaultQuests() {
        List<Quest> quests = new ArrayList<>();

        quests.add(Quest.builder()
                .id(1)
                .text("Find sword")
                .isFinished(new CheckItemInventoryPredicate(1))
                .build());

        quests.add(Quest.builder()
                .id(2)
                .text("Find fire")
                .isFinished(new CheckItemInventoryPredicate(2))
                .build());

        return quests;

    }

    public List<Item> getDefaultItems() {
        List<Item> items = new ArrayList<>();

        items.add(Item.builder().id(1).name("Sword").build());
        items.add(Item.builder().id(2).name("Fireball").build());

        return items;
    }
}
