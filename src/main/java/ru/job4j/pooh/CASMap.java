package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>CASMap class.</p>
 *
 * @author nikez
 * @version $Id: $Id
 */
public class CASMap {
    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
        String name = "weather";

        queue.putIfAbsent(name, new ConcurrentLinkedQueue<>());

        queue.get(name).add("value");

        var text = queue.getOrDefault(name, new ConcurrentLinkedQueue<>()).poll();
    }
}
