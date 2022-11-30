package dev.andrylat.game.javarush_game.engine.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Door {
    private Integer roomId;
}
