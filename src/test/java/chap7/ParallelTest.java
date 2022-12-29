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


    @Test
    void measureSequentialSum() {
        System.out.println("Sequential sum done in: " +
            measureSumPerf(ParallelTest::sequentialSum, 10_000_000) + " msecs");
    }

    @Test
    void measureIterativeSum() {
        System.out.println("Iterative sum done in: " +
            measureSumPerf(ParallelTest::iterativeSum, 10_000_000) + " msecs");
    }

    @Test
    void measureParallelSum() {
        System.out.println("Parallel sum done in: " +
            measureSumPerf(ParallelTest::parallelSum, 10_000_000) + " msecs");
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
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