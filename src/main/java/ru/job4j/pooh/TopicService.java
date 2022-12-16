/**
 *  Created by Nike Z.
 */
package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * service - handler GET/POST request of "topic".
 *
 * @author nikez
 * @version $Id: $Id
 */
public class TopicService implements Service {
    private final static Map<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>>
            TOPIC = new ConcurrentHashMap<>();

    /** {@inheritDoc} */
    @Override
    public Resp process(Req req) {
        String text = "Bad Request";
        String status = "400";
        if (req != null) {
            text = "Bad Request";
            status = "404";
            String name = req.getSourceName();
            if ("POST".equals(req.httpRequestType())) {
                if (TOPIC.get(name) != null) {
                    for (ConcurrentLinkedQueue<String> queue: TOPIC.get(name).values()) {
                        queue.add(req.getParam());
                    }
                }
                text = name + ":" + req.getParam() + ":OK";
                status = "200";
            } else if ("GET".equals(req.httpRequestType())) {
                TOPIC.putIfAbsent(name, new ConcurrentHashMap<>());
                TOPIC.get(name).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
                text = TOPIC.get(name).get(req.getParam()).poll();
                if (text == null) {
                    text = "";
                }
                status = "200";
            }
        }
        return new Resp(text, status);
    }
}
