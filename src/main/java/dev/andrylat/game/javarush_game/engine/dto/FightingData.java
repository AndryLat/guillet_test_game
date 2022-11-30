package dev.andrylat.game.javarush_game.engine.dto;

import lombok.Data;

@Data
public class FightingData {
    private Integer npcId;
    private String npcName;
    private Integer npcCurrentHealth;
    private Integer npcMaxHealth;
}
