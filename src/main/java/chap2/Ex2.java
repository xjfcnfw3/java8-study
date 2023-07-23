package chap2;

import chap2.other.Filter;
import java.util.Arrays;
import java.util.List;

public class Ex2 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 150, 145, 24, 32);
        List<Integer> result = Filter.filter(numbers, (Integer i ) -> i % 2 == 0);
        System.out.println("result = " + result);
    }
}
