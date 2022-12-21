package chap3.consumer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ConsumerTest {
    @Test
    void forEachTest() {
        ConsumerForEach.forEach(Arrays.asList(1, 2, 3, 4, 5),
            (Integer i) -> System.out.println("i = " + i));
    }
}
