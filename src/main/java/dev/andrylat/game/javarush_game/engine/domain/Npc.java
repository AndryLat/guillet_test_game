package dev.andrylat.game.javarush_game.engine.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Npc {
    private Integer id;
    private String name;
    private Integer startMessageId;
    private Integer health;
}
