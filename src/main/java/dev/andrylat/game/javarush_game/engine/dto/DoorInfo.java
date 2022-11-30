package dev.andrylat.game.javarush_game.engine.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoorInfo {
    private RoomInfo roomInfo;
    private boolean isOpened;
}
