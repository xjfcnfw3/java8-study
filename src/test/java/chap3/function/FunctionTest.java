package chap3.function;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class FunctionTest {

    @Test
    void mapTest() {
        List<Integer> result = FunctionMap.map(
            Arrays.asList("lambdas", "in", "action"),
            (String s) -> s.length()
        );
        System.out.println("result = " + result);
    }
}
