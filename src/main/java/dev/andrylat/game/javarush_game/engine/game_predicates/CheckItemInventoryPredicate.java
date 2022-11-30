package dev.andrylat.game.javarush_game.engine.game_predicates;

import dev.andrylat.game.javarush_game.engine.domain.User;

import java.io.Serializable;
import java.util.function.Predicate;

public class CheckItemInventoryPredicate implements Predicate<User>, Serializable {
    private Integer neededItemId;

    public CheckItemInventoryPredicate(Integer neededItemId) {
        this.neededItemId = neededItemId;
    }

    @Override
    public boolean test(User user) {
        return user.getItems().contains(neededItemId);
    }
}
