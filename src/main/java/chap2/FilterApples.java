package chap2;

import chap2.predicate.ApplePredicate;
import java.util.ArrayList;
import java.util.List;

public class FilterApples {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
