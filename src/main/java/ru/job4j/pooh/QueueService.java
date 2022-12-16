package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
public class QueueService implements Service {
    private final static Map<String, ConcurrentLinkedQueue<String>>
            QUEUE = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "Bad Request";
        String status = "400";
        if (req != null) {
            text = "Bad Request";
            status = "404";
            String name = req.getSourceName();
            if ("POST".equals(req.httpRequestType())) {
                QUEUE.putIfAbsent(name, new ConcurrentLinkedQueue<>());
                QUEUE.get(name).add(req.getParam());
                text = name + ":" + req.getParam() + ":OK";
                status = "200";
            } else if ("GET".equals(req.httpRequestType())) {
                if (QUEUE.get(name) != null) {
                    text = QUEUE.get(name).poll();
                    if (text == null) {
                        text = "";
                    }
                    status = "200";
                }
            }
        }
        return new Resp(text, status);
    }
}
