package bestgymever;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    public Person p1 = new Person("Bertil Bertilsson", "19800101-0101", "2023-12-31");

    @Test
    void personIDTest() {
        assertEquals("19800101-0101", p1.getPersonID());
    }

    @Test
    final void nameTest() {
        assertEquals("Bertil Bertilsson", p1.getName());
    }

    @Test
    void dateTest() {
        assertEquals("2023-12-31", p1.getDate());
    }
}
