package dev.andrylat.game.javarush_game.engine.service;

import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import dev.andrylat.game.javarush_game.engine.domain.Room;
import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.dto.DoorInfo;
import dev.andrylat.game.javarush_game.engine.dto.RoomInfo;
import dev.andrylat.game.javarush_game.engine.repository.Repository;
import dev.andrylat.game.javarush_game.engine.repository.RoomRepository;
import dev.andrylat.game.javarush_game.engine.service.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomService {
    private final Repository<String, User> userRepository;
    private final RoomRepository roomRepository;
    private final Repository<Integer, Npc> npcRepository;
    private final Repository<Integer, Item> itemRepository;

    public RoomService(Repository<String, User> userRepository,
                       RoomRepository roomRepository,
                       Repository<Integer, Npc> npcRepository,
                       Repository<Integer, Item> itemRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.npcRepository = npcRepository;
        this.itemRepository = itemRepository;
    }

    public void moveUser(String username, String nextRoomIdValue) {
        if (username == null) {
            throw new InvalidStateException("username can't be null!");
        }

        if (nextRoomIdValue == null) {
            throw new InvalidStateException("nextRoomId can't be null!");
        }

        Integer nextRoomId;
        try {
            nextRoomId = Integer.parseInt(nextRoomIdValue);
        } catch (NumberFormatException ex) {
            throw new InvalidStateException("NextRoomId is not integer.", ex);
        }

        User user = userRepository.getById(username);
        validateMove(user, nextRoomId);

        user.setCurrentRoomId(nextRoomId);
    }

    public void takeItem(String username, String itemIdValue) {
        if (username == null) {
            throw new InvalidStateException("username can't be null!");
        }

        if (itemIdValue == null) {
            throw new InvalidStateException("itemId can't be null!");
        }

        Integer itemId;
        try {
            itemId = Integer.parseInt(itemIdValue);
        } catch (NumberFormatException ex) {
            throw new InvalidStateException("itemId is not integer.", ex);
        }

        if (!itemRepository.isExists(itemId)) {
            throw new InvalidStateException("Item id " + itemId + " is not present in the game!");
        }

        User user = userRepository.getById(username);
        List<Integer> userItems = user.getItems();
        if (!userItems.contains(itemId)) {
            userItems.add(itemId);
        }
    }

    public RoomInfo fetchCurrentRoomInfo(String username) {
        if (username == null) {
            throw new InvalidStateException("username can't be null!");
        }

        User user = userRepository.getById(username);
        if (user == null) {
            throw new InvalidStateException("User with username " + username + " is not found");
        }

        Integer currentRoomId = user.getCurrentRoomId();

        Room room = roomRepository.getById(currentRoomId);

        List<DoorInfo> doors = roomRepository.fetchNextRooms(currentRoomId)
                .entrySet()
                .stream()
                .map(entry -> DoorInfo.builder()
                        .isOpened(true)
                        .roomInfo(RoomInfo.builder()
                                .id(entry.getKey())
                                .name(entry.getValue())
                                .build())
                        .build())
                .toList();

        List<Npc> npcs = new ArrayList<>();
        for (Integer npcId : room.getNpcs()) {
            Npc npc = npcRepository.getById(npcId);
            npcs.add(npc);
        }

        List<Item> items = new ArrayList<>();
        for (Integer itemId : room.getItems()) {
            if (user.getItems().contains(itemId))
                continue;

            Item item = itemRepository.getById(itemId);
            items.add(item);
        }

        return RoomInfo.builder()
                .id(room.getId())
                .name(room.getName())
                .doors(doors)
                .npcs(npcs)
                .items(items)
                .build();
    }

    private void validateMove(User user, Integer nextRoomId) {
        Integer currentRoomId = user.getCurrentRoomId();
        Map<Integer, String> possibleRooms = roomRepository.fetchNextRooms(currentRoomId);

        if (!possibleRooms.containsKey(nextRoomId)) {
            throw new InvalidStateException("User cant move from room with id " + currentRoomId + " to romm with id " + nextRoomId);
        }
    }
}
