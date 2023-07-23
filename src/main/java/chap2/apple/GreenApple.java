package chap2.apple;

import chap2.apple.Apple;
import java.util.ArrayList;
import java.util.List;

public class GreenApple {
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}
