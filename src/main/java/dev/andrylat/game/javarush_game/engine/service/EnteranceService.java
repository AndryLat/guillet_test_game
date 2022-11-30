package dev.andrylat.game.javarush_game.engine.service;

import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.repository.Repository;

public class EnteranceService {

    private final Repository<String, User> userRepository;

    public EnteranceService(Repository<String, User> userRepository) {
        this.userRepository = userRepository;
    }

    public User initOrCreateUser(String username) {
        if (userRepository.isExists(username)) {
            return userRepository.getById(username);
        } else {
            User user = new User();

            user.setUsername(username);
            user.setCurrentRoomId(1);
            user.setMaxHealth(300);
            user.setCurrentHealth(300);

            userRepository.save(username, user);

            return user;
        }
    }

}
