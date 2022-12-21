package chap2;

import chap2.other.Filter;
import chap2.predicate.AppleGreenColorPredicate;
import chap2.predicate.AppleHeavyWeightPredicate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AppleFilterTest {

    @Test
    void applePredicateTest() {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 80),
            new Apple("green", 155),
            new Apple("red", 120));
        List<Apple> heavyApples = FilterApples.filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = FilterApples.filterApples(inventory, new AppleGreenColorPredicate());

        System.out.println("greenApples = " + greenApples);
        System.out.println("heavyApples = " + heavyApples);
    }

    @Test
    void otherPredicateTest() {
        List<Integer> numbers = Arrays.asList(1, 150, 145, 24, 32);
        List<Integer> result = Filter.filter(numbers, (Integer i ) -> i % 2 == 0);
        System.out.println("result = " + result);
    }
}
