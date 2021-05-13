
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.example.AlternativeDistanceCalculator;
>>>>>>> f445a51d84082968e524745b0d5ac5d8b6523101
=======
import org.example.AlternativeDistanceCalculator;
>>>>>>> fca94e62ec35cb6e7bb39685def6a652abeef538
import org.example.DistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author
 */
public class CalculateDistanceTest {

    private DistanceCalculator distanceCalculator;
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private AlternativeDistanceCalculator altDistanceCalculator;
>>>>>>> f445a51d84082968e524745b0d5ac5d8b6523101
=======
    private AlternativeDistanceCalculator altDistanceCalculator;
>>>>>>> fca94e62ec35cb6e7bb39685def6a652abeef538

    @BeforeEach
    void setup() throws Exception {
        distanceCalculator = new DistanceCalculator();
<<<<<<< HEAD
<<<<<<< HEAD
=======
        altDistanceCalculator = new AlternativeDistanceCalculator();
>>>>>>> f445a51d84082968e524745b0d5ac5d8b6523101
=======
        altDistanceCalculator = new AlternativeDistanceCalculator();
>>>>>>> fca94e62ec35cb6e7bb39685def6a652abeef538
    }

    @ParameterizedTest
    @CsvSource({
            "true, anv@mail.com, Hà Nội",
            "false, anv@mail.com, New York"
    })
    void test(boolean expected, String address, String province) {
        boolean result;
        try {
            distanceCalculator.calculateDistance(address, province);
<<<<<<< HEAD
<<<<<<< HEAD
=======
            altDistanceCalculator.calculateDistance(province + address);
>>>>>>> f445a51d84082968e524745b0d5ac5d8b6523101
=======
            altDistanceCalculator.calculateDistance(province, address);
>>>>>>> fca94e62ec35cb6e7bb39685def6a652abeef538
            result = true;
        } catch (Exception ex) {
            result = false;
            System.out.println(ex.getMessage());
        }
        assertEquals(expected, result);
    }
}
