package tech_tasks.task_2025_04_16;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 */
public class LRUCache {
    private final int capacity;
    private final Map<Integer, Integer> priorities;
    private final LinkedList<Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.priorities = new HashMap<>(capacity);
        this.cache = new LinkedList<>();
    }

    public int get(int key) {
        int value = this.priorities.getOrDefault(key, -1);
        if (value > 0 && cache.size() > 1) {
            int index = this.cache.indexOf(value);
            if (index != 0) {
                this.cache.set(index, this.cache.get(index - 1));
                this.cache.set(0, value);
            }
        }
        return value;
    }

    public void put(int key, int value) {
        this.priorities.put(key, value);
        this.cache.addFirst(value);
        if (this.cache.size() > this.capacity) {
            Integer lastRead = this.cache.removeLast();
            this.priorities.remove(lastRead);
        }
    }

    @Override
    public String toString() {
        var map = new TreeMap<Integer, Integer>(Comparator.comparingInt(cache::indexOf));
        map.putAll(priorities);
        return map.toString();
//        Function<Integer, Integer> keyFunction = value ->
//                priorities.entrySet().stream().filter(e -> e.getValue().equals(value)).map(Map.Entry::getKey).findFirst().orElse(null);
//        return cache.stream()
//                .map(value -> Map.entry(keyFunction.apply(value), value))
//                .map(entry -> entry.getKey() + "=" + entry.getValue())
//                .collect(Collectors.joining(", ", "{", "}"));
    }
}
