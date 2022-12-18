/**
 *  Created by Nike Z.
 */
package ru.job4j.pooh;

/**
 * parser http request.
 *
 * @author nikez
 * @version $Id: $Id
 */
public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    /**
     * <p>Constructor for Req.</p>
     *
     * @param httpRequestType a {@link java.lang.String} object.
     * @param poohMode a {@link java.lang.String} object.
     * @param sourceName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     */
    public Req(String httpRequestType,
               String poohMode,
               String sourceName,
               String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    /**
     * <p>of.</p>
     *
     * @param content a {@link java.lang.String} object.
     * @return a {@link ru.job4j.pooh.Req} object.
     */
    public static Req of(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        String[] strings = content.split(System.lineSeparator());
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
                if ("GET".equals(httpRequestType)) {
                    param = "";
                }
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

    /**
     * <p>httpRequestType.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String httpRequestType() {
        return httpRequestType;
    }

    /**
     * <p>Getter for the field <code>poohMode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPoohMode() {
        return poohMode;
    }

    /**
     * <p>Getter for the field <code>sourceName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * <p>Getter for the field <code>param</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParam() {
        return param;
    }
}

