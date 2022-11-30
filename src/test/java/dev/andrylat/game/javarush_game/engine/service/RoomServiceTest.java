package dev.andrylat.game.javarush_game.engine.service;

import dev.andrylat.game.javarush_game.engine.domain.Item;
import dev.andrylat.game.javarush_game.engine.domain.Npc;
import dev.andrylat.game.javarush_game.engine.domain.User;
import dev.andrylat.game.javarush_game.engine.repository.Repository;
import dev.andrylat.game.javarush_game.engine.repository.RoomRepository;
import dev.andrylat.game.javarush_game.engine.service.exception.InvalidStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private Repository<String, User> userRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private Repository<Integer, Npc> npcRepository;

    @Mock
    private Repository<Integer, Item> itemRepository;

    private RoomService roomService;

    @BeforeEach
    void setup() {
        roomService = new RoomService(userRepository, roomRepository, npcRepository, itemRepository);
    }

    @Test
    void test_moveUser_ShouldThrowException_WhenUsernameIsNull() {
        assertThrows(InvalidStateException.class,
                () -> roomService.moveUser(null, "1"));
    }

    @Test
    void test_moveUser_ShouldThrowException_WhenNextRoomIdIsNull() {
        assertThrows(InvalidStateException.class,
                () -> roomService.moveUser("stub", null));
    }

    @Test
    void test_moveUser_ShouldThrowException_WhenBothArgumentsAreNull() {
        assertThrows(InvalidStateException.class,
                () -> roomService.moveUser(null, null));
    }

    @Test
    void test_moveUser_ShouldThrowException_WhenNextRoomIdIsNotInteger() {
        assertThrows(InvalidStateException.class,
                () -> roomService.moveUser("stub", "stub"));
    }

    @Test
    void test_moveUser_ShouldThrowException_WhenMoveIsIncorrect() {
        User mockedUser = mock(User.class);

        when(userRepository.getById(eq("test")))
                .thenReturn(mockedUser);
        when(mockedUser.getCurrentRoomId()).thenReturn(1);

        Map<Integer, String> rooms = Map.of(2, "room2", 3, "room3");
        when(roomRepository.fetchNextRooms(eq(1)))
                .thenReturn(rooms);

        assertThrows(InvalidStateException.class,
                () -> roomService.moveUser("test", "5"));
    }

    @Test
    void test_moveUser_WhenMoveIsCorrect() {
        User mockedUser = mock(User.class);

        when(userRepository.test(argThat(
                user -> user.getCurrentRoomId() == 1
                        && user.getUsername().endsWith("test"))))
                .thenReturn(true);

        when(userRepository.getById(eq("test")))
                .thenReturn(mockedUser);
        when(mockedUser.getCurrentRoomId()).thenReturn(1);

        Map<Integer, String> rooms = Map.of(2, "room2", 3, "room3");
        when(roomRepository.fetchNextRooms(eq(1)))
                .thenReturn(rooms);

        roomService.moveUser("test", "2");
        verify(mockedUser, times(1)).setCurrentRoomId(eq(2));
    }


    @Test
    void takeItem() {
    }

    @Test
    void fetchCurrentRoomInfo() {
    }
}