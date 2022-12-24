package ru.job4j.pooh;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req(HttpMethod.POST,
                        QueueMode.queue,
                        "weather",
                        paramForPostMethod)
        );
        /* Забираем данные из очереди weather. Режим queue */
        Resp result = queueService.process(
                new Req(HttpMethod.GET,
                        QueueMode.queue,
                        "weather",
                        null)
        );
        assertThat(result.text()).isEqualTo("temperature=18");
    }

    @Test
    public void whenPostThenGetQueue2() {
        QueueService queueService = new QueueService();
        String paramForPostMethod1 = "temperature=18";
        String paramForPostMethod2 = "temperature=29";
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req(HttpMethod.POST, QueueMode.queue,
                        "weather",
                        paramForPostMethod1)
        );
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req(HttpMethod.POST,
                        QueueMode.queue,
                        "weather",
                        paramForPostMethod2)
        );
        /* Забираем данные из очереди weather. Режим queue */
        Resp result1 = queueService.process(
                new Req(HttpMethod.GET,
                        QueueMode.queue,
                        "weather",
                        null)
        );
        assertThat(result1.text()).isEqualTo("temperature=18");
        /* Забираем данные из очереди weather. Режим queue */
        Resp result2 = queueService.process(
                new Req(HttpMethod.GET,
                        QueueMode.queue,
                        "weather",
                        null)
        );
        assertThat(result2.text()).isEqualTo("temperature=29");
    }
}