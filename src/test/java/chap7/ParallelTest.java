package chap7;

import static java.util.stream.Collectors.reducing;
import static org.assertj.core.api.Assertions.assertThat;

import chap3.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ParallelTest {

    @Test
    void getSumUsingSequentialStream() {
        assertThat(sequentialSum(10)).isEqualTo(55);
    }

    @Test
    void getSumUsingParallelSumStream() {
        assertThat(parallelSum(10)).isEqualTo(55);
    }

    @Test
    void sequentialParallelConversion() {
        Stream.iterate(1L, i -> i + 1)
            .limit(10)
            .sequential()
            .parallel()
            .forEach(i -> System.out.println("i = " + i));
    }


    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
    }



    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .parallel()
            .reduce(0L, Long::sum);
    }
}
