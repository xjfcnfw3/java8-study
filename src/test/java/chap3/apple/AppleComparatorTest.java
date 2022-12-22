package chap3.apple;

import static org.junit.jupiter.api.Assertions.*;

import chap2.Apple;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class AppleComparatorTest {
    @Test
    void appleSortTest1() {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 130),
            new Apple("green", 155),
            new Apple("red", 120));
        inventory.sort(new AppleComparator());
        System.out.println("inventory = " + inventory);
    }


    @Test
    void appleSortTest2() {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 130),
            new Apple("green", 155),
            new Apple("red", 120));
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
        System.out.println("inventory = " + inventory);
    }

    @Test
    void appleSortTest3() {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 130),
            new Apple("green", 155),
            new Apple("red", 120));
        inventory.sort((Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        System.out.println("inventory = " + inventory);
    }

    @Test
    void appleSortTest4() {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 130),
            new Apple("green", 155),
            new Apple("red", 120));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("inventory = " + inventory);
    }
}
