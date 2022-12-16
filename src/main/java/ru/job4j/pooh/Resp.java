/**
 *  Created by Nike Z.
 */
package ru.job4j.pooh;

/**
 * <p>Resp class.</p>
 *
 * @author nikez
 * @version $Id: $Id
 */
public class Resp {
    private final String text;
    private final String status;

    /**
     * <p>Constructor for Resp.</p>
     *
     * @param text a {@link java.lang.String} object.
     * @param status a {@link java.lang.String} object.
     */
    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    /**
     * <p>text.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String text() {
        return text;
    }

    /**
     * <p>status.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String status() {
        return status;
    }
}
