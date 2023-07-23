package chap2;

import chap2.apple.Apple;
import chap2.apple.FilterApples;
import chap2.predicate.AppleGreenColorPredicate;
import chap2.predicate.AppleHeavyWeightPredicate;
import java.util.Arrays;
import java.util.List;

public class Ex1 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
            new Apple("green", 80),
            new Apple("green", 155),
            new Apple("red", 120));
        List<Apple> heavyApples = FilterApples.filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = FilterApples.filterApples(inventory, new AppleGreenColorPredicate());

        System.out.println("greenApples = " + greenApples);
        System.out.println("heavyApples = " + heavyApples);
    }
}
