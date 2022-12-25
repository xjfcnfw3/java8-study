package chap6;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.assertj.core.api.Assertions.assertThat;

import chap4.Dish;
import chap4.Dish.Type;
import chap4.DishTest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DishesStreamTest {

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getMaxCaloriesDish(List<Dish> menu) {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        System.out.println("mostCalorieDish = " + mostCalorieDish);
        assertThat(mostCalorieDish.get().getName()).isEqualTo("pork");
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getSumCaloriesDishes(List<Dish> menu) {
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories = " + totalCalories);
        assertThat(totalCalories).isEqualTo(4200);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getSummaryDishes(List<Dish> menu) {
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);
        assertThat(menuStatistics.getCount()).isEqualTo(9);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getJoinedDishNames(List<Dish> menu) {
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("shortMenu = " + shortMenu);
        assertThat(shortMenu).contains("pork");
    }

    public static Stream<List<Dish>> getTestDishes() {
        return Stream.of(Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)));
    }
}
