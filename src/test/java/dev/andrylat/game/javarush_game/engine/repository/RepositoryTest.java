package dev.andrylat.game.javarush_game.engine.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Map<Integer, String> map;
    private Repository<Integer, String> repository;

    @BeforeEach
    void setup() {
        map = new HashMap<>();
        map.put(1, "stub1");
        map.put(2, "stub2");

        this.repository = new Repository<>(map);
    }

    @Test
    void test_save_WhenNewEntity() {
        String newEntity = "stub_3";

        repository.save(3, newEntity);

        assertEquals(3, map.size());
        assertEquals("stub_3", map.get(3));
    }

    @Test
    void test_save_WhenDuplicatedEntity() {
        String entity = "stub_2";
        repository.save(2, entity);

        assertEquals(2, map.size());

        String expected = "stub_2";
        assertEquals(expected, map.get(2));
    }

    @Test
    void test_save_ShouldThrowException_WhenIdIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> repository.save(null, "stub"));

    }

    @Test
    void test_save_ShouldThrowException_WhenEntityIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> repository.save(1, null));

    }

    @Test
    void test_save_ShouldThrowException_WhenBothArgumentsAreNull() {

        assertThrows(IllegalArgumentException.class,
                () -> repository.save(null, null));

    }

    @Test
    void test_getById_WhenIdIsPresent() {
        String actualEntity = repository.getById(1);

        String expected = "stub1";
        assertEquals(expected, actualEntity);
    }

    @Test
    void test_getById_WhenIdIsNotPresent() {
        String actualEntity = repository.getById(10);

        assertNull(actualEntity);
    }

    @Test
    void test_getById_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.getById(null));
    }

    @Test
    void test_isExists_WhenIdIsPresent() {
        boolean actual = repository.isExists(1);

        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void test_isExists_WhenIdIsNotPresent() {
        boolean actual = repository.isExists(10);

        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void test_isExists_ShouldThrowExcpetion_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.isExists(null));
    }

    @Test
    void test_isEmpty_WhenRepositoryIsNotEmpty() {
        boolean expected = false;
        assertEquals(expected, repository.isEmpty());
    }

    @Test
    void test_isEmpty_WhenRepositoryIsEmpty() {
        this.repository = new Repository<>(new HashMap<>());

        boolean expected = true;
        assertEquals(expected, repository.isEmpty());
    }
}