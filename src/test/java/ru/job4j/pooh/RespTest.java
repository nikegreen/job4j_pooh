package ru.job4j.pooh;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RespTest {
    @Test
    public void response() {
        Resp resp = new Resp("text", "status");
        assertThat(resp.text()).isEqualTo("text");
        assertThat(resp.status()).isEqualTo("status");
    }
}