package ru.job4j.pooh;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType,
               String poohMode,
               String sourceName,
               String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        String[] strings = content.split("\r\n");
        String head = strings[0];
        String[] heads = head.split(" ");
        String httpRequestType = heads[0];
        String poohMode = null;
        String sourceName = null;
        String param = null;
        if (heads.length > 1) {
            if (heads[1].startsWith("/queue/")) {
                poohMode = "queue";
                sourceName = heads[1].substring(7);
            } else if (heads[1].startsWith("/topic/")) {
                poohMode = "topic";
                if ("GET".equals(httpRequestType)) {
                    sourceName = heads[1].substring(7, heads[1].indexOf("/", 7));
                    param = heads[1].substring(heads[1].indexOf("/", 7) + 1);
                } else {
                    sourceName = heads[1].substring(7);
                }
            }
        }
        int i = 1;
        for (; i < strings.length - 1; i++) {
            if (strings[i].isEmpty()) {
                param = strings[++i];
                break;
            }
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}

