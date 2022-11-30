package dev.andrylat.game.javarush_game.engine.dto;

import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomInfo {
    private Integer id;
    private String name;

    private List<DoorInfo> doors;
    private List<Npc> npcs;
    private List<Item> items;

}
