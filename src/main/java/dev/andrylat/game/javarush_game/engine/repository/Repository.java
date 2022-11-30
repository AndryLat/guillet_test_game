package dev.andrylat.game.javarush_game.engine.repository;

import dev.andrylat.game.javarush_game.engine.service.exception.InvalidStateException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Repository<K, T> {
    protected Map<K, T> repository;


    public Repository(Map<K, T> repository) {
        this.repository = repository;
    }

    public void save(K id, T entity) {
        validateParams(id, entity);

        repository.put(id, entity);
    }

    private void validateParams(K id, T entity) {
        if(id == null) {
            throw new IllegalArgumentException("id can;t be null");
        }

        if(entity == null) {
            throw new IllegalArgumentException("entity can;t be null");
        }
    }

    public T getById(K id) {
        if(id == null) {
            throw new IllegalArgumentException("id can;t be null");
        }
        return repository.get(id);
    }

    public boolean isExists(K id) {
        if(id == null) {
            throw new IllegalArgumentException("id can;t be null");
        }
        return repository.containsKey(id);
    }

    public boolean isEmpty() {
        return repository.isEmpty();
    }

    public boolean test(T t) {
        return true;
    }


}
