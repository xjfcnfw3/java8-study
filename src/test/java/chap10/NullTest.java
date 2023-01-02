package chap10;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chap10.normal.Car;
import chap10.normal.Insurance;
import chap10.normal.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NullTest {

    @DisplayName("getCarInsuranceNameV1에 null이 있으면 예외가 발생된다.")
    @Test
    void getInsuranceNameFromPersonV1() {
        Person person = new Person();
        assertThatThrownBy(() -> getCarInsuranceNameV1(person))
            .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("getCarInsuranceNameV2에 null이 있으면 Unknown가 반환된다.")
    @Test
    void getInsuranceNameFromPersonV2() {
        Person person = new Person();
        assertThat(getCarInsuranceNameV2(person)).isEqualTo("Unknown");
    }

    @DisplayName("getCarInsuranceNameV3에 null이 있으면 Unknown가 반환된다.")
    @Test
    void getInsuranceNameFromPersonV3() {
        Person person = new Person();
        assertThat(getCarInsuranceNameV3(person)).isEqualTo("Unknown");
    }

    public String getCarInsuranceNameV1(Person person) {
        return person.getCar().getInsurance().getName();
    }

    public String getCarInsuranceNameV2(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return  insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public String getCarInsuranceNameV3(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
