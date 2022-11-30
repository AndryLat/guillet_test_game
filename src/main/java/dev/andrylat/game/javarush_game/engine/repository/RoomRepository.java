package dev.andrylat.game.javarush_game.engine.repository;

import dev.andrylat.game.javarush_game.engine.domain.Door;
import dev.andrylat.game.javarush_game.engine.domain.Room;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RoomRepository extends Repository<Integer, Room>{

    public RoomRepository(Map<Integer, Room> repository) {
        super(repository);
    }

    public Map<Integer, String> fetchNextRooms(Integer currentRoomId) {
        return this.repository
                .get(currentRoomId)
                .getDoors()
                .stream()
                .map(Door::getRoomId)
                .collect(Collectors.toMap(Function.identity(), id -> repository.get(id).getName()));

    }

}
