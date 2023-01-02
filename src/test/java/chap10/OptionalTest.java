package chap10;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OptionalTest {

    @DisplayName("getCarInsuranceName에 null이 있으면 예외가 발생된다.")
    @Test
    void getInsuranceNameFromPerson() {
        Person person = new Person();
        assertThatThrownBy(() -> getCarInsuranceName(person))
            .isInstanceOf(NullPointerException.class);
    }

    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}
