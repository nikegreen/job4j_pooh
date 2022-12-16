/**
 *  Created by Nike Z.
 */
package ru.job4j.pooh;

/**
 * <p>Service interface.</p>
 *
 * @author nikez
 * @version $Id: $Id
 */
public interface Service {
    /**
     * <p>process.</p>
     *
     * @param req a {@link ru.job4j.pooh.Req} object.
     * @return a {@link ru.job4j.pooh.Resp} object.
     */
    Resp process(Req req);
}
