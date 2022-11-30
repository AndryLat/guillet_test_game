package dev.andrylat.game.javarush_game.engine.domain;

import dev.andrylat.game.javarush_game.engine.dto.FightingData;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class User implements Serializable {
    private String username;
    private Integer numberOfGames;
    private Integer currentRoomId;

    private List<Integer> quests = new ArrayList<>();
    private List<Integer> items = new ArrayList<>();

    private Integer currentHealth;
    private Integer maxHealth;

    private FightingData currentFighting;
}
