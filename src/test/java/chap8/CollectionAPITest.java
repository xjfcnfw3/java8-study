package chap8;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CollectionAPITest {

    @Test
    void unSupportedOperationExceptionTest() {
        List<String> cars = Arrays.asList("bus", "mini", "sport");

        assertThatThrownBy(() -> cars.add("truck"))
            .isInstanceOf(UnsupportedOperationException.class);

    }
}
