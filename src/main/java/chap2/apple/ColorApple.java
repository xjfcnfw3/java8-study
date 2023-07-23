package chap2.apple;

import chap2.apple.Apple;
import java.util.ArrayList;
import java.util.List;

public class ColorApple {
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }
}
