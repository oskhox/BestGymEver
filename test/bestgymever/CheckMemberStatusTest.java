package bestgymever;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckMemberStatusTest {

    //skapar upp en instans av produktionsklassen
    CheckMemberStatus c1 = new CheckMemberStatus();
    //skapar upp en testPath där testfilen också har persondata
    Path testPath = Paths.get("test/bestgymever/test_data.txt");
    //anropar produktionsmetoden och skickar in testdata
    List<Person> list = c1.readFile(testPath);


    //testar att inläsnings-path är korrekt genom skapa upp motsvarande path och jämföra
    @Test
    void testPath() {
        //skapar upp en förväntad path för produktionsklassen
        Path expectedPath = Paths.get("src/bestgymever/data_inlamningsuppg2.txt");
        assertEquals(expectedPath, c1.getInpath());
    }

    //kontrollerar antalet skapade personobjekt
    @Test
    final void testReadFileSize() {
        assertEquals(14, list.size());
    }

    //kontrollerar att det första Person-objektet i arraylisten blivit korrekt inläst och behandlat
    @Test
    final void testFirstPerson() {
        //tar ut den första personen ur arrayList
        Person person1 = list.getFirst();
        assertEquals("7703021234", person1.getPersonID());
        assertEquals("Alhambra Aromes", person1.getName());
        assertEquals("2024-07-01", person1.getDate());
    }

    //kontrollerar att det sista Person-objektet i arraylisten blivit korrekt inläst och behandlat
    @Test
    final void testLastPerson() {
        //tar ut den sista personen ur arrayList
        Person person1 = list.getLast();
        assertEquals("7805211234", person1.getPersonID());
        assertEquals("Nahema Ninsson", person1.getName());
        assertEquals("2024-08-04", person1.getDate());
    }

    //kontrollerar att ett datum som är 3 månader gammalt är giltigt
    @Test
    void testCompareValidDate() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String threeMonthsAgoString = threeMonthsAgo.format(format);
        assertTrue(c1.compareDates(threeMonthsAgoString));
    }

    //kontrollerar att ett datum som är 24 månader gammalt är ogiltigt
    @Test
    void testCompareInvalidDate() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(24);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String threeMonthsAgoString = threeMonthsAgo.format(format);
        assertFalse(c1.compareDates(threeMonthsAgoString));
    }

    //kontrollerar terminalutskriften för giltig medlem
    @Test
    void testOutputValidMember() {
        assertEquals(1, c1.search(list, "7911061234"));
        assertNotEquals(2, c1.search(list, "7911061234"));
        assertNotEquals(3, c1.search(list, "7911061234"));
        assertNotEquals(4, c1.search(list, "7911061234"));
    }

    //kontrollerar terminalutskriften för tidigare medlem
    @Test
    void testOutputPreviousMember() {
        assertEquals(2, c1.search(list, "5711121234"));
        assertNotEquals(1, c1.search(list, "5711121234"));
        assertNotEquals(3, c1.search(list, "5711121234"));
        assertNotEquals(4, c1.search(list, "5711121234"));
    }

    //kontrollerar terminalutskriften vid tom inmatning eller blanksteg
    @Test
    void testOutputIfEmptyInput() {
        assertEquals(3, c1.search(list, ""));
        assertEquals(3, c1.search(list, " "));
        assertNotEquals(3, c1.search(list, "vadsomhelst"));
    }

    //kontrollerar terminalutskriften för obehörig person
    @Test
    void testOutputUnauthorized() {
        assertEquals(4, c1.search(list, "9001191234"));
        assertNotEquals(1, c1.search(list, "9001191234"));
        assertNotEquals(2, c1.search(list, "9001191234"));
        assertNotEquals(3, c1.search(list, "9001191234"));
    }
}